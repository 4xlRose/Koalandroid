package com.example.koadex.data


import android.content.Context

interface AppContainer {
    val formsRepository: FormRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val formsRepository: FormRepository by lazy {
        OfflineFormsRepository(FormDatabase.getDatabase(context).formDao())
    }
}

/*
interface AppContainer {
    val formsRepository: FormRepository
    val formDao: FormDao
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val formsRepository: FormRepository by lazy {
        OfflineFormRepository()
    }
    override val formDao: FormDao by lazy {
        FormDatabase.getDatabase(context).formDao()
    }
}
*/