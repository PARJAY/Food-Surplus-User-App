package com.example.tryuserapp.data.repository

import android.util.Log
import com.example.tryuserapp.common.FirebaseResult
import com.example.tryuserapp.common.HOTEL_COLLECTION
import com.example.tryuserapp.common.INTERNET_ISSUE
import com.example.tryuserapp.data.model.HotelModel
import com.example.tryuserapp.tools.FirebaseHelper
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class HotelRepositoryImpl (private val db : FirebaseFirestore) : HotelRepository {

    private var listenerRegistration : ListenerRegistration? = null

    override suspend fun getHotelList(callback: (FirebaseResult<List<HotelModel>>) -> Unit) {
        val hotelModelSnapshots = mutableListOf<HotelModel>()

        listenerRegistration = db.collection(HOTEL_COLLECTION).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                callback(FirebaseResult.Failure(IllegalStateException(INTERNET_ISSUE)))
                return@addSnapshotListener
            }

            snapshot!!.documentChanges.forEach { change ->
                val hotelModel = FirebaseHelper.fetchSnapshotToHotelModel(change.document)
                when (change.type) {
                    DocumentChange.Type.ADDED -> hotelModelSnapshots.add(hotelModel)
                    DocumentChange.Type.MODIFIED -> {
                        val index = hotelModelSnapshots.indexOfFirst { it.idHotel == change.document.id }
                        if (index != -1) hotelModelSnapshots[index] = hotelModel
                    }

                    DocumentChange.Type.REMOVED -> hotelModelSnapshots.removeAll { it.idHotel == change.document.id }
                }
                Log.d("REPOSITORY: ", "Data In -> ${change.type} - ${change.document}")
            }

            callback(FirebaseResult.Success(hotelModelSnapshots))
        }
    }

    // dipake kalau nggak pengen nerima data realtime lagi
    fun detachListener() {
        listenerRegistration?.remove()
        listenerRegistration = null
    }
}