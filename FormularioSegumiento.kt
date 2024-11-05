package com.example.koadex.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6F0DA))
            .padding(16.dp),
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

        Text("Zona", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Image(painterResource(id = R.drawable.ic_bosque), contentDescription = "Bosque", modifier = Modifier.size(64.dp))
            Image(painterResource(id = R.drawable.ic_agroforestal), contentDescription = "Arreglo Agroforestal", modifier = Modifier.size(64.dp))
            Image(painterResource(id = R.drawable.ic_cultivostransitorios), contentDescription = "Cultivos Transitorios", modifier = Modifier.size(64.dp))
            Image(painterResource(id = R.drawable.ic_cultivospermanentes), contentDescription = "Cultivos Permanentes", modifier = Modifier.size(64.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Tipo de Animal", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Image(painterResource(id = R.drawable.ic_mamifero), contentDescription = "Mamífero", modifier = Modifier.size(64.dp))
            Image(painterResource(id = R.drawable.ic_ave), contentDescription = "Ave", modifier = Modifier.size(64.dp))
            Image(painterResource(id = R.drawable.ic_reptil), contentDescription = "Reptil", modifier = Modifier.size(64.dp))
            Image(painterResource(id = R.drawable.ic_anfibio), contentDescription = "Anfibio", modifier = Modifier.size(64.dp))
            Image(painterResource(id = R.drawable.ic_insecto), contentDescription = "Insecto", modifier = Modifier.size(64.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

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

        Text("Número de Individuos", style = MaterialTheme.typography.titleMedium)
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Disminuir número */ }) {
                Icon(Icons.Filled.Remove, contentDescription = "Disminuir")
            }
            Text(text = "6", style = MaterialTheme.typography.titleMedium)
            IconButton(onClick = { /* Aumentar número */ }) {
                Icon(Icons.Filled.Add, contentDescription = "Aumentar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Tipo de Observación", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Image(painterResource(id = R.drawable.ic_la_vio), contentDescription = "La vio", modifier = Modifier.size(64.dp))
            Image(painterResource(id = R.drawable.ic_huella), contentDescription = "Huella", modifier = Modifier.size(64.dp))
            Image(painterResource(id = R.drawable.ic_rastro), contentDescription = "Rastro", modifier = Modifier.size(64.dp))
            Image(painterResource(id = R.drawable.ic_caceria), contentDescription = "Cacería", modifier = Modifier.size(64.dp))
            Image(painterResource(id = R.drawable.ic_les_dijeron), contentDescription = "Les Dijeron", modifier = Modifier.size(64.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Altura de Observación", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("< 1mt Baja", modifier = Modifier.border(1.dp, Color.Gray).padding(8.dp))
            Text("1-3 mt Media", modifier = Modifier.border(1.dp, Color.Gray).padding(8.dp))
            Text(">3mt Alta", modifier = Modifier.border(1.dp, Color.Gray).padding(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Evidencias", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { /* Elegir archivo */ }, modifier = Modifier, colors = buttonColors(containerColor = Color(0xFF97B96E))) { Text("Elige archivo") }
            Button(onClick = { /* Tomar foto */ }, modifier = Modifier, colors = buttonColors(containerColor = Color(0xFF97B96E))) { Text("Tomar foto") }
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
            Button(onClick = { /* Acción de volver */ }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF97B96E))
            ) {
                Text("ATRAS", color = Color.White)
            }
            Button(onClick = { /* Acción de enviar */ }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF97B96E))
            ) {
                Text("ENVIAR", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFormularioScreen() {
    FormularioScreen()
}

