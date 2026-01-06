package com.arjun.demo

import android.app.Application
import com.arjun.demo.navigation.di.KoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DemoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DemoApp)
            modules(KoinModules.allModules)
        }
    }
}