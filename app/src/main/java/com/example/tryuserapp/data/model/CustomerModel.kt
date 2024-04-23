package com.example.tryuserapp.data.model

data class CustomerModel (
    val id : String = "",
    val name : String = "",
    val address : String = "",
    val phone_number : String = ""
)
data class EditedCustomerModel (
    val id : String = "",
    val address : String = "",
    val phone_number : String = ""
)