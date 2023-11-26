package com.itis.mvicore

import android.app.Application
import com.itis.mvicore.di.appModule
import com.itis.mvicore.di.featureModule
import com.itis.mvicore.di.networkModule
import com.itis.mvicore.di.newsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                featureModule,
                networkModule,
                newsModule
            )
        }
    }
}
