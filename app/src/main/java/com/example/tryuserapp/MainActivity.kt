package com.example.tryuserapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tryuserapp.ui.navigation.Navigation
import com.example.tryuserapp.ui.theme.TryUserAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TryUserAppTheme {
                // A surface container using the 'background' color from the theme

                Navigation(lifecycleOwner = this)
            }
        }
    }
}