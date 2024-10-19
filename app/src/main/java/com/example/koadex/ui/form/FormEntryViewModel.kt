package com.example.koadex.ui.form


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.koadex.data.Formulario
import com.example.koadex.data.FormsRepository



class FormEntryViewModel(private val formsRepository: FormsRepository) : ViewModel() {
    var formUiState by mutableStateOf(FormUiState())
        private set

    fun updateUiState(formDetails: FormDetails) {
        formUiState =
            FormUiState(formDetails = formDetails, isEntryValid = validateInput(formDetails))
    }
    suspend fun saveForm() {
        if (validateInput()) {
            formsRepository.insertForm(formUiState.formDetails.toFormulario())
        }
    }
    private fun validateInput(uiState: FormDetails = formUiState.formDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && date.isNotBlank() && place.isNotBlank() && hour.isNotBlank()
        }
    }

}
data class FormUiState(
    val formDetails: FormDetails = FormDetails(),
    val isEntryValid: Boolean = false
)

data class FormDetails(
    val id: Int = 0,
    val name: String = "",
    val date: String = "",
    val place: String = "",
    val hour : String = "",
)

fun FormDetails.toFormulario(): Formulario = Formulario(
    id = id,
    name = name,
    date = date,
    place = place,
    hour = hour
)





fun Formulario.toFormDetails(): FormDetails = FormDetails(
    id = id,
    name = name,
    date = date,
    place = place,
    hour = hour
)
