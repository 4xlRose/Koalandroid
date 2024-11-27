package com.example.koadex.ui.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.koadex.data.FormRepository
import com.example.koadex.data.GeneralFormEntity
import com.example.koadex.data.WeatherFormEntity
import kotlinx.coroutines.flow.Flow

class FormWeatherDBViewModel (private val formRepository: FormRepository) : ViewModel() {
    var formWeatherUiState by mutableStateOf(WeatherFormUiState())

    fun updateWeatherUiState(formWeather: WeatherFormDetails) {
        formWeatherUiState = WeatherFormUiState(formWeatherDetails = formWeather)
    }

    suspend fun saveWeatherForm() {
        formRepository.insertWeatherForm(formWeatherUiState.formWeatherDetails.toEntity())
    }

    // Actualizar el ID de la Zona
    fun updateZoneTypeId(zoneTypeId: Int) {
        formWeatherUiState = formWeatherUiState.copy(
            formWeatherDetails = formWeatherUiState.formWeatherDetails.copy(idZoneType = zoneTypeId)
        )
    }

    fun id_General() : Flow<GeneralFormEntity> {
        return formRepository.getLastGeneralForm()
    }
}

data class WeatherFormUiState(
    var formWeatherDetails: WeatherFormDetails = WeatherFormDetails()
)
data class WeatherFormDetails(
    val id: Int = 0,
    var idZoneType: Int = 0, // Foreign Key
    var rainfall: Double = 0.0, // decimal(2)
    var maxTemperature: Double = 0.0, // decimal(2)
    var maxHumidity: Double = 0.0, // decimal(2)
    var minTemperature: Double = 0.0, // decimal(2)
    var minHumidity: Double = 0.0, // decimal(2)
    var streamMtLevel: Double = 0.0, // decimal(2)
    var evidences:ByteArray = byteArrayOf(), // <- AGREGO MARIA
    var observations: String = "" // <- AGREGO MARIA
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WeatherFormDetails

        if (id != other.id) return false
        if (idZoneType != other.idZoneType) return false
        if (rainfall != other.rainfall) return false
        if (maxTemperature != other.maxTemperature) return false
        if (maxHumidity != other.maxHumidity) return false
        if (minTemperature != other.minTemperature) return false
        if (minHumidity != other.minHumidity) return false
        if (streamMtLevel != other.streamMtLevel) return false
        if (!evidences.contentEquals(other.evidences)) return false
        if (observations != other.observations) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + idZoneType
        result = 31 * result + rainfall.hashCode()
        result = 31 * result + maxTemperature.hashCode()
        result = 31 * result + maxHumidity.hashCode()
        result = 31 * result + minTemperature.hashCode()
        result = 31 * result + minHumidity.hashCode()
        result = 31 * result + streamMtLevel.hashCode()
        result = 31 * result + evidences.contentHashCode()
        result = 31 * result + observations.hashCode()
        return result
    }
}

fun WeatherFormDetails.toEntity() : WeatherFormEntity = WeatherFormEntity(
    idZoneType = idZoneType,
    rainfall = rainfall,
    maxTemperature = maxTemperature,
    maxHumidity = maxHumidity,
    minTemperature = minTemperature,
    minHumidity = minHumidity, // decimal(2)
    streamMtLevel = streamMtLevel,
    evidences = evidences, // <- AGREGO MARIA
    observations = observations // <- AGREGO MARIA
)