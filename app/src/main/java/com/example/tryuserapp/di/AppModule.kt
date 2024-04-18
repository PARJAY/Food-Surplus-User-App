package com.example.tryuserapp.di

import com.example.tryuserapp.data.repository.KatalisRepositoryImpl
import com.example.tryuserapp.data.repository.CustomerRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

interface AppModule {
    val customerRepositoryImpl : CustomerRepositoryImpl
    val katalisRepositoryImpl : KatalisRepositoryImpl
    val db : FirebaseFirestore
    val storage : FirebaseStorage
}