package com.example.tryuserapp.presentation.customer

import com.example.tryuserapp.model.Customer

data class CustomerState(
    val customerListState: List<Customer> = emptyList()
)