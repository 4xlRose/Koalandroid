package com.example.koadex.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.R
import com.example.koadex.ViewModels.FomularioEspecies_ViewModel
import com.example.koadex.clases.User
import com.example.koadex.data.UserEntity
import com.example.koadex.navigate.sampleUser
import com.example.koadex.ui.theme.KoadexTheme
import java.io.File


@Preview(device = "spec:width=800px,height=1340px,dpi=300")
@Composable
fun Especies_preview(){
    FormularioEspecies(navController = rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioEspecies(
    navController: NavController,
    modifier: Modifier = Modifier,
    usuario: UserEntity = sampleUser
) {
    // Variables para los colores
    val green100 = colorResource(id = R.color.green_100)
    val green700 = colorResource(id = R.color.green_700)

    var transectoNumber by remember { mutableStateOf(TextFieldValue()) }
    var commonName by remember { mutableStateOf(TextFieldValue()) }
    var scientificName by remember { mutableStateOf(TextFieldValue()) }
    var individualsCount by remember { mutableStateOf<Int?>(1) }
    var selectedAnimalType by remember { mutableStateOf<String?>(null) }
    var selectedObservationType by remember { mutableStateOf<String?>(null) }
    var observations by remember { mutableStateOf(TextFieldValue()) }
    var numIndividuos by remember { mutableStateOf(1) }

    // ViewModel con las funciones
    val viewModel = FomularioEspecies_ViewModel()

    // Scroll State para habilitar scroll
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopAppBar(
            title = { Text("Especies en transecto",
                color = Color.White,
                fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = { navController.navigate("TiposForms") }) {
                    Icon(Icons.Filled.ArrowBack,
                        contentDescription = "Atrás",
                        tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4E7029))
        )

        // Aquí envuelvo el contenido con un Modifier.verticalScroll
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .verticalScroll(scrollState) // Habilitar scroll
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Número de transecto
                Spacer(modifier = Modifier.padding(vertical = 5.dp))

                OutlinedTextField(
                    value = transectoNumber,
                    onValueChange = { transectoNumber = it },
                    label = { Text("Número de Transecto", color = Color.DarkGray) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.padding(vertical = 5.dp))

                // Tipo de animal
                viewModel.Tipo_de_animal(
                    selectedAnimalType = selectedAnimalType,
                    onAnimalTypeSelected = { selectedAnimalType = it },
                    primaryGreen = green100
                )

                // Nombre común y científico
                OutlinedTextField(
                    value = commonName,
                    onValueChange = { commonName = it },
                    label = { Text("Nombre Común", color = Color.DarkGray) },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = scientificName,
                    onValueChange = { scientificName = it },
                    label = { Text("Nombre Científico", color = Color.DarkGray) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.padding(vertical = 5.dp))

                // Número de individuos
                Text("Número de Individuos",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Start),
                    color = Color.Black)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            if (numIndividuos > 1) numIndividuos--
                        }
                    ) {
                        Icon(Icons.Filled.Remove, contentDescription = "Disminuir")
                    }
                    Text(text = numIndividuos.toString(), style = MaterialTheme.typography.titleMedium)
                    IconButton(
                        onClick = { numIndividuos++ }
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Aumentar")
                    }
                }

                Spacer(modifier = Modifier.padding(vertical = 5.dp))

                viewModel.Tipo_observacion(
                    selectedObservationType = selectedObservationType,
                    onObservationTypeSelected = { selectedObservationType = it },
                    green100 = green100,
                    green700 = green700
                )

                Spacer(modifier = Modifier.padding(vertical = 5.dp))

                Text("Evidencias", style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Start),
                    color = Color.Black)
                viewModel.Botones_captura(green700)

                // Campo de observaciones
                OutlinedTextField(
                    value = observations,
                    onValueChange = { observations = it },
                    label = { Text("Observaciones", color = Color.DarkGray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )

                Spacer(modifier = Modifier.padding(vertical = 10.dp))

                viewModel.Atras_enviar(navController, green700)

                Spacer(modifier = Modifier.height(50.dp))

            }
        }
    }
}
