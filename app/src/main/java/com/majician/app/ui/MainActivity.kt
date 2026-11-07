package com.majician.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.majician.app.shizuku.ShizukuManager
import com.majician.app.ui.screens.HomeScreen
import com.majician.app.ui.theme.MajicianTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ShizukuManager.init(applicationContext)
        enableEdgeToEdge()
        setContent {
            MajicianTheme {
                HomeScreen()
            }
        }
    }
}
