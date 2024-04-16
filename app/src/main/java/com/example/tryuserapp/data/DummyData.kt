package com.example.tryuserapp.data

import com.example.tryuserapp.data.model.CustomerModel


class DummyData {

    companion object {

        val customer1 = CustomerModel("John Doe", "john.doe@example.com", "ABC123")

        val customer2 = CustomerModel("Jane Smith", "jane.smith@example.com", "XYZ789")

        val customer3 = CustomerModel("Michael Johnson", "michael.johnson@example.com", "DEF456")

        val dummyCustomerModelFlow: List<CustomerModel> = listOf(customer1, customer2, customer3)
    }
}