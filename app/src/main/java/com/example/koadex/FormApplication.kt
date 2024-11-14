package com.example.koadex

import android.app.Application
import com.example.koadex.data.AppContainer
import com.example.koadex.data.AppDataContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FormApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}