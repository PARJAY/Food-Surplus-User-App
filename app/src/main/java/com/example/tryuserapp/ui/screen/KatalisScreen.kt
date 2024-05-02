@file:JvmName("HomeScreenKt")

package com.example.tryuserapp.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tryuserapp.common.BEGIN_QUANTITY_KATALIS
import com.example.tryuserapp.data.model.KatalisModel
import com.example.tryuserapp.logic.OrderAction
import com.example.tryuserapp.presentation.katalis_screen.KatalisScreenEvent
import com.example.tryuserapp.presentation.katalis_screen.KatalisScreenUiState
import com.example.tryuserapp.presentation.katalis_screen.SelectedKatalis
import com.example.tryuserapp.presentation.sign_in.UserData
import com.example.tryuserapp.tools.FirebaseHelper.Companion.getFileFromFirebaseStorage
import com.example.tryuserapp.ui.component.ButtonKeranjangSmall
import com.example.tryuserapp.ui.component.ButtonPesananAnda
import com.example.tryuserapp.ui.component.Katalis
import com.example.tryuserapp.ui.component.SearchBar
import com.example.tryuserapp.ui.navigation.Screen
import com.example.tryuserapp.ui.theme.Brown
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import com.example.tryuserapp.ui.theme.backGroundScreen

@SuppressLint("MutableCollectionMutableState")
@Composable
fun KatalisScreen(
    userData: UserData?,
    katalisScreenUiState: KatalisScreenUiState,
    onNavigateToScreen: (String) -> Unit,
    onSetSelectedDetailKatalis: (KatalisModel) -> Unit,
    selectedKatalisList: SnapshotStateList<SelectedKatalis>
) {

    var imageURI by remember { mutableStateOf<Uri>(Uri.EMPTY) }

    getFileFromFirebaseStorage(
        fileReference = "WhatsApp Image 2023-12-09 at 20.32.55.jpeg",
        onSuccess = { imageURI = it},
        onError = {}
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backGroundScreen)
    ) {
        item {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Brown)
                .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                if (userData?.username != null) {
                    Text(
                        text = userData.username,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        color = Color.White
                    )

                    AsyncImage(
                        model = userData.profilePictureUrl,
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .clickable { onNavigateToScreen(Screen.ScreenProfile.route) },
                        contentScale = ContentScale.Crop
                    )
                }
            }

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
            Katalis(
                katalisModel = katalis,
                onNavigateToScreen = {
                    onSetSelectedDetailKatalis(katalis)
                    onNavigateToScreen(it)
                },
                selectedQuantityKatalis = (selectedKatalisList.find {
                    it.idKatalis == katalis.id
                })?.quantity ?: 0,

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
        )
//        Surface {
//            HomeScreen(
//                navController = rememberNavController(),
//                homeScreenVMUiState = HomeScreenUiState(),
//                homeScreenVMEffectFlow = flow {
//                    emit(HomeScreenSideEffects.ShowSnackBarMessage("this is a snackbar message"))
//                },
//                onHomeScreenEvent = {}
//            )
//        }
    }
}