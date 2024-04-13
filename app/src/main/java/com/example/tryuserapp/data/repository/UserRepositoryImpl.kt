package com.example.tryuserapp.data.repository

import com.example.tryuserapp.common.USER_COLLECTION
import com.example.tryuserapp.data.model.UserModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(private val db : FirebaseFirestore) {

    suspend fun getUserById(userId: String): UserModel? {
        val documentSnapshot = db.collection(USER_COLLECTION).document(userId).get().await()
        return documentSnapshot.toObject(UserModel::class.java)
    }

}