package com.example.koadex.Views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight

import com.example.koadex.MainActivity

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.koadex.AppViewModelProvider

import com.example.koadex.ViewModels.FomularioEspecies_ViewModel
import com.example.koadex.ViewModels.FormularioFaunaBusquedaLibreViewModel
import com.example.koadex.ViewModels.FormularioFaunaPuntoConteoViewModel
import com.example.koadex.ui.form.FormPuntoConteoDBViewModel
import kotlinx.coroutines.launch
val isFileSelectedFPC: MutableState<Boolean> = mutableStateOf(false)

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioFaunaPuntoConteo(navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: FormPuntoConteoDBViewModel = viewModel(factory = AppViewModelProvider.Factory) {
    if (CameraPermision.value) {
        CameraWindow(activity)
    } else {
        var numIndividuos by remember { mutableStateOf(1) }
        var alturaObservacion by remember { mutableStateOf("") }
        var tipoAnimalSeleccionado by remember { mutableStateOf("") }
        var zonaSeleccionada by remember { mutableStateOf("") }
        var tipoObservacionSeleccionada by remember { mutableStateOf("") }
        val actionButtonColors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029))
        val FaunaBViewModel = FormularioFaunaBusquedaLibreViewModel()
        val viewModel = FomularioEspecies_ViewModel()
        val green700 = colorResource(id = R.color.green_700)
        var nombreComun by remember { mutableStateOf("") } // Estado para Nombre Común
        var nombreCientifico by remember { mutableStateOf("") } // Estado para Nombre Científico
        var observaciones by remember { mutableStateOf("") } // Estado para Observaciones


        //Estado de scroll
        val scrollState = rememberScrollState()
        
        val coroutineScope = rememberCoroutineScope()
        val puntoConteoUiState = viewModel.puntoConteoUiState
        val puntoConteoDetails = puntoConteoUiState.puntoConteoDetails

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
        ) {
            // Barra superior
            TopAppBar(
                title = { Text("Formulario",
                    color = Color.White,
                    fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("TiposForms") }) {
                        Icon(Icons.Filled.ArrowBack,
                            contentDescription = "Atrás",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4E7029))
            )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
        ) {
            Spacer(modifier = Modifier.padding(vertical = 5.dp))

            // Zona
            Text("Zona",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start),
                color = Color.Black)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FaunaPCViewModel.ZonaButton("Bosque", puntoConteoDetails.idZoneType == 1, R.drawable.ic_bosque) {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(idZoneType = 1))
                }
                FaunaPCViewModel.ZonaButton("Agroforestal", puntoConteoDetails.idZoneType == 2, R.drawable.ic_agroforestal) {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(idZoneType = 2))
                }
                FaunaPCViewModel.ZonaButton("Cultivos Transitorios", puntoConteoDetails.idZoneType == 3, R.drawable.ic_cultivostransitorios) {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(idZoneType = 3))
                }
                FaunaPCViewModel.ZonaButton("Cultivos Permanentes", puntoConteoDetails.idZoneType == 4, R.drawable.ic_cultivospermanentes) {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(idZoneType = 4))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tipo de Animal
            Text("Tipo de Animal",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start),
                color = Color.Black)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FaunaPCViewModel.TipoAnimalButton("Mamífero", puntoConteoDetails.idAnimalType == 1, R.drawable.ic_mamifero) {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(idAnimalType = 1))
                }
                FaunaPCViewModel.TipoAnimalButton("Ave", puntoConteoDetails.idAnimalType == 2, R.drawable.ic_ave) {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(idAnimalType = 2))
                }
                FaunaPCViewModel.TipoAnimalButton("Reptil", puntoConteoDetails.idAnimalType == 3, R.drawable.ic_reptil) {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(idAnimalType = 3))
                }
                FaunaPCViewModel.TipoAnimalButton("Anfibio", puntoConteoDetails.idAnimalType == 4, R.drawable.ic_anfibio) {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(idAnimalType = 4))
                }
                FaunaPCViewModel.TipoAnimalButton("Insecto", puntoConteoDetails.idAnimalType == 5, R.drawable.ic_insecto) {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(idAnimalType = 5))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de texto
            OutlinedTextField(
                value = puntoConteoDetails.animalName,
                onValueChange = { newValue ->
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(animalName = newValue))
                },
                label = { Text("Nombre Común", color = Color.DarkGray ) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = puntoConteoDetails.scientificName,
                onValueChange = { newValue ->
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(scientificName = newValue))
                },
                label = { Text("Nombre Científico", color = Color.DarkGray) },
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(16.dp))

            // Número de Individuos
            Text("Número de Individuos",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start),
                color = Color.Black)
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    if (puntoConteoDetails.quantity > 1) {
                        viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(quantity = puntoConteoDetails.quantity - 1))
                    }
                }) {
                    Icon(Icons.Filled.Remove, contentDescription = "Disminuir")
                }
                Text(text = puntoConteoDetails.quantity.toString(), style = MaterialTheme.typography.titleMedium)
                IconButton(onClick = {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(quantity = puntoConteoDetails.quantity + 1))
                }) {
                    Icon(Icons.Filled.Add, contentDescription = "Aumentar")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tipo de Observación
            Text("Tipo de Observación",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start),
                color = Color.Black)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FaunaPCViewModel.TipoObservacionButton("La vio", puntoConteoDetails.idObservType == 1, R.drawable.ic_la_vio) {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(idObservType = 1))
                }
                FaunaPCViewModel.TipoObservacionButton("Huella", puntoConteoDetails.idObservType == 2, R.drawable.ic_huella) {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(idObservType = 2))
                }
                FaunaPCViewModel.TipoObservacionButton("Rastro", puntoConteoDetails.idObservType == 3, R.drawable.ic_rastro) {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(idObservType = 3))
                }
                FaunaPCViewModel.TipoObservacionButton("Cacería", puntoConteoDetails.idObservType == 4, R.drawable.ic_caceria) {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(idObservType = 4))
                }
                FaunaPCViewModel.TipoObservacionButton("Les Dijeron", puntoConteoDetails.idObservType == 5, R.drawable.ic_les_dijeron) {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(idObservType = 5))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Altura de Observación
            Text("Altura de Observación",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start),
                color = Color.Black)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FaunaPCViewModel.AlturaButton("< 1mt Baja", puntoConteoDetails.idHeightType == 1) {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(idHeightType = 1))
                }
                FaunaPCViewModel.AlturaButton("1-3 mt Media", puntoConteoDetails.idHeightType == 2) {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(idHeightType = 2))
                }
                FaunaPCViewModel.AlturaButton(">3mt Alta", puntoConteoDetails.idHeightType == 3) {
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(idAnimalType = 3))
                }
            }


            // Evidencias
            Text("Evidencias",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start),
                color = Color.Black)
            viewModelFE.Botones_captura(green700)


                    Spacer(modifier = Modifier.height(16.dp))

            // Observaciones
            OutlinedTextField(
                value = puntoConteoDetails.observations,
                onValueChange = { newValue ->
                    viewModel.updatePuntoConteoUiState(puntoConteoDetails.copy(observations = newValue))
                },
                label = { Text("Observaciones", color = Color.DarkGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

                    Spacer(modifier = Modifier.height(16.dp))

            viewModelFE.Atras_enviar(navController, green700)
/*
            // Botón para guardar el formulario
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.savePuntoConteo()
                        navController.popBackStack() // Navegar hacia atrás despues de guardar
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029))
            ) {
                Text("Guardar", color = Color.White)
            }

                    // Evidencias
                    Text(
                        "Evidencias",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.align(Alignment.Start),
                        color = Color.Black
                    )
                    Botones_capturaFPC(green700)


                    Spacer(modifier = Modifier.height(16.dp))

                    // Observaciones
                    OutlinedTextField(
                        value = observaciones,
                        onValueChange = { observaciones = it }, // Actualizar el estado
                        label = { Text("Observaciones", color = Color.DarkGray) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    viewModel.Atras_enviar(navController, green700)
*/
                    Spacer(modifier = Modifier.height(50.dp))

                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.P)
//@Preview(device = "spec:width=800px,height=1340px,dpi=300")
@Composable
fun PreviewFormularioPuntoConteo() {

    FormularioFaunaPuntoConteo(activity = MainActivity(),navController = rememberNavController(), modifier = Modifier)
}

@Composable
public fun Botones_capturaFPC(green700: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Boton_seleccionar_archivoFPC(green700)
        Boton_abrir_camaraFPC(green700)
    }
}

@Composable
public fun Boton_abrir_camaraFPC(green700: Color) {
    Button(
        onClick = { CameraPermision.value = true; isFileSelectedFPC.value = true },
        colors = ButtonDefaults.buttonColors(containerColor = green700)
    ) {
        Icon(Icons.Default.Camera, contentDescription = "Tomar foto", tint = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        Text("Tomar foto", color = Color.White)
    }
}

@Composable
public fun Boton_seleccionar_archivoFPC(green700: Color) {
    Button(
        onClick = { /* Handle file selection */ isFileSelectedFPC.value = true},
        colors = ButtonDefaults.buttonColors(containerColor = green700)
    ) {
        Icon(Icons.Default.FileOpen, contentDescription = "Seleccionar archivo", tint = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        Text("Elige archivo", color = Color.White)
    }
}

