package com.example.tryuserapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PesananAnda(navController: NavController){
    Column {
        Text(text = "Screen Pesanan anda")
        Button(onClick = { navController.navigate("HomeScreen") }) {
            Text(text = "Back")
        }
        Button(onClick = {}) {
            Text(text = "Konfirmasi Pesanan")
        }
    }

}