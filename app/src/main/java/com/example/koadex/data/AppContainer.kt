package com.example.koadex.data


import android.content.Context

interface AppContainer {
    val formsRepository: FormRepository

}

class AppDataContainer(private val context: Context) : AppContainer {
    override val formsRepository: FormRepository by lazy {
        OfflineFormRepository(FormDatabase.getDatabase(context).formDao())
    }
}