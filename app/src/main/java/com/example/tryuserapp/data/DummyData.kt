package com.example.tryuserapp.data

import com.example.tryuserapp.logic.StatusPesanan
import com.example.tryuserapp.data.model.DaftarKatalis
import com.example.tryuserapp.data.model.Pesanan
import java.util.Calendar
import com.example.tryuserapp.data.model.CustomerModel


class DummyData {

    companion object {

        val customer1 = CustomerModel("John Doe", "john.doe@example.com", "ABC123")

        val customer2 = CustomerModel("Jane Smith", "jane.smith@example.com", "XYZ789")

        val customer3 = CustomerModel("Michael Johnson", "michael.johnson@example.com", "DEF456")

        val dummyCustomerFlow: List<CustomerModel> = listOf(customer1, customer2, customer3)

        val pesanan1 = Pesanan(
            "21",
            "3123",
            "500312",
            "15002132, adasda",
            500f,
            "Gambar",
            StatusPesanan.SEDANG_DIANTAR,
            Calendar.getInstance().time.toString()
        )
        val pesanan2 = Pesanan(

            "21",
            "3123",
            "500312",
            "15002132, adasda",
            500f,
            "Gambar",
            StatusPesanan.SEDANG_DIANTAR,
            Calendar.getInstance().time.toString()
        )
        val pesanan3 = Pesanan(

            "21",
            "3123",
            "500312",
            "15002132, adasda",
            500f,
            "Gambar",
            StatusPesanan.SEDANG_DIANTAR,
            Calendar.getInstance().time.toString()
        )
        val dummyPesananFlow: List<Pesanan> = listOf(pesanan1, pesanan2, pesanan3)

        val dummyCustomerModelFlow: List<CustomerModel> = listOf(customer1, customer2, customer3)
    }
}