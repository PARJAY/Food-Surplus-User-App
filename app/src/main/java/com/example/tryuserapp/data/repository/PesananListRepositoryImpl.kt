package com.example.tryuserapp.data.repository

import android.util.Log
import com.example.tryuserapp.common.CUSTOMER_COLLECTION
import com.example.tryuserapp.common.FirebaseResult
import com.example.tryuserapp.common.INTERNET_ISSUE
import com.example.tryuserapp.common.PESANAN_COLLECTION
import com.example.tryuserapp.data.model.CustomerModel
import com.example.tryuserapp.data.model.PesananModel
import com.example.tryuserapp.tools.FirebaseHelper
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.tasks.await

class PesananListRepositoryImpl (private val db : FirebaseFirestore) : PesananListRepository{
    private var listenerRegistration : ListenerRegistration? = null

    override suspend fun getPesananList(callback: (FirebaseResult<List<PesananModel>>) -> Unit, idCustomer : String) {
        val pesananModelSnapshots = mutableListOf<PesananModel>()

        listenerRegistration = db.collection(PESANAN_COLLECTION).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                callback(FirebaseResult.Failure(IllegalStateException(INTERNET_ISSUE)))
                return@addSnapshotListener
            }

            snapshot!!.documentChanges.forEach { change ->
                val pesananModel = FirebaseHelper.fetchSnapshotToPesananModel(change.document)

                if (pesananModel.id_pesanan == idCustomer) {
                    Log.d("REPOSITORY: ", "pesananModel passed -> $pesananModel")
                    when (change.type) {
                        DocumentChange.Type.ADDED -> pesananModelSnapshots.add(pesananModel)
                        DocumentChange.Type.MODIFIED -> {
                            val index = pesananModelSnapshots.indexOfFirst { it.id_customer == change.document.id }
                            if (index != -1) pesananModelSnapshots[index] = pesananModel
                        }

                        DocumentChange.Type.REMOVED -> pesananModelSnapshots.removeAll { it.id_customer == change.document.id }
                    }
                }

                Log.d("REPOSITORY: ", "Data In -> ${change.type} - ${change.document}")
            }

            callback(FirebaseResult.Success(pesananModelSnapshots))
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