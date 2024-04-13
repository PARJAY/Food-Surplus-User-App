package com.example.tryuserapp.di

import android.app.Application
import android.content.Context
import com.example.tryuserapp.AppModule
import com.example.tryuserapp.presentation.customer.CustomerRepository

class AppModuleImpl(
    private val appContext: Context,
    private val application: Application
): AppModule {

//    override val database: RoomDatabase by lazy {
//        Room.databaseBuilder(appContext, RoomDatabase::class.java, "app_database")
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//
//    override val salesNameDataStore :SalesNameDataStoreManager by lazy {
//        SalesNameDataStoreManager.getInstance(application.dataStore)
//    }
//
//    override val sellingKitProductListDataStore : SellingKitProductListDataStore by lazy {
//        SellingKitProductListDataStore.getInstance(application.dataStore)
//    }
//
//    override val outletRepository: OutletRepository by lazy {
//        OutletRepository(database.outletDao())
//    }
//
//    override val productRepository: ProductRepository by lazy {
//        ProductRepository(database.productDao())
//    }

    override val customerRepository : CustomerRepository by lazy {
        CustomerRepository()
        }

}