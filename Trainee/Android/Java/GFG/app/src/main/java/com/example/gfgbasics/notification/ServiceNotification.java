package com.example.gfgbasics.notification;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.gfgbasics.R;

public class ServiceNotification extends Service {

    private static final String CHANNEL_ID = "my_channel_id";
    private static final String CHANNEL_NAME = "My Channel Name";
    private static final String CHANNEL_DESC = "My Channel Description";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Simulating some background work
        showNotificationWithProgress(this, "Downloading...", "Downloading in progress", R.drawable.home);
        return START_STICKY;
    }

    private void showNotificationWithProgress(Context context, String title, String content, int icon) {
        createNotificationChannel(context);

        // Create notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify(0, builder.build());

        // Simulate progress update
        Thread thread = new Thread(() -> {
            for (int progress = 0; progress <= 100; progress += 10) {
                SystemClock.sleep(2000); // Simulate work
                builder.setProgress(100, progress, false)
                        .setContentText("Downloading " + progress + "%");
                notificationManager.notify(0, builder.build());
            }
            // Download complete, update notification
            builder.setContentText("Download complete")
                    .setProgress(0, 0, false);
            notificationManager.notify(0, builder.build());
        });
        thread.start();
    }

    // Method to create a notification channel (for Android Oreo and above)
    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_NAME;
            String description = CHANNEL_DESC;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}