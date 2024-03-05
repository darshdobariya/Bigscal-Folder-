package com.example.test2.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.test2.R

class ActivityNotification : AppCompatActivity() {

    private val CHANNEL_ID = "channel_id"
    private val CHANNEL_NAME = "Channel Name"
    private val CHANNEL_DESCRIPTION = "Channel Description"
    private lateinit var btnNotificationSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        btnNotificationSend = findViewById(R.id.btnNotificationSend)

        btnNotificationSend.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                    description = CHANNEL_DESCRIPTION
                }
                val nManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                nManager.createNotificationChannel(channel)
            }

            // Create Intent for PendingIntent
            val intent1 = Intent(Intent.ACTION_VIEW)
            intent1.data = Uri.parse("https://www.geeksforgeeks.org/")

            val pendingIntent1 = PendingIntent.getActivity(this, 5, intent1, PendingIntent.FLAG_UPDATE_CURRENT)

            val imgBitmap = BitmapFactory.decodeResource(resources, R.drawable.housekeeper)

            val nBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Notification Title")
                .setContentText("Notification Content")
                .setSmallIcon(R.drawable.home)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setLargeIcon(imgBitmap)
                .setStyle(NotificationCompat.BigPictureStyle()
                    .bigPicture(imgBitmap)
                    .bigLargeIcon(imgBitmap))
                .setContentIntent(pendingIntent1)
                .setNumber(10)
                .setAutoCancel(true)
                .build()

            val notificationManagerCompat = NotificationManagerCompat.from(this)
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                return@setOnClickListener
            }
            notificationManagerCompat.notify(123, nBuilder)
        }
    }
}
