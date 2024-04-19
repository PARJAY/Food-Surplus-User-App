package com.example.tryuserapp.presentation.home_screen

sealed class HomeScreenSideEffect {
    data class ShowSnackBarMessage(val message: String) : HomeScreenSideEffect()

}