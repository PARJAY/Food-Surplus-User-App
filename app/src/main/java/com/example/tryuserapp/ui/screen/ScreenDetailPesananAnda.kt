package com.example.tryuserapp.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.tryuserapp.MyApp
import com.example.tryuserapp.data.model.DaftarKatalis
import com.example.tryuserapp.data.model.PesananModel
import com.example.tryuserapp.presentation.katalis_screen.SelectedKatalis
import com.example.tryuserapp.ui.component.RingkasanPesanan

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ScreenDetailPesananAnda(
    selectedDetailPesananModel : PesananModel
) {
    Log.d("SDPA Screen", "selectedDetailPesananModel : $selectedDetailPesananModel")

    // for each List_Pesanan_Katalis id
    val selectedKatalisList = remember { mutableStateListOf<SelectedKatalis>() }

    LaunchedEffect(Unit) {
        Log.d("SDPA Screen", "list_id_daftar_katalis : ${selectedDetailPesananModel.daftarKatalis}")

        selectedDetailPesananModel.daftarKatalis.forEach { (key, value) ->
            val katalisModel = MyApp.appModule.katalisRepositoryImpl.getKatalisById(key)
            selectedKatalisList.add(
                SelectedKatalis(
                    idKatalis = katalisModel.id,
                    quantity = value,
                    stokKatalis = katalisModel.stok,
                    namaKatalis = katalisModel.namaKatalis,
                    hargaKatalis = katalisModel.hargaJual
                )
            )
            Log.d("SDPA Screen", "added katalisModel : $katalisModel")
        }

        Log.d("SDPA Screen", "selectedKatalisList final: $selectedKatalisList")
    }
    
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        RingkasanPesanan(
            selectedKatalis = selectedKatalisList,
            hotelToUserDistance = selectedDetailPesananModel.jarak_user_dan_hotel,
            hotelToYayasanDistanceInMeter = selectedDetailPesananModel.jarak_user_dan_hotel
        )
    }
}