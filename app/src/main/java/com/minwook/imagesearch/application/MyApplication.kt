package com.minwook.imagesearch.application

import android.app.Application
import com.minwook.imagesearch.di.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}