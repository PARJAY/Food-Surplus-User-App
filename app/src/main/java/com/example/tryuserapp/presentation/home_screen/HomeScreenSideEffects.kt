package com.example.tryuserapp.presentation.home_screen

sealed class HomeScreenSideEffects {
    data class ShowSnackBarMessage(val message: String) : HomeScreenSideEffects()
}