package com.example.nexvia


import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.nexvia.navigation.AppNavHost
import com.example.nexvia.ui.theme.NexviaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NexviaTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}
