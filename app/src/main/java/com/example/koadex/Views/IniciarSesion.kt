package com.example.koadex.Views

import androidx.compose.material3.Button
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun InicioSesion(navController: NavController) {
    Button( onClick = {
                navController.navigate("InicioCarga")
    }) {
        Text("Pantalla de carga")
    }

}
