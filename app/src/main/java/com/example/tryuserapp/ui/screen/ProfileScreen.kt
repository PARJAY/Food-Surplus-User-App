package com.example.tryuserapp.ui.screen

import android.content.res.Configuration
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.tryuserapp.R
import com.example.tryuserapp.data.model.CustomerModel
import com.example.tryuserapp.presentation.sign_in.UserData
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import com.example.tryuserapp.ui.theme.backGroundScreen

@Composable
fun ProfileScreen(
    userData: UserData?,
    customerModel : CustomerModel,
    onSignOut: () -> Unit,
    navController: NavController
){
    var name by remember{ mutableStateOf("Gilang") }
    var alamat by remember{ mutableStateOf("Badung") }
    var email by remember{ mutableStateOf("gilang.okandhewanta@gmail.com") }

    Box(
        modifier = Modifier
        .fillMaxSize()
        .background(backGroundScreen),)
    {
        Column(modifier = Modifier.padding(16.dp)){
            Image(
                modifier = Modifier
                    .size(20.dp)
                    .clickable { navController.navigate("HomeScreen") },
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back"
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
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
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = backGroundScreen,
                        unfocusedContainerColor = backGroundScreen
                    ),
                    value = customerModel.name,
                    onValueChange = { name = it },
                    readOnly = false,
                    label = { Text("Nama") }
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = backGroundScreen,
                        unfocusedContainerColor = backGroundScreen
                    ),
                    value = alamat,
                    onValueChange = { alamat = it },
                    readOnly = false,
                    label = { Text("Alamat") }
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = backGroundScreen,
                        unfocusedContainerColor = backGroundScreen
                    ),
                    value = email,
                    onValueChange = { email = it },
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

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ProfileScreenPreview() {
    TryUserAppTheme {
        Surface {
            ProfileScreen(
                navController = rememberNavController(),
                userData = UserData(),
                customerModel = CustomerModel(),
                onSignOut = {}
            )
        }
    }
}
