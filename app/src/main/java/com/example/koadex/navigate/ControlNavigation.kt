package com.example.koadex.navigate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.koadex.Views.Configuracion
import com.example.koadex.Views.FormularioGeneral
import com.example.koadex.Views.InicioCarga
import com.example.koadex.Views.InicioSesion
import com.example.koadex.Views.Koadex
import com.example.koadex.Views.OlvidoContrasena
import com.example.koadex.Views.Perfil
import com.example.koadex.Views.Principal
import com.example.koadex.Views.Registro
import com.example.koadex.Views.SeleccionForm
import com.example.koadex.Views.Verificacion

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "FormularioGeneral") {
        composable("InicioCarga") {
            InicioCarga(navController = navController)
        }
        composable("InicioSesion") {
            InicioSesion(navController = navController)
        }

        composable("FormularioGeneral") {
            FormularioGeneral(navController = navController)
        }
        composable("Koadex") {
            Koadex(navController = navController)
        }
        composable("Principal") {
            Principal(navController = navController)
        }
        composable("Registro") {
            Registro(navController = navController)
        }
        composable("SeleccionForm") {
            SeleccionForm(navController = navController)
        }
        composable("Configuracion"){
            Configuracion(navController = navController)
        }
        composable("Perfil"){
            Perfil(navController = navController)
        }
        composable("OlvidoContrasena"){
            OlvidoContrasena(navController = navController)
        }
        composable("Verificacion"){
            Verificacion(navController = navController)
        }
    }
}