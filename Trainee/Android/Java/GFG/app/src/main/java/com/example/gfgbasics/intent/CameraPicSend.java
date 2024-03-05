package com.example.gfgbasics.intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.gfgbasics.R;

import android.Manifest;
import java.io.File;

public class CameraPicSend extends AppCompatActivity {

    private static final int CAMERA_PIC_REQUEST = 1337;
    private static final int REQUEST_EXTERNAL_STORAGE_RESULT = 1;
    private static final String FILE_NAME = "image01.jpg";
    private ImageView img1;

    File pictureDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "CameraDemo");
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_pic_send);
        img1 = findViewById(R.id.imageView1);
        if (!pictureDir.exists()) {
            pictureDir.mkdirs();
        }
    }

    // Open the camera app to capture the image
    public void callCameraApp() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File image = new File(pictureDir, FILE_NAME);
        fileUri = Uri.fromFile(image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, CAMERA_PIC_REQUEST);
    }

    public void takePicture(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            callCameraApp();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "External storage permission" + " required to save images", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE_RESULT);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK) {
            ImageView imageView = findViewById(R.id.imageView1);
            File image = new File(pictureDir, FILE_NAME);
            fileUri = Uri.fromFile(image);
            imageView.setImageURI(fileUri);
            emailPicture();
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "You did not click the photo", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_EXTERNAL_STORAGE_RESULT) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callCameraApp();
            } else {
                Toast.makeText(this, "External write permission" + " has not been granted, " + " cannot saved images", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    // Function to send the image through mail
    public void emailPicture() {
        Toast.makeText(this, "Now, sending the mail", Toast.LENGTH_LONG).show();
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("application/image");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{
                // default receiver id
                "enquiry@geeksforgeeks.org"});
        // Subject of the mail
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "New photo");
        // Body of the mail
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Here's a captured image");
        // Set the location of the image file to be added as an attachment
        emailIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
        // Start the email activity to with the prefilled information
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }
}