package com.poc.newjetpackpoc.di

import android.annotation.SuppressLint
import android.app.Application
import org.koin.core.context.GlobalContext.startKoin

class DiApplication : Application() {
    @SuppressLint("VisibleForTests")
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(projectModule)
        }
    }
}