package com.mahmoudalyudeen.ambrosia

import android.app.Application
import com.mahmoudalyudeen.ambrosia.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(dataModule, homeModule, productsModule, portionsModule, reportModule))
        }
    }
}