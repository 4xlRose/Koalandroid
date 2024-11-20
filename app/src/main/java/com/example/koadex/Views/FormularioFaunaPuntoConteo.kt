@file:JvmName("FormularioFaunaPuntoConteoKt")

package com.example.koadex.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.koadex.ViewModels.FormularioFaunaPuntoConteoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioFaunaPuntoConteo(navController: NavHostController, modifier: Modifier = Modifier) {
    var numIndividuos by remember { mutableStateOf(1) }
    var alturaObservacion by remember { mutableStateOf("") }
    var tipoAnimalSeleccionado by remember { mutableStateOf("") }
    var zonaSeleccionada by remember { mutableStateOf("") }
    var tipoObservacionSeleccionada by remember { mutableStateOf("") }
    val actionButtonColors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029))

    val FaunaBViewModel = FormularioFaunaPuntoConteoViewModel()

    //Estado de scroll
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
    ) {
        // Barra superior
        TopAppBar(
            title = { Text("Formulario", color = Color.Black) },
            navigationIcon = {
                IconButton(onClick = { navController.navigate("TiposForms") }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFB4D68F))
        )

        // Contenido desplazable
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState) // Habilitar scroll
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Zona
            Text("Zona", style = MaterialTheme.typography.titleMedium)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FaunaBViewModel.ZonaButton("Bosque", zonaSeleccionada == "Bosque", R.drawable.ic_bosque) { zonaSeleccionada = "Bosque" }
                FaunaBViewModel.ZonaButton("Arreglo Agroforestal", zonaSeleccionada == "Arreglo Agroforestal", R.drawable.ic_agroforestal) { zonaSeleccionada = "Arreglo Agroforestal" }
                FaunaBViewModel.ZonaButton("Cultivos Transitorios", zonaSeleccionada == "Cultivos Transitorios", R.drawable.ic_cultivostransitorios) { zonaSeleccionada = "Cultivos Transitorios" }
                FaunaBViewModel.ZonaButton("Cultivos Permanentes", zonaSeleccionada == "Cultivos Permanentes", R.drawable.ic_cultivospermanentes) { zonaSeleccionada = "Cultivos Permanentes" }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tipo de Animal
            Text("Tipo de Animal", style = MaterialTheme.typography.titleMedium)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FaunaBViewModel.TipoAnimalButton("Mamífero", tipoAnimalSeleccionado == "Mamífero", R.drawable.ic_mamifero) { tipoAnimalSeleccionado = "Mamífero" }
                FaunaBViewModel.TipoAnimalButton("Ave", tipoAnimalSeleccionado == "Ave", R.drawable.ic_ave) { tipoAnimalSeleccionado = "Ave" }
                FaunaBViewModel.TipoAnimalButton("Reptil", tipoAnimalSeleccionado == "Reptil", R.drawable.ic_reptil) { tipoAnimalSeleccionado = "Reptil" }
                FaunaBViewModel.TipoAnimalButton("Anfibio", tipoAnimalSeleccionado == "Anfibio", R.drawable.ic_anfibio) { tipoAnimalSeleccionado = "Anfibio" }
                FaunaBViewModel.TipoAnimalButton("Insecto", tipoAnimalSeleccionado == "Insecto", R.drawable.ic_insecto) { tipoAnimalSeleccionado = "Insecto" }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de texto
            OutlinedTextField(
                value = "", onValueChange = { /* Actualizar estado */ },
                label = { Text("Nombre Común") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = "", onValueChange = { /* Actualizar estado */ },
                label = { Text("Nombre Científico") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Número de Individuos
            Text("Número de Individuos", style = MaterialTheme.typography.titleMedium)
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
                    onClick = {
                        numIndividuos++
                    }
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Aumentar")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tipo de Observación
            Text("Tipo de Observación", style = MaterialTheme.typography.titleMedium)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FaunaBViewModel.TipoObservacionButton("La vio", tipoObservacionSeleccionada == "La vio", R.drawable.ic_la_vio) { tipoObservacionSeleccionada = "La vio" }
                FaunaBViewModel.TipoObservacionButton("Huella", tipoObservacionSeleccionada == "Huella", R.drawable.ic_huella) { tipoObservacionSeleccionada = "Huella" }
                FaunaBViewModel.TipoObservacionButton("Rastro", tipoObservacionSeleccionada == "Rastro", R.drawable.ic_rastro) { tipoObservacionSeleccionada = "Rastro" }
                FaunaBViewModel.TipoObservacionButton("Cacería", tipoObservacionSeleccionada == "Cacería", R.drawable.ic_caceria) { tipoObservacionSeleccionada = "Cacería" }
                FaunaBViewModel.TipoObservacionButton("Les Dijeron", tipoObservacionSeleccionada == "Les Dijeron", R.drawable.ic_les_dijeron) { tipoObservacionSeleccionada = "Les Dijeron" }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Altura de Observación
            Text("Altura de Observación", style = MaterialTheme.typography.titleMedium)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FaunaBViewModel.AlturaButton("< 1mt Baja", alturaObservacion == "< 1mt Baja") { alturaObservacion = "< 1mt Baja" }
                FaunaBViewModel.AlturaButton("1-3 mt Media", alturaObservacion == "1-3 mt Media") { alturaObservacion = "1-3 mt Media" }
                FaunaBViewModel.AlturaButton(">3mt Alta", alturaObservacion == ">3mt Alta") { alturaObservacion = ">3mt Alta" }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Evidencias
            Text("Evidencias", style = MaterialTheme.typography.titleMedium)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { /* Elegir archivo */ }, colors = actionButtonColors) {
                    Text("Elige archivo")
                }
                Button(onClick = { /* Tomar foto */ }, colors = actionButtonColors) {
                    Text("Tomar foto")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Observaciones
            OutlinedTextField(
                value = "", onValueChange = { /* Actualizar estado */ },
                label = { Text("Observaciones") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botones de acción
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = { /* Acción de volver */ }, colors = actionButtonColors) {
                    Text("ATRAS", color = Color.White)
                }

                Button(onClick = { /* Acción de enviar */ }, colors = actionButtonColors) {
                    Text("ENVIAR", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFormularioPuntoConteo() {
    FormularioFaunaPuntoConteo(navController = rememberNavController(), modifier = Modifier)
}