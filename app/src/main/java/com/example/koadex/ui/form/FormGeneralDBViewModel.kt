package com.example.koadex.ui.form


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.koadex.data.FormRepository
import com.example.koadex.data.GeneralFormEntity
import com.example.koadex.data.UserEntity
import kotlinx.coroutines.flow.Flow

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

    suspend fun insertUser(user: UserEntity) {
        formRepository.insertUser(user)
    }
    suspend fun updateUser(user: UserEntity) {
        formRepository.updateUser(user)
    }
    suspend fun deleteUser(user: UserEntity) {
        formRepository.deleteUser(user)
    }
    fun getAllUsers(): List<UserEntity> {
        return formRepository.getAllUsers()
    }
    fun getUserById(id: Int): UserEntity? {
        return formRepository.getUserById(id)
    }
    fun getUserByName(name: String): UserEntity? {
        return formRepository.getUserByName(name)
    }
    fun getAllAccountsByName(name: String): List<UserEntity> {
        return formRepository.getAllAccountsByName(name)
    }
    fun getUserByEmail(email: String): UserEntity? {
        return formRepository.getUserByEmail(email)
    }
    fun getAllAccountsByEmail(email: String): List<UserEntity> {
        return formRepository.getAllAccountsByEmail(email)
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