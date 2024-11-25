package com.example.koadex.ui.test

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable

object VerificacionCampo {
    var isFileSelected = mutableStateOf(false)

    fun Verificacion() {
        isFileSelected.value = true
    }
}
