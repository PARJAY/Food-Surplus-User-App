package com.example.tryuserapp

import android.app.Application
import com.example.tryuserapp.di.AppModule
import com.example.tryuserapp.di.AppModuleImpl

class MyApp: Application() {
    companion object {
        lateinit var appModule: AppModule
        lateinit var application: Application
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl()

    }
}