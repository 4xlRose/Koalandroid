package com.example.koadex.ui.test

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.junit.Test

class VerificacionCampo {
    @Composable
    @Test
    fun Inhabilitacion() {
        var isFileSelected by remember { mutableStateOf(false) }

        fun Verificacion() {
            isFileSelected = true
        }
    }
}