package com.aherbel.mptest.app

import android.app.Application
import com.aherbel.mptest.di.appModule
import com.aherbel.mptest.di.remoteDataSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MPTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MPTestApplication)
            modules(listOf(appModule, remoteDataSourceModule))
        }
    }
}