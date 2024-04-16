package com.example.tryuserapp.presentation.customer

import com.example.tryuserapp.data.model.CustomerModel

sealed interface CustomerEvent {
    data class CreateCustomer(val customer: CustomerModel): CustomerEvent
    data class UpdateCustomer(val customer: CustomerModel): CustomerEvent
    object ReadCustomer : CustomerEvent
    data class DeleteCustomer(val customer: CustomerModel):CustomerEvent
}