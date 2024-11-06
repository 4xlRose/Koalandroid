package com.example.koadex.Views

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.koadex.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Especies_preview(){
    Text("Hola Mundo")
}

@Composable
fun FormularioEspecies(navController: NavController, modifier: Modifier = Modifier) {

    val green100 = colorResource(id = R.color.green_100)
    val green700 = colorResource(id = R.color.green_700)

    var transectoNumber by remember { mutableStateOf(TextFieldValue()) }
    var commonName by remember { mutableStateOf(TextFieldValue()) }
    var scientificName by remember { mutableStateOf(TextFieldValue()) }
    var individualsCount by remember { mutableStateOf(1) }
    var selectedAnimalType by remember { mutableStateOf<String?>(null) }
    var selectedObservationType by remember { mutableStateOf<String?>(null) }
    var observations by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header_Formulario(green100, navController)

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = transectoNumber,
                    onValueChange = { transectoNumber = it },
                    label = { Text("Número de Transecto") },
                    modifier = Modifier.fillMaxWidth()
                )

                Tipo_de_animal(selectedAnimalType = selectedAnimalType, onAnimalTypeSelected = { selectedAnimalType = it }, primaryGreen = green100)

                OutlinedTextField(
                    value = commonName,
                    onValueChange = { commonName = it },
                    label = { Text("Nombre Común") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = scientificName,
                    onValueChange = { scientificName = it },
                    label = { Text("Nombre Científico") },
                    modifier = Modifier.fillMaxWidth()
                )

                Contador_numero_individuos(individualsCount = individualsCount, onCountChange = { individualsCount = it })

                Evidencia_observacion(selectedObservationType = selectedObservationType, onObservationTypeSelected = { selectedObservationType = it }, green100 = green100, green700 = green700)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { /* Handle file selection */ },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = green700)
                    ) {
                        Text("Elige archivo")
                    }
                    Button(
                        onClick = { /* Handle photo capture */ },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = green700)
                    ) {
                        Text("Tomar foto")
                    }
                }

                OutlinedTextField(
                    value = observations,
                    onValueChange = { observations = it },
                    label = { Text("Observaciones") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )

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
        }
    }
}
data class AnimalType(val name: String, val icon: Int)

val animalTypes = listOf(
    AnimalType("Mamífero", R.drawable.ic_mamifero),
    AnimalType("Ave", R.drawable.ic_ave),
    AnimalType("Reptil", R.drawable.ic_reptil),
    AnimalType("Anfibio", R.drawable.ic_anfibio),
    AnimalType("Insecto", R.drawable.ic_insecto)
)

@Composable
private fun Tipo_de_animal(
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
private fun Evidencia_observacion(
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
private fun Contador_numero_individuos(
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
        Text(
            text = individualsCount.toString(),
            style = MaterialTheme.typography.headlineMedium
        )
        IconButton(
            onClick = { onCountChange(individualsCount + 1) }
        ) {
            Text("+", style = MaterialTheme.typography.headlineMedium)
        }
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

@Composable
private fun Header_Formulario(
    green100: Color,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(green100)
            .padding(top = 48.dp, bottom = 32.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigate("Principal") }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Text(
                text = "Formulario",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
            )
        }
    }
}