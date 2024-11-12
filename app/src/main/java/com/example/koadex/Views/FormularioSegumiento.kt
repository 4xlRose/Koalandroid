package com.example.koadex.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.example.koadex.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioScreen() {
    var numIndividuos by remember { mutableStateOf(1) }
    var alturaObservacion by remember { mutableStateOf("") }
    var tipoAnimalSeleccionado by remember { mutableStateOf("") }
    var zonaSeleccionada by remember { mutableStateOf("") }
    var tipoObservacionSeleccionada by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = { Text("Formulario", color = Color.Black) },
            navigationIcon = {
                IconButton(onClick = { /* Acción para regresar */ }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF97B96E))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Zona
        Text("Zona", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ZonaButton("Bosque", zonaSeleccionada == "Bosque", R.drawable.ic_bosque) { zonaSeleccionada = "Bosque" }
            ZonaButton("Arreglo Agroforestal", zonaSeleccionada == "Arreglo Agroforestal", R.drawable.ic_agroforestal) { zonaSeleccionada = "Arreglo Agroforestal" }
            ZonaButton("Cultivos Transitorios", zonaSeleccionada == "Cultivos Transitorios", R.drawable.ic_cultivostransitorios) { zonaSeleccionada = "Cultivos Transitorios" }
            ZonaButton("Cultivos Permanentes", zonaSeleccionada == "Cultivos Permanentes", R.drawable.ic_cultivospermanentes) { zonaSeleccionada = "Cultivos Permanentes" }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tipo de Animal
        Text("Tipo de Animal", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(1.dp)) {
            TipoAnimalButton("Mamífero", tipoAnimalSeleccionado == "Mamífero", R.drawable.ic_mamifero) { tipoAnimalSeleccionado = "Mamífero" }
            TipoAnimalButton("Ave", tipoAnimalSeleccionado == "Ave", R.drawable.ic_ave) { tipoAnimalSeleccionado = "Ave" }
            TipoAnimalButton("Reptil", tipoAnimalSeleccionado == "Reptil", R.drawable.ic_reptil) { tipoAnimalSeleccionado = "Reptil" }
            TipoAnimalButton("Anfibio", tipoAnimalSeleccionado == "Anfibio", R.drawable.ic_anfibio) { tipoAnimalSeleccionado = "Anfibio" }
            TipoAnimalButton("Insecto", tipoAnimalSeleccionado == "Insecto", R.drawable.ic_insecto) { tipoAnimalSeleccionado = "Insecto" }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "", onValueChange = { /* Actualizar estado */ },
            label = { Text("Nombre Común") },
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 8.dp)

        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "", onValueChange = { /* Actualizar estado */ },
            label = { Text("Nombre Científico") },
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 8.dp)

        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Número de Individuos", style = MaterialTheme.typography.titleMedium)
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    if (numIndividuos > 1) numIndividuos-- // Decrementar solo si es mayor que 1
                }
            ) {
                Icon(Icons.Filled.Remove, contentDescription = "Disminuir")
            }
            Text(text = numIndividuos.toString(), style = MaterialTheme.typography.titleMedium)
            IconButton(
                onClick = {
                    numIndividuos++ // Incrementar el número de individuos
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Aumentar")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("Tipo de Observación", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TipoObservacionButton("La vio", tipoObservacionSeleccionada == "La vio", R.drawable.ic_la_vio) { tipoObservacionSeleccionada = "La vio" }
            TipoObservacionButton("Huella", tipoObservacionSeleccionada == "Huella", R.drawable.ic_huella) { tipoObservacionSeleccionada = "Huella" }
            TipoObservacionButton("Rastro", tipoObservacionSeleccionada == "Rastro", R.drawable.ic_rastro) { tipoObservacionSeleccionada = "Rastro" }
            TipoObservacionButton("Cacería", tipoObservacionSeleccionada == "Cacería", R.drawable.ic_caceria) { tipoObservacionSeleccionada = "Cacería" }
            TipoObservacionButton("Les Dijeron", tipoObservacionSeleccionada == "Les Dijeron", R.drawable.ic_les_dijeron) { tipoObservacionSeleccionada = "Les Dijeron" }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Altura de Observación", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AlturaButton(
                text = "< 1mt Baja",
                isSelected = alturaObservacion == "< 1mt Baja",
                onClick = { alturaObservacion = "< 1mt Baja" }
            )
            AlturaButton(
                text = "1-3 mt Media",
                isSelected = alturaObservacion == "1-3 mt Media",
                onClick = { alturaObservacion = "1-3 mt Media" }
            )
            AlturaButton(
                text = ">3mt Alta",
                isSelected = alturaObservacion == ">3mt Alta",
                onClick = { alturaObservacion = ">3mt Alta" }
            )
        }
        //test
        Spacer(modifier = Modifier.height(16.dp))

        Text("Evidencias", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { /* Elegir archivo */ }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF97B96E))) {
                Text("Elige archivo")
            }
            Button(onClick = { /* Tomar foto */ }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF97B96E))) {
                Text("Tomar foto")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "", onValueChange = { /* Actualizar estado */ },
            label = { Text("Observaciones") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { /* Acción de volver */ }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF97B96E))) {
                Text("ATRAS", color = Color.White)
            }
            Button(onClick = { /* Acción de enviar */ }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF97B96E))) {
                Text("ENVIAR", color = Color.White)
            }
        }
    }
}



@Composable
fun AlturaButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .background(if (isSelected) Color(0xFF97B96E) else Color.Transparent)
            .padding(8.dp)
            .clickable(onClick = onClick),
        color = if (isSelected) Color.Black else Color.Gray
    )
}

@Composable
fun ZonaButton(text: String, isSelected: Boolean, iconRes: Int, onClick: () -> Unit) {
    Image(
        painter = painterResource(id = iconRes),
        contentDescription = text,
        modifier = Modifier
            .size(80.dp)
            .aspectRatio(1.2f)
            .background(if (isSelected) Color(0xFF97B96E) else Color.Transparent)
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
            .size(75.dp) // Tamaño reducido
            .background(if (isSelected) Color(0xFF97B96E) else Color.Transparent)
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
            .background(if (isSelected) Color(0xFF97B96E) else Color.Transparent)
            .padding(4.dp)
            .clickable(onClick = onClick)
    )
}



@Preview(showBackground = true)
@Composable
fun PreviewFormularioScreen() {
    FormularioScreen()
}