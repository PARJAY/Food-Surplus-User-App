package com.example.tryuserapp.data

import com.example.tryuserapp.data.model.PesananModel
import java.util.Calendar
import com.example.tryuserapp.data.model.CustomerModel
import com.example.tryuserapp.data.model.DaftarKatalis
import com.google.firebase.Timestamp


class DummyData {

    companion object {

        val customer1 = CustomerModel("John Doe", "john.doe@example.com", "ABC123")

        val customer2 = CustomerModel("Jane Smith", "jane.smith@example.com", "XYZ789")

        val customer3 = CustomerModel("Michael Johnson", "michael.johnson@example.com", "DEF456")

        val dummyCustomerFlow: List<CustomerModel> = listOf(customer1, customer2, customer3)

        val pesananModel1 = PesananModel(
            "21",
            "3123",
            "500312",
            emptyMap(),
            500f,
            "Gambar",
            "SEDANG_DIANTAR",
            Timestamp.now(),
            "Dummy Lokasi User",
            alamatTujuan = "",
            0f,
        )
        val pesananModel2 = PesananModel(

            "21",
            "3123",
            "500312",
            emptyMap(),
            500f,
            "Gambar",
            "SEDANG_DIANTAR",
            Timestamp.now(),
            "Dummy Lokasi User",
            alamatTujuan = "",
            0f,
        )
        val pesananModel3 = PesananModel(

            "21",
            "3123",
            "500312",
            emptyMap(),
            500f,
            "Gambar",
            "SEDANG_DIANTAR",
            Timestamp.now(),
            "Dummy Lokasi User",
            alamatTujuan = "",
            0f,
        )
        val dummyPesananFlowModel: List<PesananModel> = listOf(pesananModel1, pesananModel2, pesananModel3)

        val dummyCustomerModelFlow: List<CustomerModel> = listOf(customer1, customer2, customer3)
    }
}