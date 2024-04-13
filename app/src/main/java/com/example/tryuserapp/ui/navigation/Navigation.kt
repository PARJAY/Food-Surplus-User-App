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

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route){
            val homeScreenVM: HomeScreenViewModel = viewModel(
                factory = viewModelFactory { HomeScreenViewModel(MyApp.appModule.katalisRepositoryImpl) }
            )
            val homeScreenVMUiState = homeScreenVM.state.collectAsState().value
            val homeScreenVMEffectFlow = homeScreenVM.effect

            HomeScreen(navController, homeScreenVMUiState, homeScreenVMEffectFlow, homeScreenVM::onEvent)
        }
        composable(Screen.ScreenDetailPesanan.route) {
            DetailPesanan(navController)
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
    }

}