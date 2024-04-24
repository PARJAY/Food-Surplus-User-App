package com.example.tryuserapp.data.retrofit

import com.example.tryuserapp.BuildConfig
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Query

interface DirectionsService {
    @GET("maps/api/directions/json")
    suspend fun getDirections(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") apiKey: String = BuildConfig.MAPS_API_KEY
    ) : DirectionsResponse
}

data class DirectionsResponse (
    @SerializedName("routes" ) var routes : ArrayList<Routes> = arrayListOf(),
    @SerializedName("status") var status : String? = null,
    @SerializedName("error_message" ) var errorMessage : String? = null
)

data class Distance (
    @SerializedName("text" ) var text  : String? = null,
    @SerializedName("value") var value : Int?    = null

)

data class Legs (
    @SerializedName("distance") var distance : Distance? = Distance()
)

data class Routes (
    @SerializedName("legs") var legs : ArrayList<Legs> = arrayListOf()
)