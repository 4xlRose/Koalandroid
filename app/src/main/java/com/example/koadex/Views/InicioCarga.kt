package com.example.koadex.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import com.example.koadex.AppViewModelProvider
import com.example.koadex.R
import com.example.koadex.ui.form.FormsPredeterminedViewModel
import kotlinx.coroutines.launch
import okhttp3.internal.platform.android.BouncyCastleSocketAdapter.Companion.factory


@Composable
fun InicioCarga(navController: NavHostController, modifier: Modifier = Modifier) {
    Fondo_vista(modifier = Modifier)
    Intro_title()
    Botton_inicio_sesion(navController = navController)
}

@Composable
fun Intro_title(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(50.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.Titulo_Intro),
            color = colorResource(id = R.color.verde_oscuro_1),
            fontSize = 55.sp,
            lineHeight = 32.sp,
            modifier = Modifier
                .padding(top = 80.dp)
        )
    }
}

@Composable
fun Fondo_vista(modifier: Modifier = Modifier){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ){
        Image(
            painter = painterResource(R.drawable.vista_intro_fondo),
            contentDescription = "Imagen",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun Botton_inicio_sesion(navController: NavHostController, modifier: Modifier = Modifier) {
    val viewModelInicialiazar: FormsPredeterminedViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxSize()
            .padding(bottom = 100.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.Crear_cuenta))
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModelInicialiazar.inicializarTablasPredeterminadas()
                        navController.navigate("InicioSesion") // Navigate to InicioSesion
                    }

                },
                modifier = Modifier.padding(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.verde_1)
                )
            ) {
                Text(text = stringResource(R.string.iniciar_sesion),
                        fontWeight = FontWeight.Bold,
                        color = Color.White)
            }
        }
    }
}
