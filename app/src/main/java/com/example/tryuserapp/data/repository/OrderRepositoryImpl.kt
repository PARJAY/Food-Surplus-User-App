package com.example.tryuserapp.data.repository

import android.util.Log
import com.example.tryuserapp.common.CUSTOMER_COLLECTION
import com.example.tryuserapp.data.model.CustomerModel
import com.example.tryuserapp.data.model.OrderModel
import com.example.tryuserapp.tools.FirebaseHelper.Companion.fetchSnapshotToCustomerModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class OrderRepositoryImpl(private val db : FirebaseFirestore) {

//    suspend fun getOrderById(customerId: String): OrderModel {
//        val documentSnapshot = db.collection(CUSTOMER_COLLECTION).document(customerId).get().await()
//        Log.d("Order Repo", "${documentSnapshot.data}")
//
//        return fetchSnapshotToOrderModel(documentSnapshot)
//    }

//    private fun fetchSnapshotToOrderModel(documentSnapshot: DocumentSnapshot?): OrderModel {
//        // TODO : change later
//
//        return OrderModel(
//            idUser = ,
//            idHotel = ,
//            idKurir = ,
//            totalHarga = 0f,
//
//        )
//        TODO("Not yet implemented")
//    }

    suspend fun addOrder(order: OrderModel) {
        db.collection(CUSTOMER_COLLECTION).add(order).await()
    }

}