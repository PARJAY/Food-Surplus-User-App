package com.example.tryuserapp.presentation.customer

import com.example.tryuserapp.data.model.CustomerModel

sealed interface CustomerEvent {
    data class CreateCustomer(val customerId : String, val customer: CustomerModel): CustomerEvent
    data class UpdateCustomer(val customerId : String, val customer: CustomerModel): CustomerEvent
    data class GetCustomerById (val customerId : String) : CustomerEvent
    data class DeleteCustomer(val customer: CustomerModel):CustomerEvent
}