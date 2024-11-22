package com.example.koadex.ui.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.room.PrimaryKey
import com.example.koadex.data.FollowUpFormEntity
import com.example.koadex.data.FormRepository

class FormFollowDBViewModel (private val formRepository: FormRepository) : ViewModel() {
    //Formulario de seguimiento
    var formFollowUiState by mutableStateOf(FollowUpFormUiState())

    fun updateFollowUpFormUiState(formFollow: FollowUpFormDetails ) {
        formFollowUiState = FollowUpFormUiState(
            formsFollowUpDetails = formFollow
        )
    }
    suspend fun saveFollowForm() {
        formRepository.insertFollowUpForm(formFollowUiState.formsFollowUpDetails.toEntity())
    }

}

data class FollowUpFormUiState(
    var formsFollowUpDetails: FollowUpFormDetails = FollowUpFormDetails()
)

data class FollowUpFormDetails(
    val id: Int = 0,
    var idGeneralForm: Int = 0, // Foreign Key
    var followUp: Boolean = false,
    var change: Boolean = false,
    var idCoverage: Int = 0, // Foreign Key
    var cropType: String = "",
    var idDisturbance: Int = 0, // Foreign Key
    var evidences: String = ""
)

fun FollowUpFormDetails.toEntity() : FollowUpFormEntity = FollowUpFormEntity (
    idGeneralForm = idGeneralForm,
    followUp = followUp,
    change = change,
    idCoverage = idCoverage,
    cropType = cropType,
    idDisturbance = idDisturbance,
    evidences = evidences
)