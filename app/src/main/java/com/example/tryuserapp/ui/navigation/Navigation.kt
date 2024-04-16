package com.example.tryuserapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tryuserapp.MyApp
import com.example.tryuserapp.presentation.home_screen.HomeScreenViewModel
import com.example.tryuserapp.presentation.viewModelFactory
import com.example.tryuserapp.ui.screen.DetailPesanan
import com.example.tryuserapp.ui.screen.HomeScreen
import com.example.tryuserapp.ui.screen.PesananAnda
import com.example.tryuserapp.ui.screen.ProfileScreen
import com.example.tryuserapp.ui.screen.ScreenCheckOut
import com.example.tryuserapp.ui.screen.ScreenLengkapiData
import com.example.tryuserapp.ui.screen.ScreenLogin

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.ScreenLogin.route) {
        composable(Screen.HomeScreen.route){
            val homeScreenVM: HomeScreenViewModel = viewModel(
                factory = viewModelFactory { HomeScreenViewModel(MyApp.appModule.katalisRepositoryImpl) }
            )
            val homeScreenVMUiState = homeScreenVM.state.collectAsState().value
            val homeScreenVMEffectFlow = homeScreenVM.effect

            HomeScreen(navController, homeScreenVMUiState, homeScreenVMEffectFlow, homeScreenVM::onEvent)
        }
        composable(Screen.ScreenDetailPesanan.route) {
            DetailPesanan("1. Sayuran segar seperti wortel, kembang kol, brokoli, kacang polong, jamur, sawi hijau, dan buncis.\n" +
                    "2. Daging ayam, sapi, atau udang, yang biasanya dipotong kecil-kecil.\n" +
                    "3. Bawang putih dan bawang merah, yang diiris tipis atau dicincang.\n" +
                    "4. Saos tiram, kecap manis, kecap asin, dan saos cabai, untuk memberikan rasa dan aroma khas.\n" +
                    "5. Minyak sayur untuk menumis bahan-bahan tersebut.\n" +
                    "6. Garam, lada, dan gula, untuk menyesuaikan rasa sesuai selera.\n" +
                    "7. Tepung maizena atau tepung terigu, untuk mengentalkan saus jika diperlukan.",navController)
        }
        composable(Screen.ScreenCheckOut.route) {
            ScreenCheckOut(navController)
        }
        composable(Screen.ScreenPesananAnda.route) {
            PesananAnda(navController)
        }
        composable(Screen.ScreenProfile.route) {
            ProfileScreen(navController)
        }
        composable(Screen.ScreenLogin.route) {
            ScreenLogin(navController)
        }
        composable(Screen.ScreenLengkapiData.route) {
            ScreenLengkapiData(navController)
        }
    }

}