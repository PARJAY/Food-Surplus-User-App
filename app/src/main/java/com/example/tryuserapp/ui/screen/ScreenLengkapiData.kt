package com.example.tryuserapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryuserapp.data.model.CustomerModel
import com.example.tryuserapp.presentation.customer.CustomerViewModel
import com.example.tryuserapp.presentation.sign_in.UserData
import com.example.tryuserapp.ui.navigation.Screen
import com.example.tryuserapp.ui.theme.HijauMuda
import com.example.tryuserapp.ui.theme.Krem

@Composable
fun ScreenLengkapiData(
    userData: UserData?,
    customerViewModel: CustomerViewModel,
    onNavigateToScreen : (String) -> Unit,
    customerModel: CustomerModel
) {
    var alamat by remember { mutableStateOf("") }

    var noTelp by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(HijauMuda),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier
                .height(525.dp)
                .width(300.dp)
                .padding(16.dp)
                .background(HijauMuda),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Krem
            ),
            shape = RoundedCornerShape(16.dp),
            onClick = { /*TODO*/ }) {
            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(32.dp))

                Text(text = "Lengkapi Data",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Tolong Lengkapi Data Diri Anda",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(35.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedLabelColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        focusedTextColor = Color.Black
                    ),
                    shape = RoundedCornerShape(16.dp),
                    label = { Text("Alamat") },
                    value =alamat ,
                    onValueChange = {
                            newValue ->
                        alamat = newValue
                    }
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedLabelColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        focusedTextColor = Color.Black
                    ),
                    shape = RoundedCornerShape(16.dp),
                    label = { Text("No Telp") },
                    value =noTelp ,
                    onValueChange = {
                            newValue ->
                        noTelp = newValue
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Box (
                    Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp),
                    contentAlignment = Alignment.BottomCenter
                ){
                    Button(modifier = Modifier
                        .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HijauMuda,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(4.dp),
                        onClick = {
                            customerViewModel.tambahDataCustomer(
                                idCustomer = userData!!.userId,
                                alamat = alamat,
                                phoneNumber = noTelp,
                                name = userData.username!!
                            )
                            onNavigateToScreen(Screen.HomeScreen.route)
                        }
                    ) {
                        Text(text = "Submit")
                    }
                }
            }
        }
    }
}

//
//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun ScreenLengkapiDataPreview() {
//    TryUserAppTheme {
//        Surface {
//            ScreenLengkapiData(onNavigateToScreen = {},
//                customerViewModel = CustomerViewModel())
//        }
//    }
//}