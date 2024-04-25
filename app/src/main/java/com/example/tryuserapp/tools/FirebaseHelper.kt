package com.example.tryuserapp.tools

import android.net.Uri
import android.util.Log
import com.example.tryuserapp.MyApp
import com.example.tryuserapp.data.model.KatalisModel
import com.example.tryuserapp.data.model.CustomerModel
import com.example.tryuserapp.data.model.HotelModel
import com.example.tryuserapp.data.model.PesananModel
import com.example.tryuserapp.logic.StatusHotel
import com.example.tryuserapp.logic.StatusPesanan
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot

class FirebaseHelper {
    companion object {
        fun fetchSnapshotToCustomerModel(queryDocumentSnapshot : DocumentSnapshot) : CustomerModel {
            return CustomerModel(
                name = queryDocumentSnapshot.getString("name") ?: "",
                address = queryDocumentSnapshot.getString("address") ?: "",
                phone_number = queryDocumentSnapshot.getString("phone_number") ?: ""
            )
        }
        fun fetchSnapshotToPesananModel(queryDocumentSnapshot : DocumentSnapshot) =
            PesananModel(
                id_customer = queryDocumentSnapshot.getString("id_customer") ?: "",
                id_hotel = queryDocumentSnapshot.getString("id_hotel") ?: "",
                id_kurir = queryDocumentSnapshot.getString("id_kurir") ?: "",
                list_id_daftar_katalis = queryDocumentSnapshot.getString("phone_number") ?: "",
                total_harga = queryDocumentSnapshot.getLong("total_harga")?.toFloat() ?: 0.0f,
                transfer_proof_image_link = queryDocumentSnapshot.getString("transfer_proof_image_link") ?: "",
                status_pesanan = queryDocumentSnapshot.getString("status_pesanan") ?: "",
                waktu_pesanan_dibuat = queryDocumentSnapshot.getString("waktu_pesanan_dibuat") ?: "",
                id_pesanan = queryDocumentSnapshot.id
            )

        fun fetchSnapshotToKatalisModel(queryDocumentSnapshot: DocumentSnapshot): KatalisModel {
            val komposisi = queryDocumentSnapshot.getString("idHotel") ?: ""
            val hargaAwal = queryDocumentSnapshot.getLong("hargaAwal")?.toFloat() ?: 0.0f

            Log.d("Helper", "$komposisi - ${komposisi::class.simpleName}")
            Log.d("Helper", "$hargaAwal - ${hargaAwal::class.simpleName}")

            return KatalisModel(
                id = queryDocumentSnapshot.id,
                idHotel = queryDocumentSnapshot.getString("idHotel") ?: "",
                namaKatalis = queryDocumentSnapshot.getString("namaKatalis") ?: "",
                imageLink = queryDocumentSnapshot.getString("imageLink")?: "",
                stok = queryDocumentSnapshot.getLong("stok")?.toInt() ?: 0 ,
                komposisi = queryDocumentSnapshot.getString("komposisi")?.split(",") ?: emptyList(),
                hargaAwal = queryDocumentSnapshot.getLong("hargaAwal")?.toFloat() ?: 0.0f,
                hargaJual = queryDocumentSnapshot.getLong("hargaJual")?.toFloat() ?: 0.0f,
                porsiJual = queryDocumentSnapshot.getString("porsiJual") ?: "",
            )
        }
        fun fetchSnapshotToHotelModel(queryDocumentSnapshot: QueryDocumentSnapshot) =
            HotelModel(
                idHotel = queryDocumentSnapshot.id,
                name =  queryDocumentSnapshot.getString("name") ?: "",
                phoneNumber = queryDocumentSnapshot.getString("phoneNumber") ?: "",
                email = queryDocumentSnapshot.getString("email")?: "",
                alamat = queryDocumentSnapshot.getString("alamat")?: "" ,
                listIdKatalis = queryDocumentSnapshot.getString("katalis")?.split(",") ?: emptyList(),
                statusHotel = queryDocumentSnapshot.getString("statusHotel")?: "",
            )

        fun getFileFromFirebaseStorage (
            fileReference : String,
            onSuccess: (Uri) -> Unit,
            onError: (Exception) -> Unit
        ) {
            MyApp.appModule.storage.reference.child(fileReference).downloadUrl.addOnSuccessListener { uri ->
                Log.d("Firebase Helper: ", "download Success : $uri")
                onSuccess(uri)
            }.addOnFailureListener { exception ->
                onError(exception)
                Log.d("Firebase Helper: ", "download error : $exception")
            }
        }

        fun uploadImageToFirebaseStorage(
            userIdForFileReference : String,
            file: Uri,
            onSuccess: (String) -> Unit,
            onError: (Exception) -> Unit
        ) {
            val fileName = file.path?.substringAfterLast("/")

            MyApp.appModule.storage.getReference("$userIdForFileReference/$fileName").putFile(file).addOnSuccessListener { snapshot ->
                snapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener { uri ->
                    Log.d("Firebase Helper: ", "upload Success : $uri")
                    onSuccess(uri.toString())
                }?.addOnFailureListener { exception ->
                    Log.d("Firebase Helper: ", "upload Failed : $exception")
                    onError(exception)
                }
            }.addOnFailureListener { exception ->
                Log.d("Firebase Helper: ", "Error : $exception")
                onError(exception)
            }
        }
    }
}