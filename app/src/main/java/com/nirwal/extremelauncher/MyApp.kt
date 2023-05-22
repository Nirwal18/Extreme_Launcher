package com.nirwal.extremelauncher

import android.app.Application
import com.nirwal.extremelauncher.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}