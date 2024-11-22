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
}

/*Formulario Ruta*/
data class RouteFormUiState(
    var formsRouteDetails:  RouteFormDetails = RouteFormDetails()
)

data class RouteFormDetails(
    val id: Int = 0,
    var idGeneralForm: Int = 0, // Foreign Key
    var idZoneType: Int = 0, // Foreign Key
    var idCamera: Int = 0, // Foreign Key
    var  guayaPlate: Int = 0,
    var routeWidth: Int = 0, // decimal(2)
    var targetDistance: Int = 0, // decimal(2)
    var lensHeight: Int = 0, // decimal(2)
    var idCheckList: Int = 0, // Foreign Key
    var evidences: String = "",
    var observations: String = ""
)

fun RouteFormDetails.toEntity() : RouteFormEntity = RouteFormEntity(
    idGeneralForm = idGeneralForm,
    idZoneType = idZoneType,
    idCamera = idCamera,
    guayaPlate = guayaPlate,
    routeWidth = routeWidth,
    targetDistance = targetDistance,
    lensHeight = lensHeight,
    idCheckList = idCheckList,
    evidences = evidences,
    observations = observations
)