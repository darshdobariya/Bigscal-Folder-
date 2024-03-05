package com.example.gfgbasics;

import static android.Manifest.*;
import static android.Manifest.permission.POST_NOTIFICATIONS;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class RequestPermission extends AppCompatActivity {

    Button btnLocation, btnWifi, btnFiles, btnMicrophone;
    public static final int RequestPermissionCode = 1;
    public static final int RC_NOTIFICATION = 99;
    private static final String CHANNEL_ID = "ID";
    NotificationManagerCompat notificationCompat;
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_permission);

        btnLocation = findViewById(R.id.btnLocation);
        btnWifi = findViewById(R.id.btnWifi);
        btnFiles = findViewById(R.id.btnFiles);
        btnMicrophone = findViewById(R.id.btnMicrophone);

        btnLocation.setOnClickListener(v -> {

        });

        btnWifi.setOnClickListener(v -> {

        });

        btnFiles.setOnClickListener(v -> {
            notificationBuild();
            if (ActivityCompat.checkSelfPermission(this, POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
                return;
            }

            // Keep Eye on this
            notificationCompat.notify(0, notification);
        });

        btnMicrophone.setOnClickListener(v->{
            if(checkPermissionMicrophone()){
                Toast.makeText(this, "Request already granted ...", Toast.LENGTH_SHORT).show();
            }else requestPermissionMicrophone();
        });
    }

    private void requestPermissionMicrophone() {
        ActivityCompat.requestPermissions(this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length > 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }

        if(requestCode == RC_NOTIFICATION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }else Toast.makeText(this, "Not granted .. .. ", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkPermissionMicrophone() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    public void notificationBuild(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            requestPermissions(new String[]{POST_NOTIFICATIONS}, RC_NOTIFICATION);
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Channel", NotificationManager.IMPORTANCE_HIGH);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.om, null);
        BitmapDrawable bitmap = (BitmapDrawable) drawable;
        Bitmap largeIcon = bitmap.getBitmap();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "builderChannel")
                .setSmallIcon(R.drawable.om)
                .setLargeIcon(largeIcon)
                .setContentText("Its Context Text")
                .setChannelId(CHANNEL_ID)
                .setSubText("Title");

        notification = builder.build();
        notificationCompat = NotificationManagerCompat.from(this);
    }
}