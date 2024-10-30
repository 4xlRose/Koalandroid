package com.example.koadex.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.koadex.AppViewModelProvider
import com.example.koadex.R
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
                formList = koadexUiState.koadexList)


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
            FormList(formList)
        }
    }
}

@Composable
 fun FormList(
    formList: List<FormEntity>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = formList) { item ->
            FormInfo(
                form = item,
                modifier = Modifier

            )
        }
    }
}

@Composable
 private fun FormInfo(
    form: FormEntity,
    modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(
            modifier = modifier
        ) {
            Text(
                text = form.name
            )

            Text(
                text = form.date
            )

            Text(
                text = form.place
            )

            Text(
                text = form.hour
            )
        }

    }
}



