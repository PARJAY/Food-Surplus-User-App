package com.example.tryuserapp.ui.navigation

sealed class Screen(val route: String) {
    object KatalisScreen : Screen("KatalisScreen")
    object ScreenDetailPesanan : Screen("ScreenDetailPesanan")
    object ScreenCheckOut : Screen("ScreenCheckOut")
    object ScreenPesananAnda : Screen("ScreenPesananAnda")
    object ScreenProfile : Screen("ScreenProfile")
    object ScreenLogin : Screen("ScreenLogin")
    object ScreenLengkapiData : Screen("ScreenLengkapiData")
    object HomeScreen : Screen("HomeScreen")
}