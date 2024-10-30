package com.example.koadex.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.koadex.AppViewModelProvider
import com.example.koadex.data.FormEntity
import com.example.koadex.ui.principal.KoadexViewModel

@Composable
fun Koadex(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: KoadexViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val koadexUiState by viewModel.koadexUiState.collectAsState()
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text= "Pantalla de Koadex")
        KoadexContenido(
            formList = koadexUiState.koadexList,

        )
        Button(onClick = {
            navController.navigate("Principal")
        }) {
            Text("Regresar")
        }
    }
}

@Composable
fun KoadexContenido(
    formList: List<FormEntity>,
    modifier: Modifier = Modifier) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (formList.isEmpty()) {
            Text(
                text = "No hay formularios guardados",
                modifier = Modifier.fillMaxSize()
            )
        } else {
            FormList(

            )
        }
    }
}

@Composable
private fun FormList(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {

    }
}