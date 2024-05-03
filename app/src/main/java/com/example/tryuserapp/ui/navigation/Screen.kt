package com.example.tryuserapp.ui.navigation

sealed class Screen(val route: String) {
    object KatalisScreen : Screen("KatalisScreen")
    object ScreenDetailKatalis : Screen("ScreenDetailKatalis")
    object ScreenCheckOut : Screen("ScreenCheckOut")
    object ScreenPesananAnda : Screen("ScreenPesananAnda")
    object ScreenDetailPesananAnda : Screen("ScreenDetailPesananAnda")

    object ScreenProfile : Screen("ScreenProfile")
    object ScreenLogin : Screen("ScreenLogin")
    object ScreenLengkapiData : Screen("ScreenLengkapiData")
    object HomeScreen : Screen("HomeScreen")
    object MapsScreen : Screen("MapsScreen")
    object TrackUserLocationScreen : Screen("TrackUserLocationScreen")
    object LocationGpsScreen : Screen("LocationGpsScreen")
}