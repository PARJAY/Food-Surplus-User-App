package com.example.tryuserapp.ui.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("HomeScreen")
    object ScreenDetailPesanan : Screen("ScreenDetailPesanan")
    object ScreenCheckOut : Screen("ScreenCheckOut")
    object ScreenPesananAnda : Screen("ScreenPesananAnda")
    object ScreenProfile : Screen("ScreenProfile")
}