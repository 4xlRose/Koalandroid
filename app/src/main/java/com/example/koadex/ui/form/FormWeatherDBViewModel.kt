package com.example.koadex.ui.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.room.PrimaryKey
import com.example.koadex.data.FormRepository
import com.example.koadex.data.WeatherEntity
import com.example.koadex.data.WeatherFormEntity

class FormWeatherDBViewModel (private val formRepository: FormRepository) : ViewModel() {
    var formWeatherUiState by mutableStateOf(WeatherFormUiState())

    fun updateWeatherUiState(formWeather: WeatherFormDetails) {
        formWeatherUiState = WeatherFormUiState(formWeatherDetails = formWeather)
    }
}

data class WeatherFormUiState(
    var formWeatherDetails: WeatherFormDetails = WeatherFormDetails()
)
data class WeatherFormDetails(
    val id: Int = 0,
    var idGeneralForm: Int = 0, // Foreign Key
    var idZoneType: Int = 0, // Foreign Key
    var rainfall: Double = 0.0, // decimal(2)
    var maxTemperature: Double = 0.0, // decimal(2)
    var maxHumidity: Double = 0.0, // decimal(2)
    var minTemperature: Double = 0.0, // decimal(2)
    var streamMtLevel: Double = 0.0 // decimal(2)
)

fun WeatherFormDetails.toEntity() : WeatherFormEntity = WeatherFormEntity(
    idGeneralForm = idGeneralForm,
    idZoneType = idZoneType,
    rainfall = rainfall,
    maxTemperature = maxTemperature,
    maxHumidity = maxHumidity,
    minTemperature = minTemperature,
    streamMtLevel = streamMtLevel
)