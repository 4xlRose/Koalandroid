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
fun Principal(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text= "Pantalla de Principal")
        Button(onClick = {
            navController.navigate("FormularioGeneral")
        }) {
            Text("Formulario General")
        }
        Button(onClick = {
            navController.navigate("Koadex")
        }) {
            Text("Busqueda")
        }

    }
}