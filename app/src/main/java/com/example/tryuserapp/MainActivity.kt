package com.example.tryuserapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tryuserapp.data.retrofit.RetrofitInstance
import com.example.tryuserapp.ui.navigation.Navigation
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TryUserAppTheme {
                // A surface container using the 'background' color from the theme

                // API Use Case
//                val apiService = RetrofitInstance.api
//                CoroutineScope(Dispatchers.IO).launch {
//                    val response = apiService.getDirections("-8.621241699999999,115.2225455", "-8.621834809775441,115.261606593286")
//                    Log.d("MainActivity", "response : $response")
//                    Log.d("MainActivity", "direction : ${response.routes[0].legs[0].distance?.text}")
//                }

                Navigation(lifecycleOwner = this)
            }
        }
    }
}