package com.example.koadex.Views

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.R
import com.example.koadex.ViewModels.FormularioEspeciesViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Especies_preview(){
    FormularioEspecies(navController = rememberNavController())
}

@Composable
fun FormularioEspecies(
    formEspecieViewModel: FormularioEspeciesViewModel = viewModel(),
    navController: NavController, modifier: Modifier = Modifier) {
    val formUi by formEspecieViewModel.uiState.collectAsState()
    val green100 = colorResource(id = R.color.green_100)
    val green700 = colorResource(id = R.color.green_700)
    var transectoNumber by remember { mutableStateOf(TextFieldValue()) }
    var commonName by remember { mutableStateOf(TextFieldValue()) }
    var scientificName by remember { mutableStateOf(TextFieldValue()) }
    //var individualsCount by remember { mutableStateOf(1) }
    var individualsCount by remember { formUi.entero }
    var selectedAnimalType by remember { mutableStateOf<String?>(null) }
    var selectedObservationType by remember { mutableStateOf<String?>(null) }
    var observations            by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header_Formulario(green100, navController)

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                /// El numero de transecto
                OutlinedTextField(
                    value = transectoNumber,
                    onValueChange = { transectoNumber = it },
                    label = { Text("Número de Transecto") },
                    modifier = Modifier.fillMaxWidth()
                )
                ////

                /// Tipo de animal
                Tipo_de_animal(selectedAnimalType = selectedAnimalType, onAnimalTypeSelected = { selectedAnimalType = it }, primaryGreen = green100)

                /// Nombre comun y cientifico
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

                individualsCount?.let { Contador_numero_individuos(formEspecieViewModel) }

                Tipo_observacion(selectedObservationType = selectedObservationType, onObservationTypeSelected = { selectedObservationType = it }, green100 = green100, green700 = green700)

                Botones_captura(green700)

                // Campo de observaciones
                OutlinedTextField(
                    value = observations,
                    onValueChange = { observations = it },
                    label = { Text("Observaciones") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )

                Atras_enviar(navController, green700)
            }
        }
    }
}

@Composable
private fun Atras_enviar(
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
private fun Botones_captura(green700: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Boton_seleccionar_archivo(green700)
        Boton_abrir_camara(green700)
    }
}

@Composable
private fun Boton_abrir_camara(green700: Color) {
    Button(
        onClick = { /* Handle photo capture */ },
        colors = ButtonDefaults.buttonColors(containerColor = green700)
    ) {
        Icon(Icons.Default.Camera, contentDescription = "Tomar foto")
        Spacer(modifier = Modifier.width(10.dp))
        Text("Tomar foto")
    }
}

@Composable
private fun Boton_seleccionar_archivo(green700: Color) {
    Button(
        onClick = { /* Handle file selection */ },
        colors = ButtonDefaults.buttonColors(containerColor = green700)
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
private fun Tipo_observacion(
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
////////////////////////////

/// PARA EL CONTADOR DE INDIVIDUOS
//@Composable
//private fun Contador_numero_individuos(
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
@Composable
private fun Contador_numero_individuos(
    formUi: FormularioEspeciesViewModel
) {
    Text("Número de individuos")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { formUi.enteroMenosUno() }
        ) {
            Text("-", style = MaterialTheme.typography.headlineMedium)
        }

        OutlinedTextField(
            value = formUi.entero.toString(),
            label = {},
            onValueChange = {
                formUi.updateEnteroInput(it.toIntOrNull() ?: 1)
            },
            modifier = Modifier.width(120.dp)
                .align(Alignment.CenterVertically)
                .border(1.dp, Color(R.color.green_700)),
            textStyle = MaterialTheme.typography.headlineMedium.copy(textAlign = TextAlign.Center),

        )

        /* Text(
             text = if(individualsCount <= 0) "1" else individualsCount.toString(),
             style = MaterialTheme.typography.headlineMedium
         )*/
        IconButton(
            onClick = { formUi.enteroMasUno() }
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
private fun verificacion_contador(individualsCount: Int): Int {
    if (individualsCount < 0)
        return individualsCount*-1
    else if (individualsCount == 0)
        return 1
    else
        return individualsCount
}
////////////////////////////

@Composable
private fun Header_Formulario(
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