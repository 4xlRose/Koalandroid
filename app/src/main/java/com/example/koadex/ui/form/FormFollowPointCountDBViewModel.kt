package com.example.koadex.ui.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.koadex.data.FormCountingPointEntity
import com.example.koadex.data.FormRepository

class FormFollowPointCountDBViewModel (private val formRepository: FormRepository) : ViewModel() {
    var formCountingPointUiState by mutableStateOf(FormCountingPointUiState())

    fun updateCountingPointFormUiState(formCountingPoint: FormCountingPointDetails ) {
        formCountingPointUiState = FormCountingPointUiState(
            formsCountingPointDetails = formCountingPoint
        )
    }

    suspend fun saveCountingPointForm() {
        formRepository.insertFormCountingPoint(formCountingPointUiState.formsCountingPointDetails.toEntity())
    }

}

data class FormCountingPointUiState(
    var formsCountingPointDetails: FormCountingPointDetails = FormCountingPointDetails()
)


data class FormCountingPointDetails(
    val id: Int = 0,
    var idGeneralForm: Int = 0 ,
    var idZoneType: Int = 0,
    var idAnimalType: Int = 0,
    var animalName: String = "",
    var scientificName: String = "",
    var quantity: Int = 0,
    var idObservType: Int = 0,
    var idHeightType: Int = 0,
    var evidences: String = "",
    var observations: String = ""
)

fun FormCountingPointDetails.toEntity() : FormCountingPointEntity = FormCountingPointEntity(
   idGeneralForm = idGeneralForm,
    idZoneType = idZoneType,
    idAnimalType = idAnimalType,
    animalName = animalName,
    scientificName = scientificName,
    quantity = quantity,
    idObservType = idObservType,
    idHeightType = idHeightType,
    evidences = evidences,
    observations = observations
)