package com.example.tryuserapp.di

import com.example.tryuserapp.data.repository.KatalisRepositoryImpl
import com.example.tryuserapp.data.repository.UserRepositoryImpl
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.ktx.storage


class AppModuleImpl : AppModule {
    override val db = Firebase.firestore
    override val storage = com.google.firebase.ktx.Firebase.storage("gs://nofwa-indonesia.appspot.com")

    override val userRepositoryImpl: UserRepositoryImpl by lazy {
        UserRepositoryImpl(db)
    }

    override val katalisRepositoryImpl: KatalisRepositoryImpl by lazy {
        KatalisRepositoryImpl(db)
    }
}