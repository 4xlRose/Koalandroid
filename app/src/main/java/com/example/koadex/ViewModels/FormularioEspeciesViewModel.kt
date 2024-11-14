package com.example.koadex.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FormularioEspeciesViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(FormEspeciesUiState())
    val uiState: StateFlow<FormEspeciesUiState> = _uiState.asStateFlow()
    var entero by mutableStateOf<Int?>(1)
    fun updateEnteroInput(input: Int) {
        entero = checkEnteroInput(input)
    }
    fun checkEnteroInput(entero: Int): Int {
        if (entero < 0) {
            return entero * -1
        }
        else if (entero == 0) {
            return 1
        }
        else {
            return entero
        }
    }
    fun enteroMasUno() {
        entero = checkEnteroInput(entero?.plus(1) ?: 1)
    }
    fun enteroMenosUno() {
        entero = checkEnteroInput(entero?.minus(1) ?: 1)
    }
}