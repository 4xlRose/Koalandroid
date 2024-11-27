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
    var idHabitat: Int = 0, // Foreign Key
    var circumference: Int = 0, // decimal(2)
    var biomonitorMtSize: Int = 0, // decimal(2)
    var distanceMt: Int = 0, // decimal(2)
    var observations: String = "",
    var heightMt: Int = 0, // decimal(2)
    var evidences: ByteArray = byteArrayOf()
)

fun QuadrantFormDetails.toEntity() : QuadrantFormEntity = QuadrantFormEntity(
    idSuperQuadrant = idSuperQuadrant,
    idMidQuadrant = idMidQuadrant,
    idSubQuadrant = idSubQuadrant,
    specieName = specieName,
    scientificName = scientificName,
    idHabitat = idHabitat,
    circumference = circumference,
    biomonitorMtSize = biomonitorMtSize,
    distanceMt = distanceMt,
    observations =  observations,
    heightMt = heightMt,
    evidences = evidences
)