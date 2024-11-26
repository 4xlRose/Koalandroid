package com.example.koadex.ui.form


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.room.PrimaryKey
import com.example.koadex.data.FormFreeSearchEntity
import com.example.koadex.data.FormRepository


class FormFreeSearchDBViewModel (private val formRepository: FormRepository) : ViewModel() {
    var formFreeSearchUiState by mutableStateOf(FormFreeSearchUiState())


    fun updateFreeSearchFormUiState(formFreeSearch: FormFreeSearchDetails ) {
        formFreeSearchUiState = FormFreeSearchUiState(
            formsFreeSearchDetails = formFreeSearch
        )
    }
    suspend fun saveFreeSearchForm() {
        formRepository.insertFormFreeSearch(formFreeSearchUiState.formsFreeSearchDetails.toEntity())
    }


}


data class FormFreeSearchUiState(
    var formsFreeSearchDetails: FormFreeSearchDetails = FormFreeSearchDetails()
)
data class FormFreeSearchDetails(
    val id: Int = 0,
    var idGeneralForm: Int = 0,
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


fun FormFreeSearchDetails.toEntity() : FormFreeSearchEntity = FormFreeSearchEntity(
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



