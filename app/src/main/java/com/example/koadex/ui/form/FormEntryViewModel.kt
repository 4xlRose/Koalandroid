package com.example.koadex.ui.form
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.koadex.data.FormRepository
import com.example.koadex.data.FormEntity
import kotlinx.coroutines.launch







class FormEntryViewModel(private val formRepository: FormRepository) : ViewModel() {

    var formUiState by mutableStateOf(FormUiState())
        private set


    fun updateUiState(formDetails: FormDetails) {
        formUiState = FormUiState(
            formDetails = formDetails,
            isEntryValid = validateInput(formDetails)
        )
    }

    suspend fun saveForm() {
        if (validateInput()) {
            formRepository.insertForm(formUiState.formDetails.toEntity())
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

// 6. Update the data classes
data class FormDetails(
    val id: Int = 0,
    val name: String = "",
    val date: String = "",
    val place: String = "",
    val hour: String = "",
    val weather: String = "",
    val season: String = ""
)



// Extension function to convert FormDetails to FormEntity
fun FormDetails.toEntity(): FormEntity = FormEntity(
    name = name,
    date = date,
    place = place,
    hour = hour,
    weather = weather,
    season = season
)

fun FormEntity.toFormUiState(isEntryValid: Boolean = false): FormUiState = FormUiState(
    formDetails = this.toFormDetails(),
    isEntryValid = isEntryValid
)

fun FormEntity.toFormDetails(): FormDetails = FormDetails(
    name = name,
    date = date,
    place = place,
    hour = hour,
    weather = weather
)