package com.example.tryuserapp.data

import com.example.tryuserapp.model.Customer


class DummyData {

    companion object {

        val customer1 = Customer(1, "John Doe", "john.doe@example.com", "ABC123", )

        val customer2 = Customer(2, "Jane Smith", "jane.smith@example.com", "XYZ789", )

        val customer3 = Customer(3, "Michael Johnson", "michael.johnson@example.com", "DEF456")

        val dummyCustomerFlow: List<Customer> = listOf(customer1, customer2, customer3)
    }
}