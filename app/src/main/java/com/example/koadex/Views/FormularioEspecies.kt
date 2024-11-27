package com.example.koadex.Views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.FileOpen
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.AppViewModelProvider
import com.example.koadex.MainActivity
import com.example.koadex.R
import com.example.koadex.ViewModels.FomularioEspecies_ViewModel
import com.example.koadex.clases.User
import com.example.koadex.data.UserEntity
import com.example.koadex.navigate.sampleUser
import com.example.koadex.ui.form.FormFollowDBViewModel
import com.example.koadex.ui.form.FormSpecieDBViewModel
import com.example.koadex.ui.theme.KoadexTheme
import kotlinx.coroutines.launch
import java.io.File


/*@Preview(device = "spec:width=800px,height=1340px,dpi=300")
@Composable
fun Especies_preview(){
    FormularioEspecies(navController = rememberNavController())
}*/

val isFileSelectedFE: MutableState<Boolean> = mutableStateOf(false)

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioEspecies(
    activity: MainActivity,
    navController: NavController,
    modifier: Modifier = Modifier,
    usuario: UserEntity = sampleUser,
    viewModel: FormSpecieDBViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    if (CameraPermision.value) {
        CameraWindow(activity)
    } else {
        // Variables para los colores
        val green100 = colorResource(id = R.color.green_100)
        val green700 = colorResource(id = R.color.green_700)


        // ViewModel con las funciones
        val viewModelS = FomularioEspecies_ViewModel()

        // Scroll State para habilitar scroll
        val scrollState = rememberScrollState()

        val coroutineScope = rememberCoroutineScope()
        val formUiState = viewModel.formEspeciesUiState

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            TopAppBar(
                title = {
                    Text(
                        "Especies en transecto",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("TiposForms") }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Atrás",
                            tint = Color.White
                        )
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
                        value = formUiState.formsEspecieDetails.transect,
                        onValueChange = { transect ->
                            viewModel.updateEspecieFormUiState(
                                formUiState.formsEspecieDetails.copy(
                                    transect = transect
                                )
                            )
                        },
                        label = { Text("Número de Transecto", color = Color.DarkGray) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.padding(vertical = 5.dp))

                    // Tipo de animal
                    viewModelS.Tipo_de_animal(
                        selectedAnimalType = formUiState.formsEspecieDetails.idAnimalType,
                        onAnimalTypeSelected = { animalType ->
                            viewModel.updateAnimalType(animalType)
                        },
                        primaryGreen = green100
                    )

                    // Nombre común y científico
                    OutlinedTextField(
                        value = formUiState.formsEspecieDetails.animalName,
                        onValueChange = { animalName ->
                            viewModel.updateEspecieFormUiState(
                                formUiState.formsEspecieDetails.copy(
                                    animalName = animalName
                                )
                            )
                        },
                        label = { Text("Nombre Común", color = Color.DarkGray) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = formUiState.formsEspecieDetails.scientificName,
                        onValueChange = { scientificName ->
                            viewModel.updateEspecieFormUiState(
                                formUiState.formsEspecieDetails.copy(
                                    scientificName = scientificName
                                )
                            )
                        },
                        label = { Text("Nombre Científico", color = Color.DarkGray) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.padding(vertical = 5.dp))

                    // Número de individuos
                    Text(
                        "Número de Individuos",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.align(Alignment.Start),
                        color = Color.Black
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                //if (numIndividuos > 1) numIndividuos--
                                viewModel.updateQuantity(
                                    (formUiState.formsEspecieDetails.quantity - 1).coerceAtLeast(
                                        1
                                    )
                                )
                            }) {
                            Icon(Icons.Filled.Remove, contentDescription = "Disminuir")
                        }
                        //Text(text = numIndividuos.toString(), style = MaterialTheme.typography.titleMedium)
                        Text("${formUiState.formsEspecieDetails.quantity}")
                        IconButton(
                            onClick = { viewModel.updateQuantity(formUiState.formsEspecieDetails.quantity + 1) }
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "Aumentar")
                        }
                    }

                    Spacer(modifier = Modifier.padding(vertical = 5.dp))

                    viewModelS.Tipo_observacion(
                        selectedObservationType = formUiState.formsEspecieDetails.idObservType, // El ID del tipo de observación actual
                        onObservationTypeSelected = { observationType ->
                            viewModel.updateObservationType(observationType) // Actualiza en el ViewModel
                        },
                        green100 = green100,
                        green700 = green700
                    )

                    Spacer(modifier = Modifier.padding(vertical = 5.dp))

                    Text(
                        "Evidencias", style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.align(Alignment.Start),
                        color = Color.Black
                    )
                    Botones_capturaFE(green700)

                    // Campo de observaciones
                    OutlinedTextField(
                        value = formUiState.formsEspecieDetails.observations,
                        onValueChange = { observations ->
                            viewModel.updateEspecieFormUiState(
                                formUiState.formsEspecieDetails.copy(
                                    observations = observations
                                )
                            )
                        },
                        label = { Text("Observaciones", color = Color.DarkGray) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    )

                    Spacer(modifier = Modifier.padding(vertical = 10.dp))

                    //viewModelS.Atras_enviar(navController, green700)

                    // Botón para guardar el formulario
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                viewModel.saveEspecieForm()
                                navController.popBackStack() // Navegar hacia atrás despues de guardar
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029))
                    ) {
                        Text("Guardar", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(50.dp))

                }
            }
        }
    }
}

@Composable
public fun Botones_capturaFE(green700: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Boton_seleccionar_archivoFE(green700)
        Boton_abrir_camaraFE(green700)
    }
}

@Composable
public fun Boton_abrir_camaraFE(green700: Color) {
    Button(
        onClick = { CameraPermision.value = true; isFileSelectedFE.value = true },
        colors = ButtonDefaults.buttonColors(containerColor = green700)
    ) {
        Icon(Icons.Default.Camera, contentDescription = "Tomar foto", tint = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        Text("Tomar foto", color = Color.White)
    }
}

@Composable
public fun Boton_seleccionar_archivoFE(green700: Color) {
    Button(
        onClick = { /* Handle file selection */ isFileSelectedFE.value = true},
        colors = ButtonDefaults.buttonColors(containerColor = green700)
    ) {
        Icon(Icons.Default.FileOpen, contentDescription = "Seleccionar archivo", tint = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        Text("Elige archivo", color = Color.White)
    }
}