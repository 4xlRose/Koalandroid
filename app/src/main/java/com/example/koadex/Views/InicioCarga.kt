package com.example.koadex.Views

import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun InicioCarga(modifier: Modifier = Modifier, navController: NavHostController) {
    val navController = rememberNavController()
    Button(
        onClick = {
            navController.navigate("IniciaCarga")
        }
    ){
        Text("Iniciar Sesion")
    }
}


