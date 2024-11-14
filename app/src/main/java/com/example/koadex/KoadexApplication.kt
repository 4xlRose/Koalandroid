@file:JvmName("FormApplicationKt")

package com.example.koadex

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

import com.example.koadex.ui.form.FormEntryViewModel
import com.example.koadex.ui.principal.KoadexViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            FormEntryViewModel(formApplication().container.formsRepository)
        }
        initializer {
            KoadexViewModel(formApplication().container.formsRepository)
        }
    }
}

fun CreationExtras.formApplication(): FormApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as FormApplication)
