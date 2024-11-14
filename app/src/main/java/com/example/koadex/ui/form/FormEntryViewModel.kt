package com.example.koadex.ui.form


import androidx.lifecycle.ViewModel
import com.example.koadex.clases.User
import com.example.koadex.data.FormDao
import com.example.koadex.data.FormDao_Impl
import com.example.koadex.data.FormDatabase
import com.example.koadex.data.FormRepository
import com.example.koadex.data.GeneralFormEntity
import com.example.koadex.data.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.sql.Date
import java.sql.Time

@HiltViewModel
class FormEntryViewModel(private val formRepository: FormRepository,
                         private val formDao: FormDao) : ViewModel()
{
    val formData: Flow<List<GeneralFormEntity>> = formDao.getAllForms()
    val userData: Flow<List<UserEntity>> = formDao.getAllUsers()

    /*
    var formUiState by mutableStateOf(FormUiState())
        private set

    fun updateUiState(formDetails: GeneralFormDetails) {
        formUiState = FormUiState(
            formDetails = formDetails,
            isEntryValid = validateInput(formDetails)
        )
    }

    suspend fun saveForm() {
        if (validateInput()) {
            formRepository.insertForm(formUiState.formDetails.toEntity())
        }
    }

    private fun validateInput(uiState: FormDetails = formUiState.formDetails): Boolean {
        return with(uiState) {
            false // Debe verificar condiciones, faltante
        }
    }*/
}
/*
data class FormUiState(
    val generalFormDetails: GeneralFormDetails = GeneralFormDetails(
        id = 0,
        date = Date(20241111),
        hour = Time(451),
        serialCode = "",
        idUser = 0, // Foreign Key
        idWeather = 0, // Foreign Key
        idSeason = 0, // Foreign Key
        idSpecieForm = 0, // Foreign Key
        idFollowUpForm = 0, // Foreign Key
        idQuadrantForm = 0, // Foreign Key
        idRouteForm = 0, // Foreign Key
        idWeatherForm = 0 // Foreign Key
    ),
    val isEntryValid: Boolean = false
)



// Update the data classes
data class GeneralFormDetails(
    val id: Int = 0,
    val date: Date = Date(20241111),
    val hour: Time = Time(451),
    val serialCode: String = "",
    val idUser: Int, // Foreign Key
    val idWeather: Int, // Foreign Key
    val idSeason: Int, // Foreign Key
    val idSpecieForm: Int, // Foreign Key
    val idFollowUpForm: Int, // Foreign Key
    val idQuadrantForm: Int, // Foreign Key
    val idRouteForm: Int, // Foreign Key
    val idWeatherForm: Int, // Foreign Key
)
// Extension functions to convert FormDetails to FormEntity
fun GeneralFormDetails.toEntity(): GeneralFormEntity = GeneralFormEntity(
    id = id,
    date = date,
    hour = hour,
    serialCode = serialCode,
    idUser = idUser, // Foreign Key
    idWeather = idWeather, // Foreign Key
    idSeason = idSeason, // Foreign Key
    idSpecieForm = idSpecieForm, // Foreign Key
    idFollowUpForm = idFollowUpForm, // Foreign Key
    idQuadrantForm = idQuadrantForm, // Foreign Key
    idRouteForm = idRouteForm, // Foreign Key
    idWeatherForm = idWeatherForm // Foreign Key
)
fun GeneralFormEntity.toFormUiState(isEntryValid: Boolean = false): FormUiState = FormUiState(
    generalFormDetails = this.toFormDetails(),
    isEntryValid = isEntryValid
)
fun GeneralFormEntity.toFormDetails(): GeneralFormDetails = GeneralFormDetails(
    id = id,
    date = date,
    hour = hour,
    serialCode = serialCode,
    idUser = idUser, // Foreign Key
    idWeather = idWeather, // Foreign Key
    idSeason = idSeason, // Foreign Key
    idSpecieForm = idSpecieForm, // Foreign Key
    idFollowUpForm = idFollowUpForm, // Foreign Key
    idQuadrantForm = idQuadrantForm, // Foreign Key
    idRouteForm = idRouteForm, // Foreign Key
    idWeatherForm = idWeatherForm // Foreign Key
)
*/