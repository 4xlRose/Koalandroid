package com.example.koadex.data


import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val formsRepository: FormsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val formsRepository: FormsRepository by lazy {
        OfflineFormsRepository(FormsDatabase.getDatabase(context).formGeneralDao())
    }
}
