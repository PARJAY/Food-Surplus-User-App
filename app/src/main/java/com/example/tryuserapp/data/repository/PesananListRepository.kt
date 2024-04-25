package com.example.tryuserapp.data.repository

import com.example.tryuserapp.common.FirebaseResult
import com.example.tryuserapp.data.model.PesananModel

interface PesananListRepository {
    suspend fun getPesananList(callback: (FirebaseResult<List<PesananModel>>) -> Unit, idCustomer : String)
}