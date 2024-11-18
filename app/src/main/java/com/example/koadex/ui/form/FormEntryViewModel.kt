package com.example.koadex.ui.form


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.example.koadex.data.FormEntity
/*
import com.example.koadex.data.AnimalTypeEntity
import com.example.koadex.data.CameraEntity
import com.example.koadex.data.CheckEntity
import com.example.koadex.data.CheckListEntity
import com.example.koadex.data.CoverageEntity
import com.example.koadex.data.DisturbanceEntity
import com.example.koadex.data.FollowUpFormEntity*/
import com.example.koadex.data.FormRepository
/*
import com.example.koadex.data.GeneralFormEntity
import com.example.koadex.data.HabitatEntity
import com.example.koadex.data.HeightTypeEntity
import com.example.koadex.data.MidQuadrantEntity
import com.example.koadex.data.ObservTypeEntity
import com.example.koadex.data.QuadrantFormEntity
import com.example.koadex.data.RouteFormEntity
import com.example.koadex.data.SeasonEntity
import com.example.koadex.data.SpecieFormEntity
import com.example.koadex.data.SubQuadrantEntity
import com.example.koadex.data.SuperQuadrantEntity
import com.example.koadex.data.UserEntity
import com.example.koadex.data.WeatherEntity
import com.example.koadex.data.WeatherFormEntity
import com.example.koadex.data.ZoneEntity
import com.example.koadex.data.ZoneTypeEntity*/
import dagger.hilt.android.lifecycle.HiltViewModel
import java.sql.Date
import java.sql.Time
import javax.inject.Inject


class FormEntryViewModel(private val formRepository: FormRepository) : ViewModel() {

    var formUiState by mutableStateOf(FormUiState())
        private set


    fun updateUiState(formDetails: FormDetails) {
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
            name.isNotBlank() && date.isNotBlank() && place.isNotBlank() && hour.isNotBlank()
        }
    }
}
data class FormUiState(
    val formDetails: FormDetails = FormDetails(),
    val isEntryValid: Boolean = false
)

// 6. Update the data classes
data class FormDetails(
    val id: Int = 0,
    val name: String = "",
    val date: String = "",
    val place: String = "",
    val hour: String = "",
    val weather: String = "",
    val season: String = ""
)



// Extension function to convert FormDetails to FormEntity
fun FormDetails.toEntity(): FormEntity = FormEntity(
    name = name,
    date = date,
    place = place,
    hour = hour,
    weather = weather,
    season = season
)

fun FormEntity.toFormUiState(isEntryValid: Boolean = false): FormUiState = FormUiState(
    formDetails = this.toFormDetails(),
    isEntryValid = isEntryValid
)

fun FormEntity.toFormDetails(): FormDetails = FormDetails(
    name = name,
    date = date,
    place = place,
    hour = hour,
    weather = weather
)
/*
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

    // Quadrant Form
    var quadrantFormUiState by mutableStateOf(QuadrantFormUiState())
    fun quadrantFormUpdateUiState(formDetails: QuadrantFormDetails) {
        quadrantFormUiState = QuadrantFormUiState(
            formDetails = formDetails,
            isEntryValid = validateQuadrantFormInput(formDetails)
        )
    }
    suspend fun saveQuadrantForm() {
        if (validateQuadrantFormInput()) {
            formRepository.insertQuadrantForm(quadrantFormUiState.formDetails.toEntity())
        }
    }
    private fun validateQuadrantFormInput(uiState: QuadrantFormDetails = quadrantFormUiState.formDetails): Boolean {
        return with(uiState) {
            idGeneralForm >= 0 &&
                    idSuperQuadrant >= 0 &&
                    idMidQuadrant >= 0 &&
                    idSubQuadrant >= 0 &&
                    specieName.isNotEmpty() &&
                    scientificName.isNotEmpty() &&
                    idHabitat >= 0 &&
                    circumference >= 0 &&
                    biomonitorMtSize >= 0 &&
                    distanceMt >= 0 &&
                    observations.isNotEmpty() &&
                    heightMt >= 0 &&
                    evidences != Icons.Filled.Apps
        }
    }

    // Route Form
    var routeFormUiState by mutableStateOf(RouteFormUiState())
    fun routeFormUpdateUiState(formDetails: RouteFormDetails) {
        routeFormUiState = RouteFormUiState(
            formDetails = formDetails,
            isEntryValid = validateRouteFormInput(formDetails)
        )
    }
    suspend fun saveRouteForm() {
        if (validateRouteFormInput()) {
            formRepository.insertRouteForm(routeFormUiState.formDetails.toEntity())
        }
    }
    private fun validateRouteFormInput(uiState: RouteFormDetails = routeFormUiState.formDetails): Boolean {
        return with(uiState) {
            idGeneralForm >= 0 &&
                    idZoneType >= 0 &&
                    idCamera >= 0 &&
                    guayaPlate >= 0 &&
                    routeWidth >= 0 &&
                    targetDistance >= 0 &&
                    lensHeight >= 0 &&
                    idCheckList >= 0 &&
                    evidences != Icons.Filled.Apps &&
                    observations.isNotEmpty()
        }
    }


    // Weather Form
    var weatherFormUiState by mutableStateOf(WeatherFormUiState())
    fun weatherFormUpdateUiState(formDetails: WeatherFormDetails) {
        weatherFormUiState = WeatherFormUiState(
            formDetails = formDetails,
            isEntryValid = validateWeatherFormInput(formDetails)
        )
    }
    suspend fun saveWeatherForm() {
        if (validateWeatherFormInput()) {
            formRepository.insertWeatherForm(weatherFormUiState.formDetails.toEntity())
        }
    }
    private fun validateWeatherFormInput(uiState: WeatherFormDetails = weatherFormUiState.formDetails): Boolean {
        return with(uiState) {
            id >= 0 &&
                    idGeneralForm >= 0 &&
                    idZoneType >= 0 &&
                    rainfall >= 0 &&
                    maxTemperature >= 0 &&
                    maxHumidity >= 0 &&
                    minTemperature >= 0 &&
                    streamMtLevel >= 0
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



data class QuadrantFormUiState(
    val formDetails: QuadrantFormDetails = QuadrantFormDetails(
        id = 0,
        idGeneralForm = 0, // Foreign Key
        idSuperQuadrant = 0, // Foreign Key
        idMidQuadrant = 0, // Foreign Key
        idSubQuadrant = 0, // Foreign Key
        specieName = "",
        scientificName = "",
        idHabitat = 0, // Foreign Key
        circumference = 0, // decimal(2)
        biomonitorMtSize = 0, // decimal(2)
        distanceMt = 0, // decimal(2)
        observations = "",
        heightMt = 0, // decimal(2)
        evidences = Icons.Filled.Apps
    ),
    val isEntryValid: Boolean = false
)
data class QuadrantFormDetails(
    val id: Int = 0,
    val idGeneralForm: Int, // Foreign Key
    val idSuperQuadrant: Int, // Foreign Key
    val idMidQuadrant: Int, // Foreign Key
    val idSubQuadrant: Int, // Foreign Key
    val specieName: String = "",
    val scientificName: String = "",
    val idHabitat: Int, // Foreign Key
    val circumference: Int = 0, // decimal(2)
    val biomonitorMtSize: Int = 0, // decimal(2)
    val distanceMt: Int = 0, // decimal(2)
    val observations: String = "",
    val heightMt: Int = 0, // decimal(2)
    val evidences: ImageVector
)
fun QuadrantFormDetails.toEntity(): QuadrantFormEntity = QuadrantFormEntity(
    id = 0,
    idGeneralForm = 0, // Foreign Key
    idSuperQuadrant = 0, // Foreign Key
    idMidQuadrant = 0, // Foreign Key
    idSubQuadrant = 0, // Foreign Key
    specieName = "",
    scientificName = "",
    idHabitat = 0, // Foreign Key
    circumference = 0, // decimal(2)
    biomonitorMtSize = 0, // decimal(2)
    distanceMt = 0, // decimal(2)
    observations = "",
    heightMt = 0, // decimal(2)
    evidences = Icons.Filled.Apps
)
fun QuadrantFormEntity.toFormDetails(): QuadrantFormDetails = QuadrantFormDetails(
    id = 0,
    idGeneralForm = 0, // Foreign Key
    idSuperQuadrant = 0, // Foreign Key
    idMidQuadrant = 0, // Foreign Key
    idSubQuadrant = 0, // Foreign Key
    specieName = "",
    scientificName = "",
    idHabitat = 0, // Foreign Key
    circumference = 0, // decimal(2)
    biomonitorMtSize = 0, // decimal(2)
    distanceMt = 0, // decimal(2)
    observations = "",
    heightMt = 0, // decimal(2)
    evidences = Icons.Filled.Apps
)
fun QuadrantFormEntity.toFormUiState(isEntryValid: Boolean = false): QuadrantFormUiState = QuadrantFormUiState(
    formDetails = this.toFormDetails(),
    isEntryValid = isEntryValid
)



data class SuperQuadrantDetails(
    val id: Int = 0,
    val quadrant: Char = ' '
)
fun SuperQuadrantDetails.toEntity(): SuperQuadrantEntity = SuperQuadrantEntity(
    id = id,
    quadrant = quadrant
)
fun SuperQuadrantEntity.toFormDetails(): SuperQuadrantDetails = SuperQuadrantDetails(
    id = id,
    quadrant = quadrant
)



data class MidQuadrantDetails(
    val id: Int = 0,
    val quadrant: Char = ' '
)
fun MidQuadrantDetails.toEntity(): MidQuadrantEntity = MidQuadrantEntity(
    id = id,
    quadrant = quadrant
)
fun MidQuadrantEntity.toFormDetails(): MidQuadrantDetails = MidQuadrantDetails(
    id = id,
    quadrant = quadrant
)



data class SubQuadrantDetails(
    val id: Int = 0,
    val subQuadrant: Char = ' '
)
fun SubQuadrantDetails.toEntity(): SubQuadrantEntity = SubQuadrantEntity(
    id = id,
    subQuadrant = subQuadrant
)
fun SubQuadrantEntity.toFormDetails(): SubQuadrantDetails = SubQuadrantDetails(
    id = id,
    subQuadrant = subQuadrant
)



data class HabitatDetails(
    val id: Int = 0,
    val habitatType: String = ""
)
fun HabitatDetails.toEntity(): HabitatEntity = HabitatEntity(
    id = id,
    habitatType = habitatType
)
fun HabitatEntity.toFormDetails(): HabitatDetails = HabitatDetails(
    id = id,
    habitatType = habitatType
)



data class RouteFormUiState(
    val formDetails: RouteFormDetails = RouteFormDetails(
        id = 0,
        idGeneralForm = 0, // Foreign Key
        idZoneType = 0, // Foreign Key
        idCamera = 0, // Foreign Key
        guayaPlate = 0,
        routeWidth = 0, // decimal(2)
        targetDistance = 0, // decimal(2)
        lensHeight = 0, // decimal(2)
        idCheckList = 0, // Foreign Key
        evidences = Icons.Filled.Apps,
        observations = ""
    ),
    val isEntryValid: Boolean = false
)
data class RouteFormDetails(
    val id: Int = 0,
    val idGeneralForm: Int, // Foreign Key
    val idZoneType: Int, // Foreign Key
    val idCamera: Int, // Foreign Key
    val guayaPlate: Int = 0,
    val routeWidth: Int = 0, // decimal(2)
    val targetDistance: Int = 0, // decimal(2)
    val lensHeight: Int = 0, // decimal(2)
    val idCheckList: Int, // Foreign Key
    val evidences: ImageVector,
    val observations: String = ""
)
fun RouteFormDetails.toEntity(): RouteFormEntity = RouteFormEntity(
    id = 0,
    idGeneralForm = 0, // Foreign Key
    idZoneType = 0, // Foreign Key
    idCamera = 0, // Foreign Key
    guayaPlate = 0,
    routeWidth = 0, // decimal(2)
    targetDistance = 0, // decimal(2)
    lensHeight = 0, // decimal(2)
    idCheckList = 0, // Foreign Key
    evidences = Icons.Filled.Apps,
    observations = ""
)
fun RouteFormEntity.toFormDetails(): RouteFormDetails = RouteFormDetails(
    id = 0,
    idGeneralForm = 0, // Foreign Key
    idZoneType = 0, // Foreign Key
    idCamera = 0, // Foreign Key
    guayaPlate = 0,
    routeWidth = 0, // decimal(2)
    targetDistance = 0, // decimal(2)
    lensHeight = 0, // decimal(2)
    idCheckList = 0, // Foreign Key
    evidences = Icons.Filled.Apps,
    observations = ""
)
fun RouteFormEntity.toFormUiState(isEntryValid: Boolean = false): RouteFormUiState = RouteFormUiState(
    formDetails = this.toFormDetails(),
    isEntryValid = isEntryValid
)



data class CameraDetails(
    val id: Int = 0,
    val name: String = "",
    val plate: Int = 0,
    val instalationDate: Date = Date(20241111)
)
fun CameraDetails.toEntity(): CameraEntity = CameraEntity(
    id = id,
    name = name,
    plate = plate,
    instalationDate = instalationDate
)
fun CameraEntity.toFormDetails(): CameraDetails = CameraDetails(
    id = id,
    name = name,
    plate = plate,
    instalationDate = instalationDate
)



data class CheckListDetails(
    val id: Int = 0,
    val idCheck: Int, // Foreign Key
)
fun CheckListDetails.toEntity(): CheckListEntity = CheckListEntity(
    id = id,
    idCheck = idCheck
)
fun CheckListEntity.toFormDetails(): CheckListDetails = CheckListDetails(
    id = id,
    idCheck = idCheck
)



data class CheckDetails(
    val id: Int = 0,
    val name: String = "",
    val state: Boolean = false
)
fun CheckDetails.toEntity(): CheckEntity = CheckEntity(
    id = id,
    name = name,
    state = state
)
fun CheckEntity.toFormDetails(): CheckDetails = CheckDetails(
    id = id,
    name = name,
    state = state
)



data class WeatherFormUiState(
    val formDetails: WeatherFormDetails = WeatherFormDetails(
        id = 0,
        idGeneralForm = 0, // Foreign Key
        idZoneType = 0, // Foreign Key
        rainfall = 0, // decimal(2)
        maxTemperature = 0, // decimal(2)
        maxHumidity = 0, // decimal(2)
        minTemperature = 0, // decimal(2)
        streamMtLevel = 0
    ),
    val isEntryValid: Boolean = false
)
data class WeatherFormDetails(
    val id: Int = 0,
    val idGeneralForm: Int, // Foreign Key
    val idZoneType: Int, // Foreign Key
    val rainfall: Int = 0, // decimal(2)
    val maxTemperature: Int = 0, // decimal(2)
    val maxHumidity: Int = 0, // decimal(2)
    val minTemperature: Int = 0, // decimal(2)
    val streamMtLevel: Int = 0 // decimal(2)
)
fun WeatherFormDetails.toEntity(): WeatherFormEntity = WeatherFormEntity(
    id = 0,
    idGeneralForm = 0, // Foreign Key
    idZoneType = 0, // Foreign Key
    rainfall = 0, // decimal(2)
    maxTemperature = 0, // decimal(2)
    maxHumidity = 0, // decimal(2)
    minTemperature = 0, // decimal(2)
    streamMtLevel = 0
)
fun WeatherFormEntity.toFormDetails(): WeatherFormDetails = WeatherFormDetails(
    id = 0,
    idGeneralForm = 0, // Foreign Key
    idZoneType = 0, // Foreign Key
    rainfall = 0, // decimal(2)
    maxTemperature = 0, // decimal(2)
    maxHumidity = 0, // decimal(2)
    minTemperature = 0, // decimal(2)
    streamMtLevel = 0
)
fun WeatherFormEntity.toFormUiState(isEntryValid: Boolean = false): WeatherFormUiState = WeatherFormUiState(
    formDetails = this.toFormDetails(),
    isEntryValid = isEntryValid
)*/