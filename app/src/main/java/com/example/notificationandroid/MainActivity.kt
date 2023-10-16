package com.example.notificationandroid

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.notificationandroid.ui.theme.NotificationAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!hasPermission(android.Manifest.permission.POST_NOTIFICATIONS)) {
                requestManyPermission(
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    0
                )
            }
        }
        setContent {
            NotificationAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(contentAlignment = Alignment.Center){
                        Text(text = "Turn down status bar to see push notification.")
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