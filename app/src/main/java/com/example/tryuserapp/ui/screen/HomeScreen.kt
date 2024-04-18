package com.example.tryuserapp.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
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
import com.example.tryuserapp.data.model.KatalisModel
import com.example.tryuserapp.logic.OrderAction
import com.example.tryuserapp.presentation.home_screen.HomeScreenEvent
import com.example.tryuserapp.presentation.home_screen.HomeScreenSideEffects
import com.example.tryuserapp.presentation.home_screen.HomeScreenUiState
import com.example.tryuserapp.presentation.home_screen.SelectedKatalis
import com.example.tryuserapp.presentation.sign_in.UserData
import com.example.tryuserapp.ui.component.ButtonKeranjangSmall
import com.example.tryuserapp.ui.component.ButtonPesananAnda
import com.example.tryuserapp.ui.component.Katalis
import com.example.tryuserapp.ui.component.SearchBar
import com.example.tryuserapp.ui.navigation.Screen
import com.example.tryuserapp.ui.theme.Brown
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import com.example.tryuserapp.ui.theme.backGroundScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@SuppressLint("MutableCollectionMutableState")
@Composable
fun HomeScreen(
    userData: UserData?,
    homeScreenUiState: HomeScreenUiState,
    homeScreenEffectFlow: Flow<HomeScreenSideEffects>,
    onHomeScreenEvent: (HomeScreenEvent) -> Unit,
    onNavigateToScreen : (String) -> Unit,
    onSetSelectedDetailKatalis : (KatalisModel) -> Unit,
    selectedKatalisList : ArrayList<SelectedKatalis>,
    onModifyQuantity: (katalisId : String, OrderAction) -> Unit,
) {

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
                text = "Pesan Katalis B",
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

//        homeScreenUiState.katalisList.forEach { katalis ->
//            item {
//                Katalis(
//                    katalisModel = katalis,
//                    onNavigateToScreen = {
//                        onSetSelectedDetailKatalis(katalis)
//                        onNavigateToScreen(it)
//                    },
//                    onHomeScreenEvent,
//                    selectedQuantityKatalis = (selectedKatalisList.find {
//                        it.idKatalis == katalis.id
//                    })?.quantity ?: 0,
//                    onModifyQuantity = onModifyQuantity
//                )
//                Spacer(modifier = Modifier.height(5.dp))
//            }
//        }

        items(homeScreenUiState.katalisList) { katalis ->
            Katalis(
                katalisModel = katalis,
                onNavigateToScreen = {
                    onSetSelectedDetailKatalis(katalis)
                    onNavigateToScreen(it)
                },
                onHomeScreenEvent,
                selectedQuantityKatalis = (selectedKatalisList.find {
                    it.idKatalis == katalis.id
                })?.quantity ?: 0,
                onModifyQuantity = onModifyQuantity
            )
            Spacer(modifier = Modifier.height(5.dp))
        }
    }

    ButtonKeranjangSmall(onNavigateToScreen)
}



@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun HomeScreenPreview() {
    TryUserAppTheme {
        HomeScreen(
            onNavigateToScreen = {  },
            homeScreenUiState = HomeScreenUiState(
                katalisList = listOf(
                    KatalisModel(namaKatalis = "Ayam Goreng"),
                    KatalisModel(namaKatalis = "Mie Goreng"),
                    KatalisModel(namaKatalis = "Bakso Goreng"),
                    KatalisModel(namaKatalis = "Bakso Goreng"),
                    KatalisModel(namaKatalis = "Bakso Goreng"),
                )
            ),
            homeScreenEffectFlow = flow {
                emit(HomeScreenSideEffects.ShowSnackBarMessage("this is a snackbar message"))
            },
            onHomeScreenEvent = {},
            userData = null,
            onSetSelectedDetailKatalis = {

            },
            selectedKatalisList = arrayListOf(),
            onModifyQuantity = { katalisId, orderAction ->

            }
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