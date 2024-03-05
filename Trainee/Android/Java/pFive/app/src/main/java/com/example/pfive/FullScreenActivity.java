package com.example.pfive;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class FullScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        ImageView imgCapture = findViewById(R.id.imgCapture);

        // Retrieve image URI from intent
        Uri imageUriString = getIntent().getParcelableExtra("imageUri");

        // Load image into ImageView using Glide or another image loading library
        try {
            assert imageUriString != null;
            InputStream inputStream = getContentResolver().openInputStream(imageUriString);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            imgCapture.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}