package com.example.tryuserapp

import com.example.tryuserapp.presentation.customer.CustomerRepository

interface AppModule {
//    val database: RoomDatabase
//    val salesNameDataStore : SalesNameDataStoreManager
//    val sellingKitProductListDataStore : SellingKitProductListDataStore
//    val outletRepository : OutletRepository
//    val productRepository : ProductRepository
    val customerRepository : CustomerRepository
}