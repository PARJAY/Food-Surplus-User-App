package com.example.tryuserapp.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.tryuserapp.MyApp
import com.example.tryuserapp.common.LIST_PESANAN_KATALIS
import com.example.tryuserapp.data.model.DaftarKatalis
import com.example.tryuserapp.data.model.PesananModel
import com.example.tryuserapp.presentation.katalis_screen.SelectedKatalis
import com.example.tryuserapp.ui.component.RingkasanPesanan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ScreenDetailPesananAnda(
    selectedDetailPesananModel : PesananModel
) {
    Log.d("SDPA Screen", "selectedDetailPesananModel : $selectedDetailPesananModel")
    // for each List_Pesanan_Katalis id
    var daftarKatalis by remember { mutableStateOf(DaftarKatalis()) }

    LaunchedEffect(Unit) {  
        Log.d("SDPA Screen", "list_id_daftar_katalis : ${selectedDetailPesananModel.list_id_daftar_katalis}")
        daftarKatalis = MyApp.appModule.pesananRepositoryImpl.getListPesananKatalisById(selectedDetailPesananModel.list_id_daftar_katalis)
        Log.d("SDPA Screen", "list_id_daftar_katalis : $daftarKatalis")
    }

//    SelectedKatalis(
//
//    )

//    RingkasanPesanan(selectedKatalis = , hotelToUserDistance = selectedDetailPesananModel.jarak_user_dan_hotel)
}