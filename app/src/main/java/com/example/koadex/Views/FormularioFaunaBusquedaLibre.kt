@file:JvmName("FormularioFaunaBusquedaLibreKt")

package com.example.koadex.Views

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.koadex.MainActivity
import com.example.koadex.ViewModels.FomularioEspecies_ViewModel
import com.example.koadex.ViewModels.FormularioFaunaBusquedaLibreViewModel

val isFileSelectedFBL: MutableState<Boolean> = mutableStateOf(false)

@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioFaunaBusquedaLibre(activity: MainActivity, navController: NavHostController, modifier: Modifier = Modifier) {
    if (CameraPermision.value) {
        CameraWindow(activity)
    } else {
        var numIndividuos by remember { mutableStateOf(1) }
        var alturaObservacion by remember { mutableStateOf("") }
        var tipoAnimalSeleccionado by remember { mutableStateOf("") }
        var zonaSeleccionada by remember { mutableStateOf("") }
        var tipoObservacionSeleccionada by remember { mutableStateOf("") }

        val isFileSelected: MutableState<Boolean> = mutableStateOf(false)


        val FaunaBViewModel = FormularioFaunaBusquedaLibreViewModel()
        val viewModel = FomularioEspecies_ViewModel()
        val green700 = colorResource(id = R.color.green_700)
        var nombreComun by remember { mutableStateOf("") } // Estado para Nombre Común
        var nombreCientifico by remember { mutableStateOf("") } // Estado para Nombre Científico
        var observaciones by remember { mutableStateOf("") } // Estado para Observaciones

        //Estado de scroll
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
        ) {
            // Barra superior
            TopAppBar(
                title = {
                    Text(
                        "Formulario",
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

            // Contenido desplazable
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState) // Habilitar scroll
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(vertical = 5.dp))

                // Zona
                Text(
                    "Zona", style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Start),
                    color = Color.Black
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FaunaBViewModel.ZonaButton(
                        "Bosque",
                        zonaSeleccionada == "Bosque",
                        R.drawable.ic_bosque
                    ) { zonaSeleccionada = "Bosque" }
                    FaunaBViewModel.ZonaButton(
                        "Arreglo Agroforestal",
                        zonaSeleccionada == "Arreglo Agroforestal",
                        R.drawable.ic_agroforestal
                    ) { zonaSeleccionada = "Arreglo Agroforestal" }
                    FaunaBViewModel.ZonaButton(
                        "Cultivos Transitorios",
                        zonaSeleccionada == "Cultivos Transitorios",
                        R.drawable.ic_cultivostransitorios
                    ) { zonaSeleccionada = "Cultivos Transitorios" }
                    FaunaBViewModel.ZonaButton(
                        "Cultivos Permanentes",
                        zonaSeleccionada == "Cultivos Permanentes",
                        R.drawable.ic_cultivospermanentes
                    ) { zonaSeleccionada = "Cultivos Permanentes" }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Tipo de Animal
                Text(
                    "Tipo de Animal", style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Start),
                    color = Color.Black
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FaunaBViewModel.TipoAnimalButton(
                        "Mamífero",
                        tipoAnimalSeleccionado == "Mamífero",
                        R.drawable.ic_mamifero
                    ) { tipoAnimalSeleccionado = "Mamífero" }
                    FaunaBViewModel.TipoAnimalButton(
                        "Ave",
                        tipoAnimalSeleccionado == "Ave",
                        R.drawable.ic_ave
                    ) { tipoAnimalSeleccionado = "Ave" }
                    FaunaBViewModel.TipoAnimalButton(
                        "Reptil",
                        tipoAnimalSeleccionado == "Reptil",
                        R.drawable.ic_reptil
                    ) { tipoAnimalSeleccionado = "Reptil" }
                    FaunaBViewModel.TipoAnimalButton(
                        "Anfibio",
                        tipoAnimalSeleccionado == "Anfibio",
                        R.drawable.ic_anfibio
                    ) { tipoAnimalSeleccionado = "Anfibio" }
                    FaunaBViewModel.TipoAnimalButton(
                        "Insecto",
                        tipoAnimalSeleccionado == "Insecto",
                        R.drawable.ic_insecto
                    ) { tipoAnimalSeleccionado = "Insecto" }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Campos de texto
                OutlinedTextField(
                    value = nombreComun,
                    onValueChange = { nombreComun = it }, // Actualizar el estado
                    label = { Text("Nombre Común", color = Color.DarkGray) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = nombreCientifico,
                    onValueChange = { nombreCientifico = it }, // Actualizar el estado
                    label = { Text("Nombre Científico", color = Color.DarkGray) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Número de Individuos
                Text(
                    "Número de Individuos", style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Start),
                    color = Color.Black
                )
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
                    Text(
                        text = numIndividuos.toString(),
                        style = MaterialTheme.typography.titleMedium
                    )
                    IconButton(
                        onClick = {
                            numIndividuos++
                        }
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Aumentar")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Tipo de Observación
                Text(
                    "Tipo de Observación", style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Start),
                    color = Color.Black
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FaunaBViewModel.TipoObservacionButton(
                        "La vio",
                        tipoObservacionSeleccionada == "La vio",
                        R.drawable.ic_la_vio
                    ) { tipoObservacionSeleccionada = "La vio" }
                    FaunaBViewModel.TipoObservacionButton(
                        "Huella",
                        tipoObservacionSeleccionada == "Huella",
                        R.drawable.ic_huella
                    ) { tipoObservacionSeleccionada = "Huella" }
                    FaunaBViewModel.TipoObservacionButton(
                        "Rastro",
                        tipoObservacionSeleccionada == "Rastro",
                        R.drawable.ic_rastro
                    ) { tipoObservacionSeleccionada = "Rastro" }
                    FaunaBViewModel.TipoObservacionButton(
                        "Cacería",
                        tipoObservacionSeleccionada == "Cacería",
                        R.drawable.ic_caceria
                    ) { tipoObservacionSeleccionada = "Cacería" }
                    FaunaBViewModel.TipoObservacionButton(
                        "Les Dijeron",
                        tipoObservacionSeleccionada == "Les Dijeron",
                        R.drawable.ic_les_dijeron
                    ) { tipoObservacionSeleccionada = "Les Dijeron" }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Altura de Observación
                Text(
                    "Altura de Observación", style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Start),
                    color = Color.Black
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FaunaBViewModel.AlturaButton(
                        "< 1mt Baja",
                        alturaObservacion == "< 1mt Baja"
                    ) { alturaObservacion = "< 1mt Baja" }
                    FaunaBViewModel.AlturaButton(
                        "1-3 mt Media",
                        alturaObservacion == "1-3 mt Media"
                    ) { alturaObservacion = "1-3 mt Media" }
                    FaunaBViewModel.AlturaButton(
                        ">3mt Alta",
                        alturaObservacion == ">3mt Alta"
                    ) { alturaObservacion = ">3mt Alta" }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Evidencias
                Text(
                    "Evidencias", style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Start),
                    color = Color.Black
                )

                Botones_capturaFBL(green700)


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

                Spacer(modifier = Modifier.height(50.dp))

            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.P)
@Preview(device = "spec:width=800px,height=1340px,dpi=300")
@Composable
fun FormularioFaunaBusquedaLibre() {
    FormularioFaunaBusquedaLibre(activity = MainActivity(), navController = rememberNavController(), modifier = Modifier)
}

@Composable
fun Botones_capturaFBL(green700: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Boton_seleccionar_archivoFBL(green700)
        Boton_abrir_camaraFBL(green700)
    }
}

@Composable
fun Boton_abrir_camaraFBL(green700: Color) {
    Button(
        onClick = { CameraPermision.value = true; isFileSelectedFBL.value = true },
        colors = ButtonDefaults.buttonColors(containerColor = green700)
    ) {
        Icon(Icons.Default.Camera, contentDescription = "Tomar foto", tint = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        Text("Tomar foto", color = Color.White)
    }
}

@Composable
fun Boton_seleccionar_archivoFBL(green700: Color) {
    Button(
        onClick = { /* Handle file selection */ isFileSelectedFBL.value = true},
        colors = ButtonDefaults.buttonColors(containerColor = green700)
    ) {
        Icon(Icons.Default.FileOpen, contentDescription = "Seleccionar archivo", tint = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        Text("Elige archivo", color = Color.White)
    }
}
