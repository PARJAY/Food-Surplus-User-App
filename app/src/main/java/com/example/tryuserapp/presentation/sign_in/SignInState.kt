package com.example.tryuserapp.presentation.sign_in

data class SignInState (
    val isSignInSuccessful: Boolean = false,
    val userData: UserData? = UserData(),
    val signInErrorMessage: String? = null
)