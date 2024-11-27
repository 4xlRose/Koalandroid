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
    var followUp: Boolean = false,
    var change: Boolean = false,
    var idCoverage: Int = 0, // Foreign Key
    var cropType: String = "",
    var idDisturbance: Int = 0, // Foreign Key
    var evidences: ByteArray = byteArrayOf(),
    var observations: String = "" // <- AGREGO MARIA
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FollowUpFormDetails

        if (id != other.id) return false
        if (followUp != other.followUp) return false
        if (change != other.change) return false
        if (idCoverage != other.idCoverage) return false
        if (cropType != other.cropType) return false
        if (idDisturbance != other.idDisturbance) return false
        if (!evidences.contentEquals(other.evidences)) return false
        if (observations != other.observations) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + followUp.hashCode()
        result = 31 * result + change.hashCode()
        result = 31 * result + idCoverage
        result = 31 * result + cropType.hashCode()
        result = 31 * result + idDisturbance
        result = 31 * result + evidences.contentHashCode()
        result = 31 * result + observations.hashCode()
        return result
    }
}

fun FollowUpFormDetails.toEntity() : FollowUpFormEntity = FollowUpFormEntity (
    followUp = followUp,
    change = change,
    idCoverage = idCoverage,
    cropType = cropType,
    idDisturbance = idDisturbance,
    evidences = evidences,
    observations = observations // <- AGREGO MARIA
)