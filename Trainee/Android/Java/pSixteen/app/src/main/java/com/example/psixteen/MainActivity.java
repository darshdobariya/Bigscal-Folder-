package com.example.psixteen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    /*What we do?
    Develop ImageSaver application
    Allow users to download an image from a given URL, display the image on the screen, and store the downloaded image file in the device's internal storage.
    The app will use a one Activity
    Screen will have one Text field to enter the URL
    Buttons to download images and cancel downloads
    Show download progress in the progress bar
    Show download progress in the notification
    Show the downloaded image on full screen, once the download succeed
    Add a button to save downloaded images in Gallery.
    Use Retrofit for networking
    */

    private TextInputEditText edtUrl;
    private ImageView imgView;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String CHANNEL_ID = "DOWNLOAD_CHANNEL";
    private ProgressBar progressBar;
    private NotificationManagerCompat notificationManager;
    private NotificationCompat.Builder notificationBuilder;
    private ImageApi imageApi;
    private Call<ResponseBody> downloadCall;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUrl = findViewById(R.id.edtUrl);
        Button btnDownload = findViewById(R.id.btnStart);
        Button btnCancel = findViewById(R.id.btnStop);
        imgView = findViewById(R.id.imgView);
        Button btnSave = findViewById(R.id.btnSave);
        progressBar = findViewById(R.id.progressBar);

        createNotificationChannel();

        String img = "id/1334591614/photo/man-using-digital-tablet-online-connect-to-internet-banking-currency-exchange-online-shopping.jpg?s=2048x2048&w=is&k=20&c=i4g0apzIPwkQ-3Iww4YYCrcNncrV5812m39vwUuU9m8=";
        String img2 = "id/808070602/photo/hand-holding-mobile-with-pay-word-and-bill-icon-feature-with-blur-back-office-counter.jpg?s=2048x2048&w=is&k=20&c=_dyiWo8YfpVd67GgMWEzSrOxFk9Ax2YYxpRen7mKtm8=";
        String img3 = "https://cdn.pixabay.com/photo/2016/10/26/19/00/domain-names-1772240_640.png";
        String img4 = "https://www.shutterstock.com/photos/460672/pexels-photo-460672.jpeg?auto=compress&cs=tinysrgb&w=600";
        String img5 = "https://www.shutterstock.com/photos/5634600/pexels-photo-5634600.jpeg?auto=compress&cs=tinysrgb&w=600";
        String img6 = "shutterstock/photos/122512825/display_1500/stock-photo-haleakala-crater-on-top-of-the-volcano-maui-hawaii-very-high-resolution-panorama-122512825.jpg";
        edtUrl.setText(img6);

        if (checkPermissions()) {
            initializeRetrofit();
        }

        btnDownload.setOnClickListener(v -> {

            notificationManager = NotificationManagerCompat.from(this);
            notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.stat_sys_download)
                    .setContentTitle("Downloading Image")
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setAutoCancel(true);

            imageUrl = Objects.requireNonNull(edtUrl.getText()).toString();
            startDownload(imageUrl);
        });

        btnCancel.setOnClickListener(v -> cancelDownload());

        btnSave.setOnClickListener(v -> {

            imgView.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(imgView.getDrawingCache());
            imgView.setDrawingCacheEnabled(false);

            saveImageToGallery(bitmap);
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeRetrofit();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initializeRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.shutterstock.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        imageApi = retrofit.create(ImageApi.class);
    }

    private void startDownload(String imageUrl) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationBuilder.setProgress(100, 0, true);
        notificationManager.notify(1, notificationBuilder.build());

        downloadCall = imageApi.downloadImage(imageUrl);
        downloadCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }

                    Log.e("Response Body : ", String.valueOf(response.body().contentLength()));
                    Log.e("Response Length :", String.valueOf(response.message().length()));

                    // Display downloaded image
                    displayImage(response.body().byteStream());

                    // Reset progress bar
                    progressBar.setProgress(100);
                    notificationBuilder.setContentText("Download Completed ... ").setSmallIcon(android.R.drawable.stat_sys_download_done).setProgress(0, 0, true);
                    notificationManager.notify(1, notificationBuilder.build());
                } else {
                    // Download failed, update notification
                    notificationBuilder.setContentText("Download failed").setProgress(0, 0, false);
                    notificationManager.notify(1, notificationBuilder.build());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                // Download failed, update notification
                notificationBuilder.setContentText("Download failed").setProgress(0, 0, false);
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                notificationManager.notify(1, notificationBuilder.build());
                Log.e("Download failed", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    private void displayImage(InputStream inputStream) {
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        imgView.setImageBitmap(bitmap);
    }

    private void cancelDownload() {
        if (downloadCall != null && downloadCall.isExecuted()) {
            downloadCall.cancel();
            progressBar.setProgress(0);
            notificationManager.cancel(1);
        }
    }

    private void saveImageToGallery(Bitmap bitmap) {
        String fileName = "image_" + System.currentTimeMillis() + ".jpg";
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(directory, fileName);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();

            // Notify the gallery about the newly saved image
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(Uri.fromFile(file));
            sendBroadcast(intent);

            Toast.makeText(this, "Image saved to gallery", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
        }
    }

   /* @Override
    public void onProgress(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response, long bytesRead, long contentLength) {
        // Calculate download percentage
        int progress = (int) ((100 * bytesRead) / contentLength);
        // Update notification with download percentage
        notificationBuilder.setProgress(100, progress, false).setContentText("Downloading: " + progress + "%");
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify(1, notificationBuilder.build());
        // Update progress bar
        progressBar.setProgress(progress);
    }*/
}