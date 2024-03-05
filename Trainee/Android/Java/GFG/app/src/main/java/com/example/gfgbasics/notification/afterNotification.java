package com.example.gfgbasics.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.example.gfgbasics.R;

public class afterNotification extends AppCompatActivity {

    Button btnSendNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_notification);

        btnSendNotification = findViewById(R.id.btnSendNotification);

        btnSendNotification.setOnClickListener(v -> {
//            ServiceNotification service = new ServiceNotification();
//            service.showNotificationWithBadge(this, "Its a Title", "Content.. Nahh!!", R.drawable.home);

            Intent serviceIntent = new Intent(this, ServiceNotification.class);
            startService(serviceIntent);
        });
    }
}