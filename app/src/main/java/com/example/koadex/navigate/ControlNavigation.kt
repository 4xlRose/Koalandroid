package com.example.koadex.navigate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.koadex.Views.InicioCarga
import com.example.koadex.Views.InicioSesion

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ControladorNavegador(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Inicio") {
        composable("InicioSesion") {
            InicioSesion(navController = navController)
        }
        composable("InicioCarga") {
            InicioCarga(navController = navController)
        }

    }
}