package com.example.tryuserapp.presentation.sing_in

data class SignInState (
    val isSignInSuccessful: Boolean = false,
    val signInErrorMessage: String? = null
)