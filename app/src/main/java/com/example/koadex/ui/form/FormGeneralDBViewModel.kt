package com.example.koadex.ui.form


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.koadex.data.FormRepository
import com.example.koadex.data.GeneralFormEntity

class FormGeneralDBViewModel (private val formRepository: FormRepository) : ViewModel()
{
    //Formulario General
    var formGeneralUiState by mutableStateOf(GeneralFormUiState())
        private  set

    fun updateGeneraFormlUiState(formGeneral: GeneralFormsDetails) {
        formGeneralUiState = GeneralFormUiState(
            formsDetails = formGeneral
        )
    }

    suspend fun saveGeneralForm() {
        formRepository.insertGeneralForm(formGeneralUiState.formsDetails.toEntity())
    }



}
/*General form*/
data class GeneralFormUiState(
    var formsDetails: GeneralFormsDetails = GeneralFormsDetails()
)
data class GeneralFormsDetails(
    val id: Int = 0,
    var date: String = "",
    var hour: String = "",
    var idUser: Int = 0, // Foreign Key
    var idWeather: Int = 0, // Foreign Key
    var idSeason: Int = 0, // Foreign Key
    var place: String = ""
)
fun GeneralFormsDetails.toEntity() : GeneralFormEntity = GeneralFormEntity(
    date = date,
    hour = hour,
    idUser = idUser,
    idWeather = idWeather,
    idSeason = idSeason,
    place = place
)
