package com.example.tryuserapp.presentation.customer

import com.example.tryuserapp.presentation.pesanan.PesananSideEffects

sealed class CustomerSideEffect {
    data class ShowSnackBarMessage(val message: String) : CustomerSideEffect()

}