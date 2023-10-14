package com.example.notificationandroid

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import kotlin.coroutines.coroutineContext

class CounterNotificationService(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    fun showNotification(counter: Int, notificationId: Int) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
            )
        val incrementIntent = Intent(context, CounterNotificationReceiver::class.java)
        val incrementPendingIntent = PendingIntent.getBroadcast(
            context,
            2,
            incrementIntent,
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )
        val notification = NotificationCompat.Builder(context, COUNTER_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Increment Counter")
            .setContentText("The Counter is $counter")
            .setContentIntent(activityPendingIntent)
            .setOngoing(true)
            .addAction(
                R.drawable.ic_launcher_foreground,
                "Increment",
                incrementPendingIntent
            ).build()
        notificationManager.notify(notificationId, notification)
    }

    fun cancleNotification(id: Int) {
        notificationManager.cancel(id)
    }

    companion object {
        const val COUNTER_CHANNEL_ID = "counter_channel"
    }
}