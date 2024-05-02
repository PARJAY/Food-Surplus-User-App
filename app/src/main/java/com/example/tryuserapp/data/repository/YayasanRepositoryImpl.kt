package com.example.tryuserapp.data.repository

import android.util.Log
import com.example.tryuserapp.common.INTERNET_ISSUE
import com.example.tryuserapp.common.YAYASAN_COLLECTION
import com.example.tryuserapp.data.model.YayasanModel
import com.example.tryuserapp.tools.FirebaseHelper
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class YayasanRepositoryImpl(private val db : FirebaseFirestore) {
    private var listenerRegistration: ListenerRegistration? = null
    fun getYayasanList(
        errorCallback: (Exception) -> Unit,
        addDataCallback: (YayasanModel) -> Unit,
        updateDataCallback: (YayasanModel) -> Unit,
        deleteDataCallback: (documentId: String) -> Unit
    ) {
        listenerRegistration = db.collection(YAYASAN_COLLECTION).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                errorCallback(IllegalStateException(INTERNET_ISSUE))
                return@addSnapshotListener
            }

            snapshot!!.documentChanges.forEach { change ->
                val yayasanModel = FirebaseHelper.fetchSnapshotToYayasanModel(change.document)
                when (change.type) {
                    DocumentChange.Type.ADDED -> addDataCallback(yayasanModel)
                    DocumentChange.Type.MODIFIED -> updateDataCallback(yayasanModel)
                    DocumentChange.Type.REMOVED -> deleteDataCallback(change.document.id)
                }

                Log.d("Yayasan Repo: ", "Data In -> ${change.type} - ${change.document}")
            }
        }
    }
}