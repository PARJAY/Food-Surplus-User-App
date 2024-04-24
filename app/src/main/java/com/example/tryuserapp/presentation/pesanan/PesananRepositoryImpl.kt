package com.example.tryuserapp.presentation.pesanan

import android.util.Log
import com.example.tryuserapp.common.CUSTOMER_COLLECTION
import com.example.tryuserapp.common.LIST_PESANAN_KATALIS
import com.example.tryuserapp.common.PESANAN_COLLECTION
import com.example.tryuserapp.data.DummyData
import com.example.tryuserapp.data.model.CustomerModel
import com.example.tryuserapp.data.model.DaftarKatalis
import com.example.tryuserapp.data.model.Pesanan
import com.example.tryuserapp.tools.FirebaseHelper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await

class PesananRepositoryImpl(private val db : FirebaseFirestore) {
    fun getAllPesanan(): Flow<List<Pesanan>> = flowOf(DummyData.dummyPesananFlow)
    suspend fun insertTransaksi(pesanan: Pesanan) {
        db.collection(PESANAN_COLLECTION).add(pesanan).await()
    }
    suspend fun insertDaftarKatalis(daftarKatalis: DaftarKatalis) {
        db.collection(LIST_PESANAN_KATALIS).add(daftarKatalis).await()
    }

}