package com.example.tryuserapp

import android.app.Application
import com.example.tryuserapp.di.AppModuleImpl

class MyApp: Application() {
    companion object {
        lateinit var appModule: AppModule
        lateinit var application: Application
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this, this)
//        appModule.database.openHelper.writableDatabase
        //        appModule.database.openHelper.readableDatabase
        }

}