package com.example.koadex.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun Configuracion(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text= "Pantalla de configuracion")
        Button(onClick = {
            navController.navigate("Perfil")
        }) {
            Text("Editar")
        }

        Button(onClick = {
            navController.navigate("Principal")
        }) {
            Text("Principal")
        }

        Button(onClick = {
            navController.navigate("Busqueda")
        }) {
            Text("Busqueda")
        }

        Button(onClick = {
            navController.navigate("Configuracion")
        }) {
            Text("Configuracion")
        }
    }
}