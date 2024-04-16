package com.example.tryuserapp.data

import com.example.tryuserapp.data.model.CustomerModel


class DummyData {

    companion object {

        val customer1 = CustomerModel("1", "John Doe", "john.doe@example.com", "ABC123", )

        val customer2 = CustomerModel("2", "Jane Smith", "jane.smith@example.com", "XYZ789", )

        val customer3 = CustomerModel("3", "Michael Johnson", "michael.johnson@example.com", "DEF456")

        val dummyCustomerModelFlow: List<CustomerModel> = listOf(customer1, customer2, customer3)
    }
}