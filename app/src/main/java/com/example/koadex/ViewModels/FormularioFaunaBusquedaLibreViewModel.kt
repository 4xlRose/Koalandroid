package com.example.koadex.ViewModels

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FormularioFaunaBusquedaLibreViewModel: ViewModel() {

    @Composable
    public fun AlturaButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
        Text(
            text = text,
            modifier = Modifier
                .background(if (isSelected) Color(0xFF97B96E) else Color.White)
                .padding(8.dp)
                .clickable(onClick = onClick),
            color = if (isSelected) Color.Black else Color.Gray
        )
    }

    data class FormNumberUiState (
        val entero: MutableState<Int> =  mutableStateOf<Int>(1)
    )
    private val _uiState = MutableStateFlow(FormNumberUiState())
    val uiState: StateFlow<FormNumberUiState> = _uiState.asStateFlow()
    var entero by mutableStateOf<Int>(1)

    fun updateEnteroInput(input: Int) {
        entero = checkEnteroInput(input)
    }
    fun checkEnteroInput(entero: Int): Int {
        if (entero < 0) {
            return entero * -1
        }
        else if (entero == 0) {
            return 1
        }
        else {
            return entero
        }
    }
    fun enteroMasUno() {
        entero = checkEnteroInput(entero?.plus(1) ?: 1)
    }
    fun enteroMenosUno() {
        entero = checkEnteroInput(entero?.minus(1) ?: 1)
    }

    @Composable
    fun zonaNametoId(zonaName: String): Int {
        if(zonaName == "Bosque"){
            return 1
        }
        else if(zonaName == "Arreglo Agroforestal") {
            return 2
        }
        else if(zonaName == "Cultivos Transitorios"){
            return 3
        }
        else {
            return 4
        }
    }
    @Composable
    fun alturaNametoId(alturaName: String): Int {
        if(alturaName == "< 1mt Baja"){
            return 1
        }
        else if(alturaName == "1-3 mt Media"){
            return 2
        }
        else{
            return 3

        }
    }
    @Composable
    fun animaltipoId(animaltipo: String): Int {
        if (animaltipo == "Mamífero") {
            return 1
        } else if (animaltipo == "Ave") {
            return 2
        } else if (animaltipo == "Reptil") {
            return 3
        }
        else if (animaltipo == "Anfibio") {
            return 4
        } else {
            return 5
        }
    }
    @Composable
    fun observacionNametoId(observacionName: String): Int {
        if (observacionName == "La vio") {
            return 1
        } else if (observacionName == "Huella") {
            return 2
        } else if (observacionName == "Rastro") {
            return 3
        }
        else if (observacionName == "Cacería") {
            return 4
        }
        else {
            return 5
        }
    }




    @Composable
    fun ZonaButton(text: String, isSelected: Boolean, iconRes: Int, onClick: () -> Unit) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier
                .size(80.dp)
                .aspectRatio(1.2f)
                .clip(RoundedCornerShape(8.dp)) // Esquinas redondeadas
                .border(
                    BorderStroke(2.dp, Color(0xFF97B96E)),
                    shape = RoundedCornerShape(8.dp) // Forma del borde
                )
                .background(if (isSelected) Color(0xFF97B96E) else Color.White)
                .padding(2.dp)
                .clickable(onClick = onClick)
        )
    }

    @Composable
    fun TipoAnimalButton(text: String, isSelected: Boolean, iconRes: Int, onClick: () -> Unit) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp)) // Esquinas redondeadas
                .border(
                    BorderStroke(2.dp, Color(0xFF97B96E)),
                    shape = RoundedCornerShape(8.dp) // Forma del borde
                )
                .background(if (isSelected) Color(0xFF97B96E) else Color.White)
                .padding(4.dp)
                .clickable(onClick = onClick)
        )
    }

    @Composable
    fun TipoObservacionButton(text: String, isSelected: Boolean, iconRes: Int, onClick: () -> Unit) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp)) // Esquinas redondeadas
                .border(
                    BorderStroke(2.dp, Color(0xFF97B96E)),
                    shape = RoundedCornerShape(8.dp) // Forma del borde
                )
                .background(if (isSelected) Color(0xFF97B96E) else Color.White)
                .padding(4.dp)
                .clickable(onClick = onClick)
        )
    }

}