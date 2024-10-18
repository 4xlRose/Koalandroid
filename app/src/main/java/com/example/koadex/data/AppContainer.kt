package com.example.koadex.data


import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val formsRepository: FormsRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineFormsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [FormsRepository]
     */
    override val formsRepository: FormsRepository by lazy {
        OfflineFormsRepository(FormsDatabase.getDatabase(context).FormGeneralDao())
    }
}
