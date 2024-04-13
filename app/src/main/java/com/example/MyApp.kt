package com.example.tryuserapp

import android.app.Application
import com.example.dummyfirebaseauth.di.AppModule
import com.example.dummyfirebaseauth.di.AppModuleImpl
import com.example.tryuserapp.di.AppModuleImpl

class MyApp: Application() {

    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
//        appModule = AppModuleImpl(appModule, appModule)
    }
}