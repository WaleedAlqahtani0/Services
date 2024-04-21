package com.example.services

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.services.ui.theme.ServicesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        checkPostNav()
        setContent {
            ServicesTheme {
                val context = LocalContext.current
                StartService(context = this)

            }
        }
    }

    private fun checkPostNav() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                1
            )
        }
    }
}

@Composable
fun StartService(context: Context) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        val serviceIntent = Intent(context, TestService::class.java)
        Button(onClick = {
            serviceIntent.action = START_SERVICE
            context.startService(serviceIntent)
        }) {
            Text(text = "Start Service")
        }
        Button(onClick = {
            serviceIntent.action = STOP_SERVICE
            context.stopService(serviceIntent)
        }) {
            Text(text = "Stop Service")
        }
    }
}

