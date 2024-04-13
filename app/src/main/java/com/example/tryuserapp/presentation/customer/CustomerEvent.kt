package com.example.tryuserapp.presentation.customer

import com.example.tryuserapp.model.Customer

sealed interface CustomerEvent {
    data class CreateCustomer(val customer: Customer): CustomerEvent
    data class UpdateCustomer(val customer: Customer): CustomerEvent
    data class ReadCustomer(val customer: Customer):CustomerEvent
    data class DeleteCustomer(val customer: Customer):CustomerEvent
}