package com.example.tryuserapp.tools

import android.net.Uri
import android.util.Log
import com.example.tryuserapp.MyApp
import com.example.tryuserapp.data.model.KatalisModel
import com.example.tryuserapp.data.model.UserModel
import com.google.firebase.firestore.QueryDocumentSnapshot

class FirebaseHelper {
    companion object {
        fun fetchSnapshotToUserModel(queryDocumentSnapshot : QueryDocumentSnapshot) : UserModel {
            return UserModel(
                id = queryDocumentSnapshot.id,
                name = queryDocumentSnapshot.getString("name") ?: "",
                address = queryDocumentSnapshot.getString("address") ?: "",
                phone_number = queryDocumentSnapshot.getString("phone_number") ?: ""
            )
        }

        fun fetchSnapshotToKatalisModel(queryDocumentSnapshot: QueryDocumentSnapshot): KatalisModel {
            val komposisi = queryDocumentSnapshot.getString("idHotel") ?: ""

            Log.d("Helper", "$komposisi - ${komposisi::class.simpleName}")

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