package com.example.tryuserapp.data.repository

import android.util.Log
import com.example.tryuserapp.common.CUSTOMER_COLLECTION
import com.example.tryuserapp.common.FirebaseResult
import com.example.tryuserapp.common.INTERNET_ISSUE
import com.example.tryuserapp.common.PESANAN_COLLECTION
import com.example.tryuserapp.data.model.CustomerModel
import com.example.tryuserapp.data.model.PesananModel
import com.example.tryuserapp.tools.FirebaseHelper
import com.example.tryuserapp.tools.FirebaseHelper.Companion.fetchSnapshotToPesananModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.tasks.await

class PesananListRepositoryImpl (private val db : FirebaseFirestore){
    private var listenerRegistration: ListenerRegistration? = null
    fun getPesananList(
        errorCallback: (Exception) -> Unit,
        addDataCallback: (PesananModel) -> Unit,
        updateDataCallback: (PesananModel) -> Unit,
        deleteDataCallback: (documentId: String) -> Unit
    ) {
        listenerRegistration = db.collection(PESANAN_COLLECTION).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                errorCallback(IllegalStateException(INTERNET_ISSUE))
                return@addSnapshotListener
            }

            snapshot!!.documentChanges.forEach { change ->
                val pesananModel = fetchSnapshotToPesananModel(change.document)
                when (change.type) {
                    DocumentChange.Type.ADDED -> addDataCallback(pesananModel)
                    DocumentChange.Type.MODIFIED -> updateDataCallback(pesananModel)
                    DocumentChange.Type.REMOVED -> deleteDataCallback(change.document.id)
                }

                Log.d("Pesanan Repo: ", "Data In -> ${change.type} - ${change.document}")
            }
        }
    }


    fun updateStatusPesanan(pesananId: String, fieldToUpdate: String, newValue: String) {
        db.collection(PESANAN_COLLECTION).document(pesananId).update(fieldToUpdate, newValue)
    }

    // dipake kalau nggak pengen nerima data realtime lagi
    fun detachListener() {
        listenerRegistration?.remove()
        listenerRegistration = null
    }


}