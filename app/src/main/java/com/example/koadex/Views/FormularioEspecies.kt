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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.R
import com.example.koadex.ViewModels.FomularioEspecies_ViewModel
import com.example.koadex.clases.User
import com.example.koadex.ui.theme.KoadexTheme
import java.io.File


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Especies_preview(){
    FormularioEspecies(navController = rememberNavController())
}

@Composable
fun FormularioEspecies(
    navController: NavController,
    modifier: Modifier = Modifier,
    usuario: User = User("Samantha", 5, 3, 2)
) {
    // Variables para los colores
    val green100 = colorResource(id = R.color.green_100)
    val green700 = colorResource(id = R.color.green_700)
    var transectoNumber by remember { mutableStateOf(TextFieldValue()) }
    var commonName by remember { mutableStateOf(TextFieldValue()) }
    var scientificName by remember { mutableStateOf(TextFieldValue()) }
    //var individualsCount by remember { mutableStateOf(1) }
    var individualsCount by remember { mutableStateOf<Int?>(1) }
    var selectedAnimalType by remember { mutableStateOf<String?>(null) }
    var selectedObservationType by remember { mutableStateOf<String?>(null) }
    var observations            by remember { mutableStateOf(TextFieldValue()) }

    // ViewnModel con las funciones
    val viewModel = FomularioEspecies_ViewModel()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        viewModel.Header_Formulario(green100, navController)

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
                viewModel.Tipo_de_animal(selectedAnimalType = selectedAnimalType, onAnimalTypeSelected = { selectedAnimalType = it }, primaryGreen = green100)

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

                individualsCount?.let { viewModel.Contador_numero_individuos(individualsCount = it, onCountChange = { individualsCount = it }) }

                viewModel.Tipo_observacion(selectedObservationType = selectedObservationType, onObservationTypeSelected = { selectedObservationType = it }, green100 = green100, green700 = green700)

                viewModel.Botones_captura(green700)

                // Campo de observaciones
                OutlinedTextField(
                    value = observations,
                    onValueChange = { observations = it },
                    label = { Text("Observaciones") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )

                viewModel.Atras_enviar(navController, green700)
            }
        }
    }
}

