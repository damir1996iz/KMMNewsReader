package com.damikkg.kmmnewsapi.android

import android.app.Application
import com.damikkg.kmmnewsapi.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger(Level.NONE)
            androidContext(this@MainApp)
        }
    }
}