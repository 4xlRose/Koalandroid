package com.example.koadex.ViewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class FormEspeciesUiState (
    val entero: MutableState<Int?> =  mutableStateOf<Int?>(1)
)