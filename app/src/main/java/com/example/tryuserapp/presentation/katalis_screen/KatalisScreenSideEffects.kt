package com.example.tryuserapp.presentation.katalis_screen

sealed class KatalisScreenSideEffects {
    data class ShowSnackBarMessage(val message: String) : KatalisScreenSideEffects()
}