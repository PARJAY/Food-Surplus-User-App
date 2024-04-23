package com.example.tryuserapp.presentation.customer

import com.example.tryuserapp.data.model.CustomerModel

data class CustomerState(
    val isLoading: Boolean = false,
    val customerState: CustomerModel = CustomerModel(),
    val errorMessage: String? = null,

)