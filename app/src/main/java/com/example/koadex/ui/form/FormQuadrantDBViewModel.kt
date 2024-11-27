package com.example.koadex.ui.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.koadex.data.FormRepository
import com.example.koadex.data.QuadrantFormEntity

class FormQuadrantDBViewModel (private val formRepository: FormRepository) : ViewModel() {
    //Formulario Cuadrante
    var formQuadrantUiState by  mutableStateOf(QuadrantFormUiState())
        private set

    fun updateQuadranteUiState(formQuadrant: QuadrantFormDetails) {
        formQuadrantUiState = QuadrantFormUiState(
            formsQuadrantDetails = formQuadrant
        )
    }

    suspend fun saveQuadrantForm() {
        formRepository.insertQuadrantForm(formQuadrantUiState.formsQuadrantDetails.toEntity())

    }

    suspend fun getLatestFormId(): Int {
        return formRepository.getLatestSpecieFormId()
    }
}

/*Formulario Cuadrante*/
data class QuadrantFormUiState(
    var formsQuadrantDetails: QuadrantFormDetails= QuadrantFormDetails()
)

data class QuadrantFormDetails(
    val id: Int = 0,
    var idSuperQuadrant: Int = 0, // Foreign Key
    var idMidQuadrant: Int = 0, // Foreign Key
    var idSubQuadrant: Int = 0, // Foreign Key
    var specieName: String = "",
    var scientificName: String = "",
    var placa  : String = "",
    var idHabitat: Int = 0, // Foreign Key
    var circumference: Int = 0, // decimal(2)
    var biomonitorMtSize: Int = 0, // decimal(2)
    var distanceMt: Int = 0, // decimal(2)
    var observations: String = "",
    var heightMt: Int = 0, // decimal(2)
    var evidences: ByteArray = byteArrayOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as QuadrantFormDetails

        if (id != other.id) return false
        if (idSuperQuadrant != other.idSuperQuadrant) return false
        if (idMidQuadrant != other.idMidQuadrant) return false
        if (idSubQuadrant != other.idSubQuadrant) return false
        if (specieName != other.specieName) return false
        if (scientificName != other.scientificName) return false
        if (placa != other.placa) return false
        if (idHabitat != other.idHabitat) return false
        if (circumference != other.circumference) return false
        if (biomonitorMtSize != other.biomonitorMtSize) return false
        if (distanceMt != other.distanceMt) return false
        if (observations != other.observations) return false
        if (heightMt != other.heightMt) return false
        if (!evidences.contentEquals(other.evidences)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + idSuperQuadrant
        result = 31 * result + idMidQuadrant
        result = 31 * result + idSubQuadrant
        result = 31 * result + specieName.hashCode()
        result = 31 * result + scientificName.hashCode()
        result = 31 * result + placa.hashCode()
        result = 31 * result + idHabitat
        result = 31 * result + circumference
        result = 31 * result + biomonitorMtSize
        result = 31 * result + distanceMt
        result = 31 * result + observations.hashCode()
        result = 31 * result + heightMt
        result = 31 * result + evidences.contentHashCode()
        return result
    }
}

fun QuadrantFormDetails.toEntity() : QuadrantFormEntity = QuadrantFormEntity(
    idSuperQuadrant = idSuperQuadrant,
    idMidQuadrant = idMidQuadrant,
    idSubQuadrant = idSubQuadrant,
    specieName = specieName,
    scientificName = scientificName,
    placa = placa,
    idHabitat = idHabitat,
    circumference = circumference,
    biomonitorMtSize = biomonitorMtSize,
    distanceMt = distanceMt,
    observations =  observations,
    heightMt = heightMt,
    evidences = evidences
)