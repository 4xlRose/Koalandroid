package com.example.koadex.ui.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.koadex.data.FormRepository
import com.example.koadex.data.SpecieFormEntity


class FormSpecieDBViewModel (private val formRepository: FormRepository) : ViewModel()
{
    //Fomulario Especie
    var formEspeciesUiState by mutableStateOf(SpeciesFormUiState())
        private set

    fun updateEspecieFormUiState(formEspecie: SpecieFormsDetails) {
        formEspeciesUiState = SpeciesFormUiState(
            formsEspecieDetails = formEspecie
        )
    }

    suspend fun saveEspecieForm() {
        formRepository.insertSpecieForm(formEspeciesUiState.formsEspecieDetails.toEntity())
    }
}


/*Formulario Especies*/
data class SpeciesFormUiState(
    var formsEspecieDetails: SpecieFormsDetails = SpecieFormsDetails()
)

data class SpecieFormsDetails(
    val id: Int = 0,
    var transect: String = "",
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

fun SpecieFormsDetails.toEntity() : SpecieFormEntity = SpecieFormEntity(
    transect = transect,
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

