package com.example.tryuserapp.data.repository

import com.example.tryuserapp.common.CUSTOMER_COLLECTION
import com.example.tryuserapp.data.model.CustomerModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CustomerRepositoryImpl(private val db : FirebaseFirestore) {

    suspend fun getCustomerById(customerId: String): CustomerModel? {
        val documentSnapshot = db.collection(CUSTOMER_COLLECTION).document(customerId).get().await()
        return documentSnapshot.toObject(CustomerModel::class.java)
    }

    suspend fun addCustomer(customer: CustomerModel) {
        db.collection(CUSTOMER_COLLECTION).add(customer).await()
    }

    suspend fun updateCustomer(customerId: String, newCustomer: CustomerModel) {
        db.collection(CUSTOMER_COLLECTION).document(customerId).set(newCustomer).await()
    }

}