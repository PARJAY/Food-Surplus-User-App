package com.example.tryuserapp.ui.navigation

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

    NavHost(navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(Screen.ScreenDetailPesanan.route) {
            DetailPesanan(navController)
        }
        composable(Screen.ScreenCheckOut.route) {
            ScreenCheckOut(navController)
        }
        composable(Screen.ScreenPesananAnda.route) {
            PesananAnda(navController)
        }
        composable(Screen.ScreenProfile.route) {
            ProfileScreen(navController)
        }
    }

}