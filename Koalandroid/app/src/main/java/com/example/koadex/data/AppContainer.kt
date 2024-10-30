package com.example.koadex.data


import android.content.Context

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
