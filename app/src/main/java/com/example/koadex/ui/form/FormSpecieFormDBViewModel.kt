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

    // Actualizar el ID de la Zona
    fun updateZoneTypeId(zoneTypeId: Int) {
        formEspeciesUiState = formEspeciesUiState.copy(
            formsEspecieDetails = formEspeciesUiState.formsEspecieDetails.copy(idZoneType = zoneTypeId)
        )
    }

    // Actualizar tipo de animal
    fun updateAnimalType(animalType: Int) {
        formEspeciesUiState = formEspeciesUiState.copy(
            formsEspecieDetails = formEspeciesUiState.formsEspecieDetails.copy(
                idAnimalType = animalType
            )
        )
    }

    // Actualizar tipo de observacion
    fun updateObservationType(observationType: Int) {
        formEspeciesUiState = formEspeciesUiState.copy(
            formsEspecieDetails = formEspeciesUiState.formsEspecieDetails.copy(
                idObservType = observationType
            )
        )
    }

    // Actualizar cantidad
    fun updateQuantity(quantity: Int) {
        formEspeciesUiState = formEspeciesUiState.copy(
            formsEspecieDetails = formEspeciesUiState.formsEspecieDetails.copy(
                quantity = quantity
            )
        )
    }

    suspend fun getLatestFormId(): Int {
        return formRepository.getLatestSpecieFormId()
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
    var evidences: ByteArray = byteArrayOf(),
    var observations: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SpecieFormsDetails

        if (id != other.id) return false
        if (transect != other.transect) return false
        if (idZoneType != other.idZoneType) return false
        if (idAnimalType != other.idAnimalType) return false
        if (animalName != other.animalName) return false
        if (scientificName != other.scientificName) return false
        if (quantity != other.quantity) return false
        if (idObservType != other.idObservType) return false
        if (idHeightType != other.idHeightType) return false
        if (!evidences.contentEquals(other.evidences)) return false
        if (observations != other.observations) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + transect.hashCode()
        result = 31 * result + idZoneType
        result = 31 * result + idAnimalType
        result = 31 * result + animalName.hashCode()
        result = 31 * result + scientificName.hashCode()
        result = 31 * result + quantity
        result = 31 * result + idObservType
        result = 31 * result + idHeightType
        result = 31 * result + evidences.contentHashCode()
        result = 31 * result + observations.hashCode()
        return result
    }
}

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

