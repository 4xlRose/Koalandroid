package com.example.koadex

import android.app.Application
import com.example.koadex.localdata.AppContainer
import com.example.koadex.localdata.AppDataContainer


class FormApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}