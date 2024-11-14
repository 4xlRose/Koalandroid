package com.example.koadex.ui.form


import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.koadex.R
import com.example.koadex.clases.User
import com.example.koadex.data.AnimalTypeEntity
import com.example.koadex.data.CoverageEntity
import com.example.koadex.data.DisturbanceEntity
import com.example.koadex.data.FollowUpFormEntity
import com.example.koadex.data.FormDao
import com.example.koadex.data.FormRepository
import com.example.koadex.data.GeneralFormEntity
import com.example.koadex.data.HeightTypeEntity
import com.example.koadex.data.ObservTypeEntity
import com.example.koadex.data.SeasonEntity
import com.example.koadex.data.SpecieFormEntity
import com.example.koadex.data.UserEntity
import com.example.koadex.data.WeatherEntity
import com.example.koadex.data.ZoneEntity
import com.example.koadex.data.ZoneTypeEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import java.sql.Date
import java.sql.Time
import javax.inject.Inject

@HiltViewModel
class FormEntryViewModel @Inject constructor(private val formRepository: FormRepository) : ViewModel()
{
    // General Form
    var generalFormUiState by mutableStateOf(GeneralFormUiState())
    fun generalFormUpdateUiState(formDetails: GeneralFormDetails) {
        generalFormUiState = GeneralFormUiState(
            formDetails = formDetails,
            isEntryValid = validateGeneralFormInput(formDetails)
        )
    }
    suspend fun saveGeneralForm() {
        if (validateGeneralFormInput()) {
            formRepository.insertGeneralForm(generalFormUiState.formDetails.toEntity())
        }
    }
    private fun validateGeneralFormInput(uiState: GeneralFormDetails = generalFormUiState.formDetails): Boolean {
        return with(uiState) {
            date >= Date(19000101) &&
                    date <= Date(20250101) &&
                    hour >= Time(0) &&
                    hour <= Time(2359) &&
                    serialCode.isNotEmpty() &&
                    idUser >= 0 &&
                    idWeather >= 0 &&
                    idSeason >= 0 &&
                    idSpecieForm >= 0 &&
                    idFollowUpForm >= 0 &&
                    idQuadrantForm >= 0 &&
                    idRouteForm >= 0 &&
                    idWeatherForm >= 0
        }
    }


    // Specie Form
    var specieFormUiState by mutableStateOf(SpecieFormUiState())
    fun specieFormUpdateUiState(formDetails: SpecieFormDetails) {
        specieFormUiState = SpecieFormUiState(
            formDetails = formDetails,
            isEntryValid = validateSpecieFormInput(formDetails)
        )
    }
    suspend fun saveSpecieForm() {
        if (validateSpecieFormInput()) {
            formRepository.insertSpecieForm(specieFormUiState.formDetails.toEntity())
        }
    }
    private fun validateSpecieFormInput(uiState: SpecieFormDetails = specieFormUiState.formDetails): Boolean {
        return with(uiState) {
            idGeneralForm >= 0 &&
                    transect.isNotEmpty() &&
                    idZoneType >= 0 &&
                    idAnimalType >= 0 &&
                    animalName.isNotEmpty() &&
                    scientificName.isNotEmpty() &&
                    quantity >= 0 &&
                    idObservType >= 0 &&
                    idHeightType >= 0 &&
                    evidences != Icons.Filled.Apps &&
                    observations.isNotEmpty()
        }
    }

    // Follow Up Form
    var followUpFormUiState by mutableStateOf(FollowUpFormUiState())
    fun followUpFormUpdateUiState(formDetails: FollowUpFormDetails) {
        followUpFormUiState = FollowUpFormUiState(
            formDetails = formDetails,
            isEntryValid = validateFollowUpFormInput(formDetails)
        )
    }
    suspend fun saveFollowUpForm() {
        if (validateFollowUpFormInput()) {
            formRepository.insertFollowUpForm(followUpFormUiState.formDetails.toEntity())
        }
    }
    private fun validateFollowUpFormInput(uiState: FollowUpFormDetails = followUpFormUiState.formDetails): Boolean {
        return with(uiState) {
            idGeneralForm >= 0 &&
                    followUp &&
                    change &&
                    idCoverage >= 0 &&
                    cropType.isNotEmpty() &&
                    idDisturbance >= 0 &&
                    evidences != Icons.Filled.Apps
        }
    }
}


data class GeneralFormUiState(
    val formDetails: GeneralFormDetails = GeneralFormDetails(
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
    var date: Date = Date(20241111),
    var hour: Time = Time(451),
    var serialCode: String = "",
    var idUser: Int, // Foreign Key
    var idWeather: Int, // Foreign Key
    var idSeason: Int, // Foreign Key
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
fun GeneralFormEntity.toFormUiState(isEntryValid: Boolean = false): GeneralFormUiState = GeneralFormUiState(
    formDetails = this.toFormDetails(),
    isEntryValid = isEntryValid
)



data class UserDetails(
    val id: Int = 0,
    var name: String = "",
    var email: String = "",
    var password: String = "",
    val startDate: Date = Date(20241111),
    var idZone: Int, // Foreign Key
)
fun UserDetails.toEntity(): UserEntity = UserEntity(
    id = id,
    name = name,
    email = email,
    password = password,
    startDate = startDate,
    idZone = idZone, // Foreign Key
)
fun UserEntity.toFormDetails(): UserDetails = UserDetails(
    id = id,
    name = name,
    email = email,
    password = password,
    startDate = startDate,
    idZone = idZone, // Foreign Key
)



data class WeatherDetails(
    val id: Int = 0,
    val weather: String = ""
)
fun WeatherDetails.toEntity(): WeatherEntity = WeatherEntity(
    id = id,
    weather = weather
)
fun WeatherEntity.toFormDetails(): WeatherDetails = WeatherDetails(
    id = id,
    weather = weather
)



data class SeasonDetails(
    val id: Int = 0,
    val season: String = ""
)
fun SeasonDetails.toEntity(): SeasonEntity = SeasonEntity(
    id = id,
    season = season
)
fun SeasonEntity.toFormDetails(): SeasonDetails = SeasonDetails(
    id = id,
    season = season
)



data class ZoneDetails(
    val id: Int = 0,
    val zone: String = ""
)
fun ZoneDetails.toEntity(): ZoneEntity = ZoneEntity(
    id = id,
    zone = zone
)
fun ZoneEntity.toFormDetails(): ZoneDetails = ZoneDetails(
    id = id,
    zone = zone
)



data class SpecieFormUiState(
    val formDetails: SpecieFormDetails = SpecieFormDetails(
        id = 0,
        idGeneralForm = 0, // Foreign Key
        transect = "",
        idZoneType = 0, // Foreign Key
        idAnimalType = 0, // Foreign Key
        animalName = "",
        scientificName = "",
        quantity = 0,
        idObservType = 0, // Foreign Key
        idHeightType = 0, // Foreign Key
        evidences = Icons.Filled.Apps,
        observations = ""
    ),
    val isEntryValid: Boolean = false
)
data class SpecieFormDetails(
    val id: Int = 0,
    val idGeneralForm: Int, // Foreign Key
    var transect: String = "",
    var idZoneType: Int, // Foreign Key
    var idAnimalType: Int, // Foreign Key
    var animalName: String = "",
    var scientificName: String = "",
    var quantity: Int = 0,
    var idObservType: Int, // Foreign Key
    var idHeightType: Int, // Foreign Key
    var evidences: ImageVector,
    var observations: String
)
fun SpecieFormDetails.toEntity(): SpecieFormEntity = SpecieFormEntity(
    id = id,
    idGeneralForm = idGeneralForm, // Foreign Key
    transect = transect,
    idZoneType = idZoneType, // Foreign Key
    idAnimalType = idAnimalType, // Foreign Key
    animalName = animalName,
    scientificName = scientificName,
    quantity = quantity,
    idObservType = idObservType, // Foreign Key
    idHeightType = idHeightType, // Foreign Key
    evidences = evidences,
    observations = observations
)
fun SpecieFormEntity.toFormDetails(): SpecieFormDetails = SpecieFormDetails(
    id = id,
    idGeneralForm = idGeneralForm, // Foreign Key
    transect = transect,
    idZoneType = idZoneType, // Foreign Key
    idAnimalType = idAnimalType, // Foreign Key
    animalName = animalName,
    scientificName = scientificName,
    quantity = quantity,
    idObservType = idObservType, // Foreign Key
    idHeightType = idHeightType, // Foreign Key
    evidences = evidences,
    observations = observations
)
fun SpecieFormEntity.toFormUiState(isEntryValid: Boolean = false): SpecieFormUiState = SpecieFormUiState(
    formDetails = this.toFormDetails(),
    isEntryValid = isEntryValid
)



data class ZoneTypeDetails(
    val id: Int = 0,
    val zoneType: String = ""
)
fun ZoneTypeDetails.toEntity(): ZoneTypeEntity = ZoneTypeEntity(
    id = id,
    zoneType = zoneType
)
fun ZoneTypeEntity.toFormDetails(): ZoneTypeDetails = ZoneTypeDetails(
    id = id,
    zoneType = zoneType
)



data class AnimalTypeDetails(
    val id: Int = 0,
    val animalType: String = ""
)
fun AnimalTypeDetails.toEntity(): AnimalTypeEntity = AnimalTypeEntity(
    id = id,
    animalType = animalType
)
fun AnimalTypeEntity.toFormDetails(): AnimalTypeDetails = AnimalTypeDetails(
    id = id,
    animalType = animalType
)



data class ObservTypeDetails(
    val id: Int = 0,
    val observType: String = ""
)
fun ObservTypeDetails.toEntity(): ObservTypeEntity = ObservTypeEntity(
    id = id,
    observType = observType
)
fun ObservTypeEntity.toFormDetails(): ObservTypeDetails = ObservTypeDetails(
    id = id,
    observType = observType
)



data class HeightTypeDetails(
    val id: Int = 0,
    val heightType: String = ""
)
fun HeightTypeDetails.toEntity(): HeightTypeEntity = HeightTypeEntity(
    id = id,
    heightType = heightType
)
fun HeightTypeEntity.toFormDetails(): HeightTypeDetails = HeightTypeDetails(
    id = id,
    heightType = heightType
)



data class FollowUpFormUiState(
    val formDetails: FollowUpFormDetails = FollowUpFormDetails(
        id = 0,
        idGeneralForm = 0, // Foreign Key
        followUp = false,
        change = false,
        idCoverage = 0, // Foreign Key
        cropType = "",
        idDisturbance = 0, // Foreign Key
        evidences = Icons.Filled.Apps
    ),
    val isEntryValid: Boolean = false
)
data class FollowUpFormDetails(
    val id: Int = 0,
    val idGeneralForm: Int, // Foreign Key
    var followUp: Boolean = false,
    var change: Boolean = false,
    var idCoverage: Int, // Foreign Key
    var cropType: String = "",
    var idDisturbance: Int, // Foreign Key
    var evidences: ImageVector
)
fun FollowUpFormDetails.toEntity(): FollowUpFormEntity = FollowUpFormEntity(
    id = 0,
    idGeneralForm = 0, // Foreign Key
    followUp = false,
    change = false,
    idCoverage = 0, // Foreign Key
    cropType = "",
    idDisturbance = 0, // Foreign Key
    evidences = Icons.Filled.Apps
)
fun FollowUpFormEntity.toFormDetails(): FollowUpFormDetails = FollowUpFormDetails(
    id = 0,
    idGeneralForm = 0, // Foreign Key
    followUp = false,
    change = false,
    idCoverage = 0, // Foreign Key
    cropType = "",
    idDisturbance = 0, // Foreign Key
    evidences = Icons.Filled.Apps
)
fun FollowUpFormEntity.toFormUiState(isEntryValid: Boolean = false): FollowUpFormUiState = FollowUpFormUiState(
    formDetails = this.toFormDetails(),
    isEntryValid = isEntryValid
)



data class CoverageDetails(
    val id: Int = 0,
    val coverage: String = "" // char(2)
)
fun CoverageDetails.toEntity(): CoverageEntity = CoverageEntity(
    id = id,
    coverage = coverage
)
fun CoverageEntity.toFormDetails(): CoverageDetails = CoverageDetails(
    id = id,
    coverage = coverage
)



data class DisturbanceDetails(
    val id: Int = 0,
    val disturbance: String = ""
)
fun DisturbanceDetails.toEntity(): DisturbanceEntity = DisturbanceEntity(
    id = id,
    disturbance = disturbance
)
fun DisturbanceEntity.toFormDetails(): DisturbanceDetails = DisturbanceDetails(
    id = id,
    disturbance = disturbance
)