package com.example.tryuserapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.tryuserapp.data.DummyData
import com.example.tryuserapp.data.model.CustomerModel
import com.example.tryuserapp.presentation.customer.CustomerEvent
import com.example.tryuserapp.presentation.customer.CustomerState

@Composable
fun CustomerListScreen(
    customerState: CustomerState,
    onEvent: (CustomerEvent)->Unit
) {
    CustomerItem(customer = customerState.customerState)
}

@Composable
fun CustomerItem(customer : CustomerModel) {
    Surface {
        Column {
            Text(text = customer.name)
            Text(text = customer.phone_number)
            Text(text = customer.address)
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MaterialTheme {
//        CustomerListScreen(
//            CustomerState(DummyData.dummyCustomerModelFlow[0]),
//            onEvent = {}
//        )
//    }
//}