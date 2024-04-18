package com.example.tryuserapp.presentation.customer

import com.example.tryuserapp.data.DummyData
import com.example.tryuserapp.model.Customer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CustomerRepository {
    fun getAllCustomer(): Flow<List<Customer>> = flowOf(DummyData.dummyCustomerFlow)
    suspend fun insertCustomer(customer: Customer) {
        insertCustomer(customer)
    }
    suspend fun readCustomer(customer: Customer) {
        readCustomer(customer)
    }

    suspend fun updateCustomer(customer: Customer) {
        updateCustomer(customer)
    }

    suspend fun deleteCustomer(customer: Customer) {
        deleteCustomer(customer)
    }
}