package com.example.tryuserapp.tools

import android.net.Uri
import android.os.StrictMode
import android.util.Log
import com.example.tryuserapp.MyApp
import com.example.tryuserapp.data.model.KatalisModel
import com.example.tryuserapp.data.model.CustomerModel
import com.example.tryuserapp.data.model.HotelModel
import com.example.tryuserapp.logic.StatusHotel
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
                stokKatalis = queryDocumentSnapshot.getLong("stokKatalis")?.toFloat() ?: 0.0f ,
                komposisi = queryDocumentSnapshot.getString("komposisi")?.split(",") ?: emptyList(),
                hargaAwal = queryDocumentSnapshot.getLong("hargaAwal")?.toFloat() ?: 0.0f,
                hargaJual = queryDocumentSnapshot.getLong("hargaJual")?.toFloat() ?: 0.0f,
                porsiJual = queryDocumentSnapshot.getString("porsiJual") ?: "",
            )
        }
        fun fetchSnapshotToHotelModel(queryDocumentSnapshot: QueryDocumentSnapshot): HotelModel {

            val statusHotelQS = queryDocumentSnapshot.getLong("statusHotel")?.toInt()
            var statusHotel : StatusHotel = StatusHotel.DECLINED

            if (statusHotelQS == 0)  statusHotel = StatusHotel.WAITING
            else if (statusHotelQS == 1 ) statusHotel = StatusHotel.SUDAH_DI_ACC
            else if (statusHotelQS == 2 ) statusHotel = StatusHotel.DECLINED

            return HotelModel(
                idHotel = queryDocumentSnapshot.id,
                name =  queryDocumentSnapshot.getString("name") ?: "",
                phoneNumber = queryDocumentSnapshot.getString("phoneNumber") ?: "",
                email = queryDocumentSnapshot.getString("email")?: "",
                alamat = queryDocumentSnapshot.getString("alamat")?: "" ,
                listIdKatalis = queryDocumentSnapshot.getString("katalis")?.split(",") ?: emptyList(),
                statusHotel = statusHotel
            )
        }

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