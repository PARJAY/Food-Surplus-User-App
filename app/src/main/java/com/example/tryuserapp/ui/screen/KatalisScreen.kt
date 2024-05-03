@file:JvmName("HomeScreenKt")

package com.example.tryuserapp.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryuserapp.MyApp
import com.example.tryuserapp.common.BEGIN_QUANTITY_KATALIS
import com.example.tryuserapp.data.model.KatalisModel
import com.example.tryuserapp.presentation.katalis_screen.KatalisScreenUiState
import com.example.tryuserapp.presentation.katalis_screen.SelectedKatalis
import com.example.tryuserapp.presentation.sign_in.UserData
import com.example.tryuserapp.tools.Utility
import com.example.tryuserapp.ui.component.ButtonKeranjangSmall
import com.example.tryuserapp.ui.component.ButtonPesananAnda
import com.example.tryuserapp.ui.component.Katalis
import com.example.tryuserapp.ui.component.SearchBar
import com.example.tryuserapp.ui.component.TopBar
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import com.example.tryuserapp.ui.theme.backGroundScreen

@SuppressLint("MutableCollectionMutableState")
@Composable
fun KatalisScreen(
    userData: UserData?,
    katalisScreenUiState: KatalisScreenUiState,
    onNavigateToScreen: (String) -> Unit,
    onSetSelectedDetailKatalis: (KatalisModel) -> Unit,
    selectedKatalisList: SnapshotStateList<SelectedKatalis>,
    selectedHotelId : String
) {
    val contex = LocalContext.current

    val katalisModel = remember { mutableStateListOf<KatalisModel>() }

    LaunchedEffect (Unit) {
        MyApp.appModule.katalisRepositoryImpl.getKatalisListLiveData(
            errorCallback = {
                Utility.showToast(contex, "error : $it")
                Log.d("KatalisScreen", "error : $it")
            },
            addDataCallback = {
                katalisModel.add(it)
                Log.d("KatalisScreen", "added to screen : $it")
            },
            updateDataCallback = { updatedData ->
                val index = katalisModel.indexOfFirst { it.id == updatedData.id }
                if (index != -1) katalisModel[index] = updatedData
            },
            deleteDataCallback = { documentId: String ->
                katalisModel.removeAll { it.id == documentId }
                Log.d("PesananMasukScreen", "deleted from screen : id = $documentId")
            }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backGroundScreen)
    ) {
        item {
            TopBar(
                userData,
                onNavigateToScreen
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Pesan Katalis Baru",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                SearchBar()
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                ButtonPesananAnda(onNavigateToScreen)
            }
            Spacer(modifier = Modifier.height(8.dp))

        }

        items(katalisScreenUiState.katalisList) { katalis ->
            // TODO : katalis yang sudah kadaluarsa nggak tampil
            if (katalis.idHotel == selectedHotelId && katalis.stok != 0) {
                Katalis(
                    katalisModel = katalis,

                    onNavigateToScreen = {
                        onSetSelectedDetailKatalis(katalis)
                        onNavigateToScreen(it)
                    },

                    selectedQuantityKatalis = selectedKatalisList.find {
                        it.idKatalis == katalis.id
                    }?.quantity ?: 0,

                    onAddSelectedKatalisList = {
                        selectedKatalisList.add(
                            SelectedKatalis(
                                katalis.id, BEGIN_QUANTITY_KATALIS,
                                namaKatalis = katalis.namaKatalis,
                                hargaKatalis = katalis.hargaJual,
                                stokKatalis = katalis.stok
                            )
                        )
                        Log.d("Katalis Screen", "Added Katalis with id ${katalis.id}")
                    },
                    onModifySelectedKatalisList = { modifiedQuantityKatalis ->
                        selectedKatalisList.find { loopedKatalis ->
                            loopedKatalis.idKatalis == katalis.id
                        }?.quantity = modifiedQuantityKatalis

                        Log.d("Katalis Screen", "Modified Katalis with id ${katalis.id}. quantity to $modifiedQuantityKatalis")
                    },
                    onRemoveSelectedKatalisListById = {
                        selectedKatalisList.removeAll { it.idKatalis == katalis.id }
                        Log.d("Katalis Screen", "Removed Katalis with id ${katalis.id}")
                    },
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
    ButtonKeranjangSmall(onNavigateToScreen)
}



@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun KatlisScreenPreview() {
    TryUserAppTheme {
        KatalisScreen(
            userData = null,
            katalisScreenUiState = KatalisScreenUiState(
                katalisList = listOf(
                    KatalisModel(namaKatalis = "Ayam Goreng"),
                    KatalisModel(namaKatalis = "Mie Goreng"),
                    KatalisModel(namaKatalis = "Bakso Goreng"),
                    KatalisModel(namaKatalis = "Bakso Goreng"),
                    KatalisModel(namaKatalis = "Bakso Goreng"),
                )
            ),
            onNavigateToScreen = {  },
            onSetSelectedDetailKatalis = {

            },
            selectedKatalisList = SnapshotStateList(),
            selectedHotelId = ""
        )
    }
}