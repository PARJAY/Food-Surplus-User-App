package com.example.tryuserapp.ui.screen

import android.content.res.Configuration
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tryuserapp.R
import com.example.tryuserapp.data.model.KatalisModel
import com.example.tryuserapp.presentation.home_screen.HomeScreenEvent
import com.example.tryuserapp.presentation.home_screen.HomeScreenSideEffects
import com.example.tryuserapp.presentation.home_screen.HomeScreenUiState
import com.example.tryuserapp.ui.component.ButtonKeranjangSmall
import com.example.tryuserapp.ui.component.ButtonPesananAnda
import com.example.tryuserapp.ui.component.Katalis
import com.example.tryuserapp.ui.component.SearchBar
import com.example.tryuserapp.ui.component.TopBar
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import com.example.tryuserapp.ui.theme.backGroundScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun HomeScreen(
    navController: NavController,
    homeScreenUiState: HomeScreenUiState,
    homeScreenEffectFlow: Flow<HomeScreenSideEffects>,
    onHomeScreenEvent: (HomeScreenEvent) -> Unit,
){
    var jumlah by remember {
        mutableStateOf(0)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backGroundScreen)
    ) {
        item {
            TopBar(navController)
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Pesan Katalis B",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                SearchBar()
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                ButtonPesananAnda(navController)
            }
            Spacer(modifier = Modifier.height(8.dp))

        }

        items(homeScreenUiState.katalisList) { katalis ->

//            if (katalis.id == homeScreenUiState.selectedKatalisList)
            Katalis(katalisModel = katalis, navController, onHomeScreenEvent, )
            Spacer(modifier = Modifier.height(5.dp))
        }
    }

    ButtonKeranjangSmall(navController)
}



@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun HomeScreenPreview() {
    TryUserAppTheme {
        HomeScreen(
            navController = rememberNavController(),
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
            onHomeScreenEvent = {}
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