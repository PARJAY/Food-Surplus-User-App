package com.example.tryuserapp.ui.navigation

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tryuserapp.MyApp
import com.example.tryuserapp.data.model.CustomerModel
import com.example.tryuserapp.presentation.customer.CustomerViewModel
import com.example.tryuserapp.presentation.home_screen.HomeScreenViewModel
import com.example.tryuserapp.presentation.sign_in.GoogleAuthUiClient
import com.example.tryuserapp.presentation.sign_in.SignInViewModel
import com.example.tryuserapp.presentation.viewModelFactory
import com.example.tryuserapp.ui.screen.DetailPesanan
import com.example.tryuserapp.ui.screen.HomeScreen
import com.example.tryuserapp.ui.screen.PesananAnda
import com.example.tryuserapp.ui.screen.ProfileScreen
import com.example.tryuserapp.ui.screen.ScreenCheckOut
import com.example.tryuserapp.ui.screen.ScreenLengkapiData
import com.example.tryuserapp.ui.screen.ScreenLogin
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

@Composable
fun Navigation(lifecycleOwner: LifecycleOwner) {
    val context = LocalContext.current

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    val lifecycleScope = lifecycleOwner.lifecycleScope

    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.ScreenLogin.route) {
        composable(Screen.ScreenLogin.route){
            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit) {
                if (googleAuthUiClient.getSignedInUser() != null)
                    navController.navigate(Screen.HomeScreen.route)
            }
            
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == ComponentActivity.RESULT_OK) {
                        lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (!state.isSignInSuccessful) return@LaunchedEffect

                Toast.makeText(context, "Sign in Success", Toast.LENGTH_LONG).show()

                val userData = viewModel.state.value.userData
                userData?.let { fetchedUser ->
                    val existingUser = MyApp.appModule.customerRepositoryImpl.getCustomerById(fetchedUser.userId)
                    Log.d("NAVIGATION : ", "logged in user firebase data : $existingUser")

                    if (existingUser.name.isEmpty()) {
                        val newUser = CustomerModel(
                            fetchedUser.userId,
                            fetchedUser.username ?: ""
                        )

                        try {
                            MyApp.appModule.customerRepositoryImpl.addOrUpdateCustomer(fetchedUser.userId, newUser)
                            Log.d("NAVIGATION : ", "registering user success")
                        } catch (e: Exception) {
                            Log.d("NAVIGATION : ", "failed registering user $e")
                        }
                    }
                }

                navController.navigate(Screen.HomeScreen.route)
                viewModel.resetState()
            }

            ScreenLogin(
                state = state,
                onSignInClick = {
                    lifecycleScope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                },
            )

        }

        composable(Screen.ScreenProfile.route) {
            val customerVM: CustomerViewModel = viewModel(
                factory = viewModelFactory {
                    CustomerViewModel(
                        MyApp.appModule.customerRepositoryImpl,
                        googleAuthUiClient.getSignedInUser()!!.userId
                    )
                }
            )
            val customerUiState = customerVM.state.collectAsStateWithLifecycle().value

            ProfileScreen (
                userData = googleAuthUiClient.getSignedInUser(),
                customerModel = customerUiState.customerState,
                onSignOut = {
                    lifecycleScope.launch {
                        googleAuthUiClient.signOut()
                        Toast.makeText(context, "Signed Out", Toast.LENGTH_LONG).show()

                        navController.navigate(Screen.ScreenLogin.route)
                    }
                },
            )
        }

        composable(Screen.HomeScreen.route){
            val homeScreenVM: HomeScreenViewModel = viewModel(
                factory = viewModelFactory { HomeScreenViewModel(MyApp.appModule.katalisRepositoryImpl) }
            )
            val homeScreenVMUiState = homeScreenVM.state.collectAsState().value
            val homeScreenVMEffectFlow = homeScreenVM.effect

            HomeScreen(
                userData = googleAuthUiClient.getSignedInUser(),
                homeScreenVMUiState,
                homeScreenVMEffectFlow,
                homeScreenVM::onEvent,
                onNavigateToScreen = { navController.navigate(it) }
            )
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
            ScreenCheckOut(
                onNavigateToHome = { navController.popBackStack() },
                userData = googleAuthUiClient.getSignedInUser()!!,
            )
        }
        composable(Screen.ScreenPesananAnda.route) {
            PesananAnda(
                onNavigateToScreen = { navController.navigate(it) }
            )
        }
        composable(Screen.ScreenLengkapiData.route) {
            ScreenLengkapiData(
                onNavigateToScreen = { navController.navigate(it) }
            )
        }
    }

}