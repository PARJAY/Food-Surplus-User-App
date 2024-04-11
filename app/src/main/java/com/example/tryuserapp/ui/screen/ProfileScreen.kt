package com.example.tryuserapp.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tryuserapp.R
import com.example.tryuserapp.ui.component.TambahKurang
import com.example.tryuserapp.ui.theme.Black
import com.example.tryuserapp.ui.theme.Orange
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import com.example.tryuserapp.ui.theme.backGroundScreen

@Composable
fun ProfileScreen(navController: NavController){
    var name by remember{ mutableStateOf("Gilang") }
    var noTelp by remember{ mutableStateOf("085738623451") }
    var alamat by remember{ mutableStateOf("Badung") }
    var jenisKelamin by remember{ mutableStateOf("Laki Laki") }
    var umur by remember{ mutableStateOf("17thn") }
    var ttl by remember{ mutableStateOf("Denpasar, 1 September 2006") }
    var email by remember{ mutableStateOf("gilang.okandhewanta@gmail.com") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(backGroundScreen),){
        Column(
            modifier = Modifier
                .padding(16.dp)
        ){
            Image(
                modifier = Modifier
                    .size(20.dp)
                    .clickable { navController.navigate("HomeScreen") },
                painter = painterResource(id = R.drawable.back)
                , contentDescription = "Back")
            Spacer(modifier = Modifier.height(32.dp))
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Button(
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp),
                    shape = RoundedCornerShape(48.dp),
                    onClick = { /*TODO*/ }
                ) {

                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Column {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = backGroundScreen,
                        unfocusedContainerColor = backGroundScreen
                    ),
                    value = name,
                    onValueChange = {
                            newValue ->
                        name = newValue
                    },
                    readOnly = false,
                    label = { Text("Nama") }
                )
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

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ProfileScreenPreview() {
    TryUserAppTheme {
        Surface {
            ProfileScreen(navController = rememberNavController())
        }
    }
}
