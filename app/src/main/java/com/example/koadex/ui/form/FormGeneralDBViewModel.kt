package com.example.koadex.ui.form


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.koadex.R
import com.example.koadex.data.FormRepository
import com.example.koadex.data.GeneralFormEntity
import com.example.koadex.data.SeasonEntity
import com.example.koadex.data.UserEntity
import com.example.koadex.data.WeatherEntity
import kotlinx.coroutines.flow.Flow

class FormGeneralDBViewModel (private val formRepository: FormRepository) : ViewModel()
{
    //Formulario General
    var formGeneralUiState by mutableStateOf(GeneralFormUiState())
        private  set

    fun updateGeneraFormUiState(formGeneral: GeneralFormsDetails) {
        formGeneralUiState = GeneralFormUiState(
            formsDetails = formGeneral
        )
    }

    suspend fun saveGeneralForm() {
        formRepository.insertGeneralForm(formGeneralUiState.formsDetails.toEntity())
    }

    //Usuario
    var userUiState by mutableStateOf(UserUiState())
        private  set

    suspend fun insertUser(user: UserEntity) {
        formRepository.insertUser(user)
    }
    suspend fun updateUser(user: UserEntity) {
        formRepository.updateUser(user)
    }
    suspend fun deleteUser(user: UserEntity) {
        formRepository.deleteUser(user)
    }
    fun getAllUsers(): Flow<List<UserEntity>> {
        return formRepository.getAllUsers()
    }
    fun getUserById(id: Int): Flow<UserEntity?> {
        return formRepository.getUserById(id)
    }
    fun updateUserUiState(user: UserDetails) {
        userUiState = UserUiState(
            userDetails = user
        )
    }


    fun getWeatherById(id: Int): Flow<WeatherEntity?> {
        return formRepository.getWeatherById(id)
    }
    fun getSeasonById(id: Int): Flow<SeasonEntity?> {
        return formRepository.getSeasonById(id)
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

/*User*/
data class UserUiState(
    var userDetails: UserDetails = UserDetails()
)
data class UserDetails(
    val id: Int = 0,
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var startDate: String = "",
    var idZone: Int = 0, // Foreign Key
    var totalForms: Int = 0,
    var uploadedForms: Int = 0,
    var locallyStoredForms: Int = 0,
    var posts: Int = 0,
    var following: Int = 0,
    var followers: Int = 0,
    var isloggedIn: Boolean = true,
    var profilePicture: Int = R.drawable.profilepicture
)
fun UserDetails.toEntity() : UserEntity = UserEntity(
    id = id,
    name = name,
    email = email,
    password = password,
    startDate = startDate,
    idZone = idZone,
    totalForms = totalForms,
    uploadedForms = uploadedForms,
    locallyStoredForms = locallyStoredForms,
    posts = posts,
    following = following,
    followers = followers,
    isloggedIn = isloggedIn,
    profilePicture = profilePicture
)