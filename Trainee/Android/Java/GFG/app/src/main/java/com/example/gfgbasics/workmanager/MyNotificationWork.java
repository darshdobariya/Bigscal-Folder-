package com.example.gfgbasics.workmanager;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.gfgbasics.MainActivity;
import com.example.gfgbasics.R;

import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MyNotificationWork extends Worker {

    public MyNotificationWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            // Get current time
            Calendar now = Calendar.getInstance();
            int currentHour = now.get(Calendar.HOUR_OF_DAY);
            int currentMinute = now.get(Calendar.MINUTE);

            if (currentHour > 12) {
                currentHour = currentHour - 12;
            }

            int currentTime = (currentHour * 60) + currentMinute;

            int time = getInputData().getInt("min", 0);
            String repeat = getInputData().getString("repeat");

            // Calculate delay until the desired time
            long initialDelay = (time - currentTime);
            if (initialDelay < 0) {
                initialDelay = 720 - time;
            }

            Log.e("doSome min here", String.valueOf(time));

            Thread.sleep(initialDelay * 60000);
            showNotification(getApplicationContext());

            if (Objects.equals(repeat, "Daily")){
                int interVal = 1440;

                // Build the periodic work request
                PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(
                        MyNotificationWork.class,
                        interVal, // Repeat interval
                        TimeUnit.MINUTES
                ).build();
                WorkManager.getInstance(getApplicationContext()).enqueue(workRequest);
            }else if (Objects.equals(repeat, "Weekly")){
                int interVal = 1440*7;

                // Build the periodic work request
                PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(
                        MyNotificationWork.class,
                        interVal, // Repeat interval
                        TimeUnit.MINUTES
                ).build();
                WorkManager.getInstance(getApplicationContext()).enqueue(workRequest);
            }else{
                return Result.success();
            }
        } catch (InterruptedException e) {
            return Result.failure();
        }
        return Result.success();
    }

    private void showNotification(Context context) {
        createNotificationChannel(context);

        Intent notificationIntent = new Intent(context, NotifyWorkManager.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel_id")
                .setSmallIcon(R.drawable.home)
                .setContentTitle("It's Time")
                .setContentText("Notify You that ... ")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify(1, builder.build());
    }

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel Name";
            String description = "Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel_id", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}