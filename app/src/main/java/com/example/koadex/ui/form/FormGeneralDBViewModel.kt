package com.example.koadex.ui.form


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.koadex.R
import com.example.koadex.data.FormRepository
import com.example.koadex.data.FormStateEntity
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

    suspend fun getLatestFormId(): Int {
        return formRepository.getLatestFormId()
    }

    fun updateGeneraFormUiState(formGeneral: GeneralFormsDetails) {
        formGeneralUiState = GeneralFormUiState(
            formsDetails = formGeneral
        )
    }

    suspend fun saveGeneralForm() {
        formRepository.insertGeneralForm(formGeneralUiState.formsDetails.toEntity())
    }

    // Usuario
    var userUiState by mutableStateOf(UserUiState())
        private  set

    fun updateUserUiState(user: UserDetails) {
        userUiState = UserUiState(
            userDetails = user
        )
    }

    suspend fun saveUser() {
        formRepository.updateUser(userUiState.userDetails.toEntity())
    }


    // Formularios locales
    var formStateUiState by mutableStateOf(FormStateUiState())
        private  set

    fun updateFormStateUiState(formState: FormStateDetails) {
        formStateUiState = FormStateUiState(
            formStateDetails = formState
        )
    }

    suspend fun saveFormState() {
        formRepository.insertFormState(formStateUiState.formStateDetails.toEntity())
    }


    fun getWeatherByName(name: String): Flow<WeatherEntity?> {
        return formRepository.getWeatherByName(name)
    }
    fun getSeasonByName(name: String): Flow<SeasonEntity?> {
        return formRepository.getSeasonByName(name)
    }
}

/*Data classes*/
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
    var place: String = "",
    var idSpecieForm: Int = 0, // Foreign Key
    var idFollowUpForm: Int = 0, // Foreign Key
    var idQuadrantForm: Int = 0, // Foreign Key
    var idRouteForm: Int = 0, // Foreign Key
    var idWeatherForm: Int = 0 // Foreign Key
)
fun GeneralFormsDetails.toEntity() : GeneralFormEntity = GeneralFormEntity(
    date = date,
    hour = hour,
    idUser = idUser,
    idWeather = idWeather,
    idSeason = idSeason,
    place = place,
    idSpecieForm = idSpecieForm,
    idFollowUpForm = idFollowUpForm,
    idQuadrantForm = idQuadrantForm,
    idRouteForm = idRouteForm,
    idWeatherForm = idWeatherForm
)


data class UserUiState(
    var userDetails: UserDetails = UserDetails()
)
data class UserDetails(
    val id: Int = 0,
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var phone: String = "",
    var startDate: String = "",
    var idZone: Int = 0, // Foreign Key
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
    phone = phone,
    startDate = startDate,
    idZone = idZone,
    uploadedForms = uploadedForms,
    locallyStoredForms = locallyStoredForms,
    posts = posts,
    following = following,
    followers = followers,
    isloggedIn = isloggedIn,
    profilePicture = profilePicture
)

data class FormStateUiState(
    var formStateDetails: FormStateDetails = FormStateDetails()
)
data class FormStateDetails(
    val id: Int = 0,
    val idUser: Int = 0,
    val idGeneralForm: Int = 0,
    val isUploaded: Boolean = false
)
fun FormStateDetails.toEntity() : FormStateEntity = FormStateEntity(
    id = id,
    idUser = idUser,
    idGeneralForm = idGeneralForm,
    isUploaded = isUploaded
)