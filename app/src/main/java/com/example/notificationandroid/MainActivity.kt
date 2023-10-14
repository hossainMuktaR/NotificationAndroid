package com.example.notificationandroid

import android.Manifest
import android.content.pm.PackageManager
import android.media.tv.AdRequest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.notificationandroid.ui.theme.NotificationAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!hasPermission(Manifest.permission.POST_NOTIFICATIONS)) {
            requestManyPermission(
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }
        val counterNotifService = CounterNotificationService(applicationContext)
        setContent {
            NotificationAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = {
                            counterNotifService.showNotification(Counter.value, Counter.CounterNotificationId)
                        }) {
                            Text(text = "Show notification")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = {
                            counterNotifService.cancleNotification(Counter.CounterNotificationId)
                        }) {
                            Text(text = "Hide notification")
                        }
                    }
                }
            }
        }
    }
    private fun hasPermission(permissionName: String): Boolean {
        return ActivityCompat.checkSelfPermission(
             this,
             permissionName
         ) == PackageManager.PERMISSION_GRANTED
    }
    private fun requestManyPermission(arrayOfPermissions: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(
            this,
            arrayOfPermissions,
            requestCode
        )
    }
}