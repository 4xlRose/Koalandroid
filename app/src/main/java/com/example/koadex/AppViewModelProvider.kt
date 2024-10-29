package com.example.koadex

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

import com.example.koadex.localdata.OfflineFormsRepository
import com.example.koadex.ui.form.FormEntryViewModel
import com.example.koadex.localdata.FormDatabase

class KoadexApplication : Application() {
    // Lazy initialization of the database
    // This means the database won't be created until it's first needed
    val database: FormDatabase by lazy { FormDatabase.getDatabase(this) }
}

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            FormEntryViewModel(
                formRepository = OfflineFormsRepository(
                    (this[AndroidViewModelFactory.APPLICATION_KEY] as KoadexApplication).database.formDao()
                )
            )
        }
    }
}