package com.example.smarttask.app

import android.app.Application
import android.content.Context
import com.example.smarttask.di.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@ExperimentalCoroutinesApi
class MyApp : Application() {
    companion object {
        var appContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this.applicationContext
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApp)
            modules(
                listOf(
                    networkingModules,
                    inAppUpdateModules,
                    contactsAdaptersModule,
                    registerRepositoryModule,
                    initialViewModelModule
                )
            )
        }
    }
}