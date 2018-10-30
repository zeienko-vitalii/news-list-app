package com.zeienko.newslistapp.core

import android.app.Application
import com.zeienko.newslistapp.di.getAllKoinModules
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, getAllKoinModules())
    }
}