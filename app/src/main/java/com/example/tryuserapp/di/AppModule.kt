package com.example.tryuserapp.di

import com.example.tryuserapp.data.repository.KatalisRepositoryImpl
import com.example.tryuserapp.data.repository.CustomerRepositoryImpl
import com.example.tryuserapp.data.repository.HotelRepositoryImpl
import com.example.tryuserapp.data.repository.PesananListRepositoryImpl
import com.example.tryuserapp.presentation.pesanan.PesananRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

interface AppModule {
    val customerRepositoryImpl : CustomerRepositoryImpl
    val katalisRepositoryImpl : KatalisRepositoryImpl
    val pesananRepositoryImpl : PesananRepositoryImpl
    val pesananListRepositoryImpl : PesananListRepositoryImpl
    val hotelRepositoryImpl : HotelRepositoryImpl
    val db : FirebaseFirestore
    val storage : FirebaseStorage
}