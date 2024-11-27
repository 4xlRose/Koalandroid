package com.example.koadex.ui.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.koadex.data.FormRepository
import com.example.koadex.data.PuntoConteoEntity

class FormPuntoConteoDBViewModel (private val formRepository: FormRepository) : ViewModel() {
    var puntoConteoUiState by mutableStateOf(PuntoConteoUiState())
        private  set

    fun updatePuntoConteoUiState(puntoConteo: PuntoConteoDetails) {
        puntoConteoUiState = PuntoConteoUiState(
            puntoConteoDetails = puntoConteo
        )
    }

    suspend fun savePuntoConteo() {
        formRepository.insertPuntoConteo(puntoConteoUiState.puntoConteoDetails.toEntity())
    }

}

data class PuntoConteoUiState(
    var puntoConteoDetails: PuntoConteoDetails = PuntoConteoDetails()
)
data class PuntoConteoDetails(
    val id: Int = 0,
    var idZoneType: Int = 0, // Foreign Key
    var idAnimalType: Int = 0, // Foreign Key
    var animalName: String = "",
    var scientificName: String = "",
    var quantity: Int = 0,
    var idObservType: Int = 0, // Foreign Key
    var idHeightType: Int= 0, // Foreign Key
    var evidences: ByteArray = byteArrayOf(),
    var observations: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PuntoConteoDetails

        if (id != other.id) return false
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

fun PuntoConteoDetails.toEntity() : PuntoConteoEntity = PuntoConteoEntity(
    id = id,
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