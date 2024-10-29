package com.example.koadex.localdata


import android.content.Context
import com.example.koadex.localdata.FormDatabase
import com.example.koadex.localdata.OfflineFormsRepository

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val formsRepository: FormRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val formsRepository: FormRepository by lazy {
        OfflineFormsRepository(FormDatabase.getDatabase(context).formDao())
    }
}
