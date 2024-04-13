package com.example.tryuserapp.data.repository

import com.example.dummyfirebaseauth.common.FirebaseResult
import com.example.tryuserapp.data.model.UserModel

interface UserRepository {
    suspend fun getUsers(callback: (FirebaseResult<List<UserModel>>) -> Unit)
}
