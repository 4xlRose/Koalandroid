package com.example.koadex.ui.test

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.junit.Test

class InhabilitacionTest {
    @Composable
    @Test
    fun Inhabilitacion() {
        val isFileSelected by remember { VerificacionCampo.isFileSelected }
        VerificacionCampo.Verificacion()
        println("Archivo seleccionado: $isFileSelected")
    }
}
