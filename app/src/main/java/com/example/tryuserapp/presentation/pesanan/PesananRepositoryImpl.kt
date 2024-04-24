package com.example.tryuserapp.presentation.pesanan

import com.example.tryuserapp.common.LIST_PESANAN_KATALIS
import com.example.tryuserapp.common.PESANAN_COLLECTION
import com.example.tryuserapp.data.DummyData
import com.example.tryuserapp.data.model.PesananModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await

class PesananRepositoryImpl(private val db : FirebaseFirestore) {
    fun getAllPesanan(): Flow<List<PesananModel>> = flowOf(DummyData.dummyPesananFlowModel)
    suspend fun insertTransaksi(pesananModel: PesananModel) {
        db.collection(PESANAN_COLLECTION).add(pesananModel).await()
    }
    suspend fun insertDaftarKatalis(
        daftarKatalis: Map<String, Int>,
        createdDocumentId : (String) -> Unit
    ) {
        createdDocumentId(db.collection(LIST_PESANAN_KATALIS).add(daftarKatalis).await().id)
    }

}