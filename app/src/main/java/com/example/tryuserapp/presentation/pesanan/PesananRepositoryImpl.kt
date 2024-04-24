package com.example.tryuserapp.presentation.pesanan

import com.example.tryuserapp.common.LIST_PESANAN_KATALIS
import com.example.tryuserapp.common.PESANAN_COLLECTION
import com.example.tryuserapp.data.DummyData
import com.example.tryuserapp.data.model.DaftarKatalis
import com.example.tryuserapp.data.model.Pesanan
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await

class PesananRepositoryImpl(private val db : FirebaseFirestore) {
    fun getAllPesanan(): Flow<List<Pesanan>> = flowOf(DummyData.dummyPesananFlow)
    suspend fun insertTransaksi(pesanan: Pesanan) {
        db.collection(PESANAN_COLLECTION).add(pesanan).await()
    }
    suspend fun insertDaftarKatalis(
        daftarKatalis: Map<String, Int>,
        createdDocumentId : (String) -> Unit
    ) {
        createdDocumentId(db.collection(LIST_PESANAN_KATALIS).add(daftarKatalis).await().id)
    }

}