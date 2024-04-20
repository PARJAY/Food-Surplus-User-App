package com.example.tryuserapp.data.repository

import android.util.Log
import com.example.tryuserapp.common.FirebaseResult
import com.example.tryuserapp.common.INTERNET_ISSUE
import com.example.tryuserapp.common.KATALIS_COLLECTION
import com.example.tryuserapp.data.model.CustomerModel
import com.example.tryuserapp.data.model.KatalisModel
import com.example.tryuserapp.tools.FirebaseHelper
import com.example.tryuserapp.tools.FirebaseHelper.Companion.fetchSnapshotToKatalisModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QueryDocumentSnapshot
import kotlinx.coroutines.tasks.await

class KatalisRepositoryImpl(private val db : FirebaseFirestore) : KatalisRepository {

    private var listenerRegistration : ListenerRegistration? = null

    override suspend fun getKatalisList(callback: (FirebaseResult<List<KatalisModel>>) -> Unit) {
        val katalisModelSnapshots = mutableListOf<KatalisModel>()

        listenerRegistration = db.collection(KATALIS_COLLECTION).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                callback(FirebaseResult.Failure(IllegalStateException(INTERNET_ISSUE)))
                return@addSnapshotListener
            }

            snapshot!!.documentChanges.forEach { change ->
                val katalisModel = fetchSnapshotToKatalisModel(change.document)
                when (change.type) {
                    DocumentChange.Type.ADDED -> katalisModelSnapshots.add(katalisModel)
                    DocumentChange.Type.MODIFIED -> {
                        val index = katalisModelSnapshots.indexOfFirst { it.id == change.document.id }
                        if (index != -1) katalisModelSnapshots[index] = katalisModel
                    }

                    DocumentChange.Type.REMOVED -> katalisModelSnapshots.removeAll { it.id == change.document.id }
                }
                Log.d("REPOSITORY: ", "Data In -> ${change.type} - ${change.document}")
            }

            callback(FirebaseResult.Success(katalisModelSnapshots))
        }
    }


    suspend fun getKatalisById(katalisId: String): KatalisModel {
        val documentSnapshot = db.collection(KATALIS_COLLECTION).document().get().await()
        Log.d("Get Katalis By Id", "${documentSnapshot.data}")

        return FirebaseHelper.fetchSnapshotToKatalisModel(documentSnapshot)
    }

    suspend fun addOrUpdateKatalis(katalisId: String, newStok: KatalisModel) {
        db.collection(KATALIS_COLLECTION).document(katalisId).set(newStok).await()
    }


    // dipake kalau nggak pengen nerima data realtime lagi
    fun detachListener() {
        listenerRegistration?.remove()
        listenerRegistration = null
    }
}