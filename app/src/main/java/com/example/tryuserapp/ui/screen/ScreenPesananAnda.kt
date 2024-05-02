package com.example.tryuserapp.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryuserapp.MyApp
import com.example.tryuserapp.R
import com.example.tryuserapp.data.model.PesananModel
import com.example.tryuserapp.presentation.pesanan.PesananListViewModel
import com.example.tryuserapp.presentation.pesanan.PesananState
import com.example.tryuserapp.presentation.sign_in.UserData
import com.example.tryuserapp.tools.Utility
import com.example.tryuserapp.ui.component.CheckStatusPesanan
import com.example.tryuserapp.ui.navigation.Screen

@Composable
fun PesananAnda(
    userData: UserData,
    pesananState: PesananState,
    pesananListViewModel: PesananListViewModel,
    onNavigateToDetailPesananScreen : (pesananModel : PesananModel, desiredScreen : String) -> Unit
) {
    val contex = LocalContext.current

    val listPesananAnda = remember { mutableStateListOf<PesananModel>() }

    var idCustomer : String = ""

    LaunchedEffect (Unit) {
        MyApp.appModule.pesananListRepositoryImpl.getPesananList (
            errorCallback = {
                Utility.showToast(contex, "error : $it")
                Log.d("PesananMasukScreen", "error : $it")
            },
            addDataCallback = {
                listPesananAnda.add(it)
                listPesananAnda.sortedBy { it2 -> it2.waktu_pesanan_dibuat.seconds }
                Log.d("PesananMasukScreen", "added to screen : $it")
            },
            updateDataCallback = { updatedData ->
                val index = listPesananAnda.indexOfFirst { it.id_pesanan == updatedData.id_pesanan }
                if (index != -1) listPesananAnda[index] = updatedData
                listPesananAnda.sortedBy { it.waktu_pesanan_dibuat.seconds }
                Log.d("PesananMasukScreen", "updated to screen : $updatedData")
            },
            deleteDataCallback = { documentId: String ->
                listPesananAnda.removeAll { it.id_pesanan == documentId }
                listPesananAnda.sortedBy { it.waktu_pesanan_dibuat.seconds }
                Log.d("PesananMasukScreen", "deleted from screen : id = $documentId")
            }
        )
    }

    Log.d("Cek Id Customer", "Id Customer : ${userData.userId}")
    Log.d("Cek Id Customer Di Pesanan", "Id Customer Di Pesanan : ${idCustomer}")

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(
                text = "Pesanan Anda",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 20.dp),
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
        }

        items(listPesananAnda) { pesanan ->
            if (pesanan.id_customer == userData.userId) {
                Column(
                    Modifier
                        .padding(horizontal = 8.dp)
                        .clickable {
                            onNavigateToDetailPesananScreen(
                                pesanan,
                                Screen.ScreenDetailPesananAnda.route
                            )
                        }
                ) {
                    CheckStatusPesanan(
                        statusPhoto = R.drawable.otw,
                        pesananModel = pesanan,
                        pesananListViewModel = pesananListViewModel,
                    )

                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
    }



//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun ScreenPesananAndaPreview() {
//    TryUserAppTheme {
//        Surface {
//            PesananAnda(
//                pesananState = PesananState(),
//                onNavigateToScreen = {},
//                onHomeScreenEvent = HomeScreenEvent
//            )
//        }
//    }
//}
