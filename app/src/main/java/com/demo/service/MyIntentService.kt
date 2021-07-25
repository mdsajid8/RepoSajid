package com.demo.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import com.demo.R


class MyIntentService  : IntentService("MyIntentService") {

    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager
    lateinit var builder: Notification.Builder
    private val channelId = "12345"
    private val description = "Test Notification"

    override fun onHandleIntent(intent: Intent?) {
        intent?.apply {
            when (intent.action) {
                ACTION_SEND_TEST_MESSAGE -> {
                    val message = getStringExtra(EXTRA_MESSAGE)
//                    println(message)
                    if (message != null) {
                        notifyHere(message)
                    }
                }
            }
        }
    }

    companion object {
        const val ACTION_SEND_TEST_MESSAGE = "ACTION_SEND_TEST_MESSAGE"
        const val EXTRA_MESSAGE = "EXTRA_MESSAGE"
    }

    fun notifyHere(message: String){
        val intent = Intent(this, LauncherActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val soundUri: Uri = Uri.parse("android.resource://" + getApplicationContext()
                .getPackageName().toString() + "/" + R.raw.feeling_happy)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.lightColor = Color.BLUE
            notificationChannel.enableVibration(true)
            if (soundUri != null) {
                val audioAttributes = AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .build()
                notificationChannel.setSound(soundUri, audioAttributes)
            }
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
            builder = Notification.Builder(this, channelId).setContentTitle("Meal reminder ").setContentText(""+message).setSmallIcon(R.drawable.notifications).setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable
                    .ic_launcher_background)).setContentIntent(pendingIntent)
        }
        notificationManager.notify(12345, builder.build())

    }
}