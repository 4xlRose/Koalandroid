package com.example.koadex.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.koadex.R

@Composable
fun Verificacion(navController: NavHostController, modifier: Modifier = Modifier) {
    VerificacionFondo(navController, modifier)
}

@Composable
fun VerificacionFondo(navController: NavHostController, modifier: Modifier = Modifier) {
    val fondo = painterResource(R.drawable.ffigma)
    Box (
        modifier = Modifier
    )
    {
        Image (
            painter = fondo,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        VerificacionContenido(navController, modifier)
    }
}

@Composable
fun VerificacionContenido(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 32.dp)
    ) {
        Text(
            text = "Verificación",
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Text(
                text = "Por favor escriba el codigo de verificacion de 4 digitos enviado a",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 18.dp)

            )
            Text(
                text = "test@gmail.com",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,

                )
            Row(
                horizontalArrangement = Arrangement.Absolute.Center,
                modifier = Modifier
                    .padding(vertical = 16.dp)

            ) {
                var text1 = "1"
                TextField(
                    value = text1,
                    onValueChange = { text1 = it },
                    label = null,
                    modifier = Modifier
                        .size(width = 60.dp, height = 60.dp)
                        .padding(horizontal = 10.dp),
                )
                var text2 = "1"
                TextField(
                    value = text2,
                    onValueChange = { text2 = it },
                    label = null,
                    modifier = Modifier
                        .size(width = 60.dp, height = 60.dp)
                        .padding(horizontal = 10.dp)
                )
                var text3 = "1"
                TextField(
                    value = text3,
                    onValueChange = { text3 = it },
                    label = null,
                    modifier = Modifier
                        .size(width = 60.dp, height = 60.dp)
                        .padding(horizontal = 10.dp)
                )
                var text4 = "1"
                TextField(
                    value = text4,
                    onValueChange = { text4 = it },
                    label = null,
                    modifier = Modifier
                        .size(width = 60.dp, height = 60.dp)
                        .padding(horizontal = 10.dp)
                )
            }
            Text(
                text = "Reenviar código",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color(78, 112, 41)
            )
        }
        Button(
            onClick = {
                navController.navigate("InicioSesion")
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(78, 112, 41)),
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally),
        ) {
            Text(
                text = "VERIFICAR",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
            )
        }
    }
}