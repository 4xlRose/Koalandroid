package com.example.koadex.legos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.koadex.R

class funciones_especies {
    @Composable
    public fun Atras_enviar(
        navController: NavController,
        green700: Color
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = green700)
            ) {
                Text("ATRAS")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { /* Handle form submission */ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = green700)
            ) {
                Text("ENVIAR")
            }
        }
    }

    //////////////// PARA LOS BOTONES DE CAPTURAR //////////////////
    @Composable
    public fun Botones_captura(green700: Color) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Boton_seleccionar_archivo(green700)
            Boton_abrir_camara(green700)
        }
    }

    @Composable
    public fun Boton_abrir_camara(green700: Color) {
        Button(
            onClick = { /* Handle photo capture */ },
            colors = ButtonDefaults.buttonColors(containerColor = green700,contentColor = Color.White)
        ) {
            Icon(Icons.Default.Camera, contentDescription = "Tomar foto")
            Spacer(modifier = Modifier.width(10.dp))
            Text("Tomar foto")
        }
    }

    @Composable
    public fun Boton_seleccionar_archivo(green700: Color) {
        Button(
            onClick = { /* Handle file selection */ },
            colors = ButtonDefaults.buttonColors(containerColor = green700,contentColor = Color.White)
        ) {
            Icon(Icons.Default.FileOpen, contentDescription = "Seleccionar archivo")
            Spacer(modifier = Modifier.width(10.dp))
            Text("Elige archivo")
        }
    }
////////////////////////////////////////////////////////////////

    ////////////// PARA EL TIPO DE ANIMAL //////////////////
    data class AnimalType(val name: String, val icon: Int)

    val animalTypes = listOf(
        AnimalType("Mamífero", R.drawable.ic_mamifero),
        AnimalType("Ave", R.drawable.ic_ave),
        AnimalType("Reptil", R.drawable.ic_reptil),
        AnimalType("Anfibio", R.drawable.ic_anfibio),
        AnimalType("Insecto", R.drawable.ic_insecto)
    )

    @Composable
    public fun Tipo_de_animal(
        selectedAnimalType: String?,
        onAnimalTypeSelected: (String) -> Unit,
        primaryGreen: Color
    ) {
        Text("Tipo de Animal")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            animalTypes.forEach { animalType ->
                AnimalTypeButton(
                    text = animalType.name,
                    iconRes = animalType.icon,
                    selected = selectedAnimalType,
                    selectedColor = primaryGreen
                ) { onAnimalTypeSelected(animalType.name) }
            }
        }
    }

    @Composable
    fun AnimalTypeButton(
        text: String,
        iconRes: Int,
        selected: String?,
        selectedColor: Color,
        onClick: () -> Unit
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = if (selected == text) selectedColor else Color.Gray,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
                .clickable(onClick = onClick)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.size(40.dp)
            )
            Text(text, style = MaterialTheme.typography.bodySmall)
        }
    }
///////////////////////////////////////////////////////////

    /// PARA LAS OBSERVACIONES
    @Composable
    public fun Tipo_observacion(
        selectedObservationType: String?,
        onObservationTypeSelected: (String) -> Unit,
        green100: Color,
        green700: Color
    ) {
        Text("Tipo de Observación")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ObservationTypeButton("La vió", R.drawable.ic_ave, selectedObservationType, green100) { onObservationTypeSelected("La vió") }
            ObservationTypeButton("Huella", R.drawable.ic_ave, selectedObservationType, green100) { onObservationTypeSelected("Huella") }
            ObservationTypeButton("Rastro", R.drawable.ic_ave, selectedObservationType, green100) { onObservationTypeSelected("Rastro") }
            ObservationTypeButton("Cacería", R.drawable.ic_reptil, selectedObservationType, green100) { onObservationTypeSelected("Cacería") }
            ObservationTypeButton("Les dijeron", R.drawable.ic_reptil, selectedObservationType, green100) { onObservationTypeSelected("Les dijeron") }
        }
    }
    @Composable
    fun ObservationTypeButton(
        text: String,
        iconRes: Int,
        selected: String?,
        selectedColor: Color,
        onClick: () -> Unit
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = if (selected == text) selectedColor else Color.Gray,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
                .clickable(onClick = onClick)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.size(40.dp)
            )
            Text(text, style = MaterialTheme.typography.bodySmall)
        }
    }

    @Composable
    public fun Contador_numero_individuos(
        individualsCount: Int,
        onCountChange: (Int) -> Unit
    ) {
        Text("Número de individuos")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { if (individualsCount > 0) onCountChange(individualsCount - 1) }
            ) {
                Text("-", style = MaterialTheme.typography.headlineMedium)
            }

            OutlinedTextField(
                value = verificacion_contador(individualsCount).toString(),
                label = {},
                onValueChange = {
                    if (it.isEmpty()) {
                        onCountChange(1)
                    }
                    else if (it.toIntOrNull() == null) {
                        onCountChange(1)
                    }
                    else if (it.toIntOrNull() != null) {
                        onCountChange(it.toInt())
                    }
                },
                modifier = Modifier.width(120.dp),
                textStyle = MaterialTheme.typography.headlineMedium
            )

            /* Text(
                 text = if(individualsCount <= 0) "1" else individualsCount.toString(),
                 style = MaterialTheme.typography.headlineMedium
             )*/
            IconButton(
                onClick = { onCountChange(individualsCount + 1) }
            ) {
                Text("+", style = MaterialTheme.typography.headlineMedium)
            }
        }
    }

    public fun verificacion_contador(individualsCount: Int): Int {
        if (individualsCount < 0)
            return individualsCount*-1
        else if (individualsCount == 0)
            return 1
        else
            return individualsCount
    }
////////////////////////////


    @Composable
    public fun Header_Formulario(
        green100: Color,
        navController: NavController
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(green100)
                .padding(top = 24.dp, bottom = 18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate("TiposForms") }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
                Text(
                    text = "Especie en Transecto",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 35.sp
                )
            }
        }
    }


}



////////////////////////////

/// PARA EL CONTADOR DE INDIVIDUOS
//@Composable
//public fun Contador_numero_individuos(
//    individualsCount: Int,
//    onCountChange: (Int) -> Unit
//) {
//    Text("Número de individuos")
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        IconButton(
//            onClick = { if (individualsCount > 0) onCountChange(individualsCount - 1) }
//        ) {
//            Text("-", style = MaterialTheme.typography.headlineMedium)
//        }
//        Text(
//            text = individualsCount.toString(),
//            style = MaterialTheme.typography.headlineMedium
//        )
//        IconButton(
//            onClick = { onCountChange(individualsCount + 1) }
//        ) {
//            Text("+", style = MaterialTheme.typography.headlineMedium)
//        }
//    }
//}

