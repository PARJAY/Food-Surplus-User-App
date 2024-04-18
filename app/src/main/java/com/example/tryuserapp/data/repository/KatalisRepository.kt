package com.example.tryuserapp.data.repository

import com.example.tryuserapp.common.FirebaseResult
import com.example.tryuserapp.data.model.KatalisModel

interface KatalisRepository {
    suspend fun getKatalisList(callback: (FirebaseResult<List<KatalisModel>>) -> Unit)
}
