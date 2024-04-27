package com.example.tryuserapp.presentation.pesanan

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tryuserapp.data.model.DaftarKatalis
import com.example.tryuserapp.common.FirebaseResult
import com.example.tryuserapp.data.model.PesananModel
import com.example.tryuserapp.data.repository.KatalisRepositoryImpl
import com.example.tryuserapp.data.repository.PesananListRepositoryImpl
import com.example.tryuserapp.presentation.katalis_screen.KatalisScreenUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PesananViewModel(
    private val pesananRepositoryImpl: PesananRepositoryImpl,
    private val katalisRepositoryImpl: KatalisRepositoryImpl,
    private val pesananListRepositoryImpl: PesananListRepositoryImpl,
): ViewModel() {
    private val _state = MutableStateFlow(PesananState())
    private val _katalis_state = MutableStateFlow(KatalisScreenUiState())

    private val _transaksi = pesananRepositoryImpl.getAllPesanan()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state, _transaksi) { state, transaksi ->
        state.copy(
            pesananListState = transaksi
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PesananState())

    init {
        onEvent(PesananListEvent.GetListPesanan)
    }

    private fun setState(newState: PesananState) {
        _state.value = newState
    }

    private val _effect: Channel<PesananSideEffects> = Channel()
    val effect = _effect.receiveAsFlow()

    private fun setEffect(builder: () -> PesananSideEffects) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    fun onEvent(event: PesananListEvent) {
        when (event) {
//            is PesananEvent.CreatePesanan -> {
//                createPesanan(event.pesanan)
//            }
            is PesananListEvent.GetUserListPesanan -> getPesananList(event.idCustomer)

//            is HomeScreenEvent.ModifyOrder -> {
//                modifyOrder(
//                    katalisId = event.katalisId,
//                    action = event.orderAction
//                )
//            }
            PesananListEvent.GetListPesanan -> { }
        }
    }

     fun createPesanan(newPesananModel: PesananModel) {
        viewModelScope.launch {
            setState(_state.value.copy(isLoading = true))

            try {
                pesananRepositoryImpl.insertPesanan(
                    PesananModel(
                        newPesananModel.id_customer,
                        newPesananModel.id_hotel,
                        newPesananModel.id_kurir,
                        newPesananModel.daftarKatalis,
                        newPesananModel.total_harga,
                        newPesananModel.transfer_proof_image_link,
                        newPesananModel.status_pesanan,
                        newPesananModel.waktu_pesanan_dibuat,
                        newPesananModel.lokasiUser,
                         newPesananModel.jarak_user_dan_hotel,
                        ongkir =  newPesananModel.ongkir,
                    )
                )

                setState(_state.value.copy(isLoading = false))
                setEffect { PesananSideEffects.ShowSnackBarMessage(message = "Pesanan added successfully") }
                setEffect { PesananSideEffects.ShowSnackBarMessage(message = "Daftar Katalis added successfully") }
            } catch (e: Exception) {
                setState(_state.value.copy(isLoading = false, errorMessage = e.localizedMessage))
                setEffect { PesananSideEffects.ShowSnackBarMessage(e.message ?: "Error fetching Pesanan") }
                setEffect { PesananSideEffects.ShowSnackBarMessage(e.message ?: "Failed Added Daftar Katalis  ") }
            }
        }
    }

    fun createDaftarKatalisPesanan(
        newDaftarKatalis: DaftarKatalis,
        createdDocumentId : (String) -> Unit
    ) {
        viewModelScope.launch {
            setState(_state.value.copy(isLoading = true))

            try {
                pesananRepositoryImpl.insertDaftarKatalis(
                    newDaftarKatalis,
                    createdDocumentId
                )

                setState(_state.value.copy(isLoading = false))
                setEffect { PesananSideEffects.ShowSnackBarMessage(message = "Daftar Katalis added successfully") }
            } catch (e: Exception) {
                setState(_state.value.copy(isLoading = false, errorMessage = e.localizedMessage))
                setEffect { PesananSideEffects.ShowSnackBarMessage(e.message ?: "Failed Added Daftar Katalis  ") }
            }
        }
    }

    fun decrementStok(selectedKatalisId : String, stok : Int, quantity: Int ) {
        viewModelScope.launch {
            setState(_state.value.copy(isLoading = true))
            val newStok = (stok - quantity)

            try {
                katalisRepositoryImpl.updateStokKatalis(katalisId = selectedKatalisId, fieldToUpdate = "stok", newValue =newStok  )
                setState(_state.value.copy(isLoading = false))
                setEffect { PesananSideEffects.ShowSnackBarMessage(message = "Update Stok successfully") }
            } catch (e: Exception) {
                setState(_state.value.copy(isLoading = false, errorMessage = e.localizedMessage))
                setEffect { PesananSideEffects.ShowSnackBarMessage(e.message ?: "Error Stok ") }
            }
        }
    }

    private fun getPesananList(idCustomer : String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true) // Update loading state

            pesananListRepositoryImpl.getPesananList(
                callback =  { firebaseResult ->
                    if (firebaseResult is FirebaseResult.Failure) {
                        setState(_state.value.copy(isLoading = false))
                        Log.d("VIEWMODEL: ", "error - ${firebaseResult.exception.message}")
                        setEffect {
                            PesananSideEffects.ShowSnackBarMessage(
                                firebaseResult.exception.message ?: "Error fetching users"
                            )
                        }
                    } else if (firebaseResult is FirebaseResult.Success) {
                        Log.d("VIEWMODEL: ", "sucess - ${firebaseResult.data}")
                        _state.value =
                            _state.value.copy(isLoading = false, pesananListState = firebaseResult.data)
                        setEffect { PesananSideEffects.ShowSnackBarMessage(message = "User list data loaded successfully") }
                    }
                },
                idCustomer = idCustomer
            )
        }
    }
}