package com.example.tryuserapp.data.repository

import android.util.Log
import com.example.tryuserapp.common.CUSTOMER_COLLECTION
import com.example.tryuserapp.data.model.CustomerModel
import com.example.tryuserapp.tools.FirebaseHelper.Companion.fetchSnapshotToCustomerModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CustomerRepositoryImpl(private val db : FirebaseFirestore) {

    suspend fun getCustomerById(customerId: String): CustomerModel {
        val documentSnapshot = db.collection(CUSTOMER_COLLECTION).document(customerId).get().await()
        Log.d("Customer Repo", "${documentSnapshot.data}")

        return fetchSnapshotToCustomerModel(documentSnapshot)
    }

    suspend fun addOrUpdateCustomer(customerId: String, newCustomer: CustomerModel) {
        db.collection(CUSTOMER_COLLECTION).document(customerId).set(newCustomer).await()
    }

}