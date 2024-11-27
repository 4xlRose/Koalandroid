package com.example.koadex.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.koadex.utils.DateValidator

class FormularioCamaraTrampaViewModel : ViewModel() {

    private val dateValidator = DateValidator()

    private val _dateText = mutableStateOf("")
    val dateText: State<String> = _dateText

    private val _dateError = mutableStateOf<String?>(null)
    val dateError: State<String?> = _dateError

    fun updateDate(input: String) {
        if (input.length <= 8) { // Considerando los '/' en el formato
            val formattedInput = if (input.replace("/", "").length <= 6) {
                dateValidator.formatDate(input.replace("/", ""))
            } else {
                input
            }

            _dateText.value = formattedInput

            // Validar solo si el campo está completo
            if (formattedInput.length == 8) { // dd/mm/yy
                _dateError.value = if (dateValidator.isValidDate(formattedInput)) {
                    null
                } else {
                    "Fecha inválida"
                }
            } else {
                _dateError.value = null // Limpia el error si no está completa
            }
        }
    }
}
