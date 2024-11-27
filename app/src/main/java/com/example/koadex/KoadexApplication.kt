@file:JvmName("FormApplicationKt")

package com.example.koadex

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.koadex.ViewModels.NavigationModel
import com.example.koadex.ViewModels.PerfilScreenViewModel
import com.example.koadex.ui.form.FormFollowDBViewModel

import com.example.koadex.ui.form.FormGeneralDBViewModel
import com.example.koadex.ui.form.FormQuadrantDBViewModel
import com.example.koadex.ui.form.FormRouteFormDBViewModel
import com.example.koadex.ui.form.FormSpecieDBViewModel
import com.example.koadex.ui.form.FormWeatherDBViewModel
import com.example.koadex.ui.form.FormsPredeterminedViewModel
import com.example.koadex.ui.principal.KoadexViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            FormGeneralDBViewModel(formApplication().container.formsRepository)
        }
        initializer {
            FormFollowDBViewModel(formApplication().container.formsRepository)
        }
        initializer {
            FormQuadrantDBViewModel(formApplication().container.formsRepository)
        }
        initializer {
            FormRouteFormDBViewModel(formApplication().container.formsRepository)
        }
        initializer {
            FormSpecieDBViewModel(formApplication().container.formsRepository)
        }
        initializer {
            FormWeatherDBViewModel(formApplication().container.formsRepository)
        }
        initializer {
            FormsPredeterminedViewModel(formApplication().container.formsRepository)
        }
        initializer {
            KoadexViewModel(formApplication().container.formsRepository)
        }
        initializer {
            NavigationModel(formApplication().container.formsRepository)
        }
        initializer {
            PerfilScreenViewModel(formApplication().container.formsRepository)
        }
    }
}

fun CreationExtras.formApplication(): FormApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as FormApplication)
