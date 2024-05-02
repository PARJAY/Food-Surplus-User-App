package com.example.tryuserapp.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tryuserapp.data.model.CustomerModel
import com.example.tryuserapp.presentation.sign_in.UserData
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import com.example.tryuserapp.ui.theme.Krem

@Composable
fun ProfileScreen(
    userData: UserData?,
    customerModel : CustomerModel,
    onSignOut: () -> Unit
) {
    var name by remember{ mutableStateOf(customerModel.name) }
    var alamat by remember{ mutableStateOf(customerModel.address) }
    var phoneNumber by remember{ mutableStateOf(customerModel.phone_number) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Krem)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (userData?.profilePictureUrl != null) {
            AsyncImage(
                model = userData.profilePictureUrl,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Krem,
                unfocusedContainerColor = Krem
            ),
            value = customerModel.name,
            onValueChange = { name = it },
            readOnly = false,
            label = { Text("Nama") }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Krem,
                unfocusedContainerColor = Krem
            ),
            value = alamat,
            onValueChange = { alamat = it },
            readOnly = false,
            label = { Text("Alamat") }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Krem,
                unfocusedContainerColor = Krem
            ),
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            readOnly = false,
            label = { Text("Nomor Telepon") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onSignOut) {
            Text(text = "Sign Out")
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
                userData = UserData(),
                customerModel = CustomerModel(),
                onSignOut = {}
            )
        }
    }
}
