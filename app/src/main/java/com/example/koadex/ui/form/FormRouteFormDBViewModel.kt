package com.example.koadex.ui.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.room.PrimaryKey
import com.example.koadex.data.FormRepository
import com.example.koadex.data.RouteFormEntity

class FormRouteFormDBViewModel (private val formRepository: FormRepository) : ViewModel() {
    //FormularioRuta
    var formRouteUiState by mutableStateOf(RouteFormUiState())
        private set
    fun updateRouteFormUiState(formRoute: RouteFormDetails) {
        formRouteUiState = RouteFormUiState(formsRouteDetails = formRoute)
    }

    suspend fun saveRouteFrom() {
        formRepository.insertRouteForm(formRouteUiState.formsRouteDetails.toEntity())
    }

    // Actualizar el ID de la Zona
    fun updateZoneTypeId(zoneTypeId: Int) {
        formRouteUiState = formRouteUiState.copy(
            formsRouteDetails = formRouteUiState.formsRouteDetails.copy(idZoneType = zoneTypeId)
        )
    }
}

/*Formulario Ruta*/
data class RouteFormUiState(
    var formsRouteDetails:  RouteFormDetails = RouteFormDetails()
)

data class RouteFormDetails(
    val id: Int = 0,
    var idZoneType: Int = 0, // Foreign Key
    var nameCamara: String = "",
    var placaCamara: String = "",
    var  guayaPlate: Int = 0,
    var fecha: Int = 0,
    var instalada: Int = 0,
    var memoria: Int = 0,
    var pruebaGateo: Int = 0,
    var programa: Int = 0,
    var prendida: Int = 0,
    var letreroCamara: Int = 0,
    var routeWidth: Int = 0, // decimal(2)
    var targetDistance: Int = 0, // decimal(2)
    var lensHeight: Int = 0, // decimal(2)
    var idCheckList: Int = 0, // Foreign Key
    var evidences: ByteArray = byteArrayOf(),
    var observations: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RouteFormDetails

        if (id != other.id) return false
        if (idZoneType != other.idZoneType) return false
        if (nameCamara != other.nameCamara) return false
        if (placaCamara != other.placaCamara) return false
        if (guayaPlate != other.guayaPlate) return false
        if (fecha != other.fecha) return false
        if (instalada != other.instalada) return false
        if (memoria != other.memoria) return false
        if (pruebaGateo != other.pruebaGateo) return false
        if (programa != other.programa) return false
        if (prendida != other.prendida) return false
        if (letreroCamara != other.letreroCamara) return false
        if (routeWidth != other.routeWidth) return false
        if (targetDistance != other.targetDistance) return false
        if (lensHeight != other.lensHeight) return false
        if (idCheckList != other.idCheckList) return false
        if (!evidences.contentEquals(other.evidences)) return false
        if (observations != other.observations) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + idZoneType
        result = 31 * result + nameCamara.hashCode()
        result = 31 * result + placaCamara.hashCode()
        result = 31 * result + guayaPlate
        result = 31 * result + fecha
        result = 31 * result + instalada
        result = 31 * result + memoria
        result = 31 * result + pruebaGateo
        result = 31 * result + programa
        result = 31 * result + prendida
        result = 31 * result + letreroCamara
        result = 31 * result + routeWidth
        result = 31 * result + targetDistance
        result = 31 * result + lensHeight
        result = 31 * result + idCheckList
        result = 31 * result + evidences.contentHashCode()
        result = 31 * result + observations.hashCode()
        return result
    }
}

fun RouteFormDetails.toEntity() : RouteFormEntity = RouteFormEntity(
    idZoneType = idZoneType,
    nameCamara = nameCamara,
    placaCamara = placaCamara,
    guayaPlate = guayaPlate,
    fecha = fecha,
    instalada = instalada,
    memoria = memoria,
    pruebaGateo = pruebaGateo,
    programa = programa,
    prendida = prendida,
    letreroCamara = letreroCamara,
    routeWidth = routeWidth,
    targetDistance = targetDistance,
    lensHeight = lensHeight,
    idCheckList = idCheckList,
    evidences = evidences,
    observations = observations
)