package com.example.tryuserapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tryuserapp.ui.screen.DetailPesanan
import com.example.tryuserapp.ui.screen.HomeScreen
import com.example.tryuserapp.ui.screen.PesananAnda
import com.example.tryuserapp.ui.screen.ProfileScreen
import com.example.tryuserapp.ui.screen.ScreenCheckOut

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "HomeScreen") {
        composable("HomeScreen"){
            HomeScreen(navController = navController)
        }
        composable("screenDetailPesanan") {
            DetailPesanan(navController)
        }
        composable("screenCheckOut") {
            ScreenCheckOut(navController)
        }
        composable("screenPesananAnda") {
            PesananAnda(navController)
        }
        composable("screenProfile") {
            ProfileScreen(navController)
        }
    }

}