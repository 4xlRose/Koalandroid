package com.example.koadex

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

import com.example.koadex.ui.form.FormEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            FormEntryViewModel(formApplication().container.formsRepository)
        }




    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.formApplication(): FormApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as FormApplication)
