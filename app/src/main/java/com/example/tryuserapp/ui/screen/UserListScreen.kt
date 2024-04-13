package com.example.tryuserapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import java.lang.reflect.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import com.example.tryuserapp.data.DummyData
import com.example.tryuserapp.model.Customer
import com.example.tryuserapp.presentation.customer.CustomerEvent
import com.example.tryuserapp.presentation.customer.CustomerState

@Composable
fun CustomerListScreen(
    customerState: CustomerState,
    onEvent: (CustomerEvent)->Unit
) {
    LazyColumn() {
        items(customerState.customerListState) { customer ->
            CustomerItem(customer = customer)
        }
    }
}

@Composable
fun CustomerItem(customer : Customer) {
    Surface() {
        Column() {
            Text(text = customer.name)
            Text(text = customer.phone_number)
            Text(text = customer.address)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        CustomerListScreen(
            CustomerState(DummyData.dummyCustomerFlow),
            onEvent = {}
        )
    }
}