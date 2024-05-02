package com.example.tryuserapp.data.repository

import com.example.tryuserapp.common.FirebaseResult
import com.example.tryuserapp.data.model.PesananModel
import com.example.tryuserapp.data.model.YayasanModel

interface YayasanRepository {
    suspend fun getYayasanList(callback: (FirebaseResult<List<YayasanModel>>) -> Unit, idYayasan : String)
}