package com.example.tryuserapp.data.repository

import com.example.tryuserapp.common.FirebaseResult
import com.example.tryuserapp.data.model.HotelModel

interface HotelRepository {
        suspend fun getHotelList(callback: (FirebaseResult<List<HotelModel>>) -> Unit)

}