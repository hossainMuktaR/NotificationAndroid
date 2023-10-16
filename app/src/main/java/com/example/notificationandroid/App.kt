package com.example.notificationandroid

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.messaging.FirebaseMessaging

class App: Application() {
    private val TAG = "applicationTag"
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        instantiateFirebaseMessaging()
    }
    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "pushNotificationChannel",
                "pushNotification",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "This Channel used for counter related notification"
            }
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun instantiateFirebaseMessaging() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {task ->
            if(!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }
            val token = task.result
            val msg = "Your firebase messaging token: $token"
            Log.d(TAG, msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        }
    }
}