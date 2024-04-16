package com.example.tryuserapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tryuserapp.R
import com.example.tryuserapp.presentation.sing_in.UserData
import com.example.tryuserapp.ui.navigation.Screen
import com.example.tryuserapp.ui.theme.backGroundScreen

@Composable
fun ProfileScreen(
    userData: UserData?,
    onSignOut: () -> Unit,
    onNavigateToScreen : (String) -> Unit
){
    var name by remember{ mutableStateOf("Gilang") }
    var noTelp by remember{ mutableStateOf("085738623451") }
    var alamat by remember{ mutableStateOf("Badung") }
    var jenisKelamin by remember{ mutableStateOf("Laki Laki") }
    var umur by remember{ mutableStateOf("17thn") }
    var ttl by remember{ mutableStateOf("Denpasar, 1 September 2006") }
    var email by remember{ mutableStateOf("gilang.okandhewanta@gmail.com") }

    LazyColumn {
        item {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(backGroundScreen),){
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ){
                    Spacer(modifier = Modifier.height(32.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        if (userData?.profilePictureUrl != null){
                            AsyncImage(
                                model = userData.profilePictureUrl ,
                                contentDescription = "Profile Picture",
                                modifier = Modifier
                                    .size(150.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Column {
                        if (userData?.username != null) {
                            Text(
                                text = userData.username,
                                textAlign = TextAlign.Center,
                                fontSize = 36.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = backGroundScreen,
                                focusedContainerColor = backGroundScreen,
                            ),
                            value = noTelp,
                            onValueChange = {
                                    newValue ->
                                noTelp = newValue
                            },
                            readOnly = false,
                            label = { Text("No Telepon") }
                        )
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = backGroundScreen,
                                unfocusedContainerColor = backGroundScreen
                            ),
                            value = alamat,
                            onValueChange = {
                                    newValue ->
                                alamat = newValue
                            },
                            readOnly = false,
                            label = { Text("Alamat") }
                        )
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = backGroundScreen,
                                unfocusedContainerColor = backGroundScreen
                            ),
                            value = jenisKelamin,
                            onValueChange = {
                                    newValue ->
                                jenisKelamin = newValue
                            },
                            readOnly = false,
                            label = { Text("Jenis Kelamin") }
                        )
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = backGroundScreen,
                                unfocusedContainerColor = backGroundScreen
                            ),
                            value = umur,
                            onValueChange = {
                                    newValue ->
                                umur = newValue
                            },
                            readOnly = false,
                            label = { Text("Umur") }
                        )
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = backGroundScreen,
                                unfocusedContainerColor = backGroundScreen
                            ),
                            value = ttl,
                            onValueChange = {
                                    newValue ->
                                ttl = newValue
                            },
                            readOnly = false,
                            label = { Text("Tempat Tanggal Lahir") }
                        )
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = backGroundScreen,
                                unfocusedContainerColor = backGroundScreen
                            ),
                            value = email,
                            onValueChange = {
                                    newValue ->
                                email = newValue
                            },
                            readOnly = false,
                            label = { Text("Email") }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ){
                            Button(onClick = onSignOut) {
                                Text(text = "Sign Out")
                            }
                        }

                    }

                }
            }
        }
    }
}

//Column {
//    Text(text = "Screen Profile")
//    Button(onClick = {navController.navigate("HomeScreen") }) {
//        Text(text = "Back")
//    }
//}

//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun ProfileScreenPreview() {
//    TryUserAppTheme {
//        Surface {
//            ProfileScreen(navController = rememberNavController())
//        }
//    }
//}
