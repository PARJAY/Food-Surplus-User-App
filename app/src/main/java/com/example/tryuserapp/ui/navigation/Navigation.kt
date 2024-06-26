package com.example.tryuserapp.ui.navigation

import android.annotation.SuppressLint
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tryuserapp.MyApp
import com.example.tryuserapp.common.BEGIN_QUANTITY_KATALIS
import com.example.tryuserapp.data.model.CustomerModel
import com.example.tryuserapp.data.model.DaftarKatalis
import com.example.tryuserapp.data.model.KatalisModel
import com.example.tryuserapp.data.model.PesananModel
import com.example.tryuserapp.data.repository.KatalisRepositoryImpl
import com.example.tryuserapp.data.repository.PesananListRepositoryImpl
import com.example.tryuserapp.logic.StatusPesanan
import com.example.tryuserapp.presentation.customer.CustomerViewModel
import com.example.tryuserapp.presentation.home_screen.HomeScreenViewModel
import com.example.tryuserapp.presentation.katalis_screen.KatalisScreenViewModel
import com.example.tryuserapp.presentation.pesanan.PesananRepositoryImpl
import com.example.tryuserapp.presentation.pesanan.PesananViewModel
import com.example.tryuserapp.presentation.katalis_screen.SelectedKatalis
import com.example.tryuserapp.presentation.pesanan.PesananListViewModel
import com.example.tryuserapp.presentation.sign_in.GoogleAuthUiClient
import com.example.tryuserapp.presentation.sign_in.SignInViewModel
import com.example.tryuserapp.presentation.viewModelFactory
import com.example.tryuserapp.ui.component.Toggleableinfo
import com.example.tryuserapp.ui.screen.DetailKatalis
import com.example.tryuserapp.ui.screen.HomeScreen
import com.example.tryuserapp.ui.screen.KatalisScreen
import com.example.tryuserapp.ui.screen.LocationGpsScreen
import com.example.tryuserapp.ui.screen.MapsScreen
import com.example.tryuserapp.ui.screen.PesananAnda
import com.example.tryuserapp.ui.screen.ProfileScreen
import com.example.tryuserapp.ui.screen.ScreenCheckOut
import com.example.tryuserapp.ui.screen.ScreenDetailPesananAnda
import com.example.tryuserapp.ui.screen.ScreenLengkapiData
import com.example.tryuserapp.ui.screen.ScreenLogin
import com.example.tryuserapp.ui.screen.TrackUserLocationScreen
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
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

    val selectedKatalis = remember { mutableStateListOf<SelectedKatalis>() }

    // TODO : Set from hotel screen, get in Checkout Screen
    var selectedHotelId by remember { mutableStateOf("") }

    var selectedDetailKatalis by remember { mutableStateOf(KatalisModel()) }

    var selectedDetailPesananModel by remember { mutableStateOf(
        PesananModel(
            id_customer = "",
            id_kurir = "",
            id_hotel = "",
            daftarKatalis = DaftarKatalis().daftarKatalis,
            total_harga = 0f,
            transfer_proof_image_link = "",
            waktu_pesanan_dibuat = Timestamp.now(),
            jarak_user_dan_hotel = 0f,
            status_pesanan = StatusPesanan.MENUNGGU_KONFIRMASI_ADMIN.toString()
        )
    )}

    var navAlamatByName by remember { mutableStateOf("") }

    val navAlamatCustomerByGeolocation = remember { mutableStateOf(LatLng(0.0, 0.0)) }
    var navAlamatHotelByGeolocation by remember { mutableStateOf("") }

    var radioButtons = remember{
        mutableStateListOf(
            Toggleableinfo(
                isChecked = true,
                text = "Ambil Sendiri",
                textview = false,
                extretextview = false
            ),
            Toggleableinfo(
                isChecked = false,
                text = "Diantar",
                textview = true,
                extretextview = false
            ),
            Toggleableinfo(
                isChecked = false,
                text = "Donasi",
                textview = true,
                extretextview = true
            ),
        )
    }

    val changeScreen = remember { mutableStateOf(false) }
    val normalizeScreen = remember { mutableStateOf(false) }

    NavHost(navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.ScreenLogin.route) {
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
                    val existingUser =
                        MyApp.appModule.customerRepositoryImpl.getCustomerById(fetchedUser.userId)
                    Log.d("NAVIGATION : ", "logged in user firebase data : $existingUser")

                    if (existingUser.name.isEmpty()) {
                        val newUser = CustomerModel(
                            id = fetchedUser.userId,
                            name = fetchedUser.username ?: "",
                        )

                        try {
                            MyApp.appModule.customerRepositoryImpl.addOrUpdateCustomer(
                                fetchedUser.userId,
                                newUser
                            )
                            Log.d("NAVIGATION : ", "registering user success")
                        } catch (e: Exception) {
                            Log.d("NAVIGATION : ", "failed registering user $e")
                        }
                    }
                }

                navController.navigate(Screen.ScreenLengkapiData.route)
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

        composable(Screen.ScreenLengkapiData.route) {
            ScreenLengkapiData(
                onNavigateToScreen = { navController.navigate(it) },
                userData = googleAuthUiClient.getSignedInUser(),
                customerModel = CustomerModel(),
                customerViewModel = CustomerViewModel(
                    MyApp.appModule.customerRepositoryImpl,
                    googleAuthUiClient.getSignedInUser()!!.userId
                )
            )
        }

        composable(Screen.ScreenCheckOut.route) {
            Log.d("Screen Checkout", "Passed here")

            selectedKatalis.toList().forEach {
                Log.d("Screen Checkout", "selected Katalis List : $it")
            }

            ScreenCheckOut(
                pesananViewModel = PesananViewModel(
                    PesananRepositoryImpl(db = FirebaseFirestore.getInstance()),
                    KatalisRepositoryImpl(db = FirebaseFirestore.getInstance()),
                    pesananListRepositoryImpl = PesananListRepositoryImpl(db = FirebaseFirestore.getInstance())
                ),
                onNavigateToHome = {
                    navController.popBackStack()
                    navController.popBackStack()
                },
                userData = googleAuthUiClient.getSignedInUser()!!,
                onNavigateToScreen = {navController.navigate(it) },
                alamatByName = navAlamatByName,
                alamatByGeolocation = navAlamatCustomerByGeolocation,
                selectedKatalis = selectedKatalis,
                selectedIdHotel = selectedHotelId,
                alamatHotelByName = navAlamatHotelByGeolocation,
                radioButtons = radioButtons
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

            ProfileScreen(
                userData = googleAuthUiClient.getSignedInUser(),
                customerModel = customerUiState.customerState,
                onSignOut = {
                    lifecycleScope.launch {
                        googleAuthUiClient.signOut()
                        Toast.makeText(context, "Signed Out", Toast.LENGTH_LONG).show()

                        navController.popBackStack()
                    }
                },
            )
        }

        composable(Screen.KatalisScreen.route) {
            val katalisScreenVM: KatalisScreenViewModel = viewModel(
                factory = viewModelFactory { KatalisScreenViewModel(MyApp.appModule.katalisRepositoryImpl) }
            )
            val katalisScreenVMUiState = katalisScreenVM.state.collectAsState().value

            radioButtons = remember {
                    mutableStateListOf(
                        Toggleableinfo(
                            isChecked = true,
                            text = "Ambil Sendiri",
                            textview = false,
                            extretextview = false
                        ),
                        Toggleableinfo(
                            isChecked = false,
                            text = "Diantar",
                            textview = true,
                            extretextview = false
                        ),
                        Toggleableinfo(
                            isChecked = false,
                            text = "Donasi",
                            textview = true,
                            extretextview = true
                        ),
                    )
                }

            KatalisScreen(
                userData = googleAuthUiClient.getSignedInUser(),
                katalisScreenVMUiState,
                onNavigateToScreen = { navController.navigate(it) },
                onSetSelectedDetailKatalis = { selectedDetailKatalis = it },
                selectedKatalisList = selectedKatalis,
                selectedHotelId = selectedHotelId
            )
        }

        composable(Screen.HomeScreen.route){
            val homeScreenVM: HomeScreenViewModel = viewModel(
                factory = viewModelFactory { HomeScreenViewModel(MyApp.appModule.hotelRepositoryImpl) }
            )
            val homeScreenVMUiState = homeScreenVM.state.collectAsState().value

            LaunchedEffect(key1 = Unit) {
                if (googleAuthUiClient.getSignedInUser() == null)
                    navController.navigate(Screen.ScreenLogin.route)
            }

            HomeScreen(
                userData = googleAuthUiClient.getSignedInUser(),
                homeScreenUiState = homeScreenVMUiState,
                onNavigateToScreen = { navController.navigate(it) },
                onSelectHotel = { idHotel, geolocationHotel ->
                    selectedHotelId = idHotel
                    navAlamatHotelByGeolocation = geolocationHotel
                }

            )
        }

        composable(Screen.ScreenPesananAnda.route) {
//            Log.d("Nav > Pesanan Screen", googleAuthUiClient.getSignedInUser()!!.userId)
            val pesananScreenVM: PesananListViewModel = viewModel(
                factory = viewModelFactory { PesananListViewModel(
                    pesananListRepositoryImpl =  MyApp.appModule.pesananListRepositoryImpl,
                    idCustomer = googleAuthUiClient.getSignedInUser()!!.userId
                ) }
            )

            val pesananScreenVMUiState = pesananScreenVM.state.collectAsState().value

            PesananAnda(
                pesananState = pesananScreenVMUiState,
                onNavigateToDetailPesananScreen = { pesananModel, desiredScreen ->
                    selectedDetailPesananModel = pesananModel
                    navController.navigate(desiredScreen)
                },
                pesananListViewModel = PesananListViewModel(
                    MyApp.appModule.pesananListRepositoryImpl,
                    idCustomer = googleAuthUiClient.getSignedInUser()!!.userId
                ),
                userData = googleAuthUiClient.getSignedInUser()!!
            )
        }

        composable(Screen.ScreenDetailPesananAnda.route) {
            ScreenDetailPesananAnda(
                selectedDetailPesananModel,
            )
        }

        composable(Screen.ScreenDetailKatalis.route) {
            DetailKatalis(
                selectedDetailKatalis = selectedDetailKatalis,
                onAddSelectedKatalisList = {
                    selectedKatalis.add(
                        SelectedKatalis(
                            selectedDetailKatalis.id,
                            BEGIN_QUANTITY_KATALIS,
                            namaKatalis = selectedDetailKatalis.namaKatalis,
                            hargaKatalis = selectedDetailKatalis.hargaJual,
                            stokKatalis = selectedDetailKatalis.stok
                        )
                    )
                },
                onModifySelectedKatalisList = { modifiedQuantityKatalis ->
                    selectedKatalis.find { loopedKatalis ->
                        loopedKatalis.idKatalis == selectedDetailKatalis.id
                    }?.quantity = modifiedQuantityKatalis
                },
                onRemoveSelectedKatalisListById = {
                    selectedKatalis.removeAll { it.idKatalis == selectedDetailKatalis.id }
                },
                selectedQuantityKatalis = selectedKatalis.find{
                    it.idKatalis == selectedDetailKatalis.id
                }?.quantity ?: 0,
            )
        }

        composable(Screen.MapsScreen.route) {
            MapsScreen(
                onButtonSelectLocationClick = { alamatByName, alamatByGeolocation ->
                    navAlamatByName = alamatByName
                    navAlamatCustomerByGeolocation.value = alamatByGeolocation
                    changeScreen.value = true
                    Log.d("MapsScreen", "changeScreen.value = ${changeScreen.value}")
                }
            )

            if (changeScreen.value) {
                Log.d("MapsScreen", "How many you been executed?")
                navController.popBackStack()
                changeScreen.value = false
                normalizeScreen.value = true
            }
        }

        composable(Screen.TrackUserLocationScreen.route) {
            TrackUserLocationScreen(
                onPermissionGranted = {},
                onPermissionDenied = {},
                onPermissionsRevoked = {}
            )
        }

        composable(Screen.LocationGpsScreen.route) {
            LocationGpsScreen()
        }
    }
}