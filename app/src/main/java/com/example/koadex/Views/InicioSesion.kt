package com.example.koadex.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun InicioSesion(navController: NavHostController, modifier: Modifier = Modifier) {
    IniciarSesionFondo(navController, modifier)
}

@Composable
fun IniciarSesionFondo(navController: NavHostController, modifier: Modifier = Modifier) {
    val fondo = painterResource(R.drawable.login)
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
        IniciarSesionContenido(navController, modifier)
    }
}

@Composable
fun IniciarSesionContenido(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, top = 50.dp, end = 16.dp, bottom = 16.dp)
    ) {
        IconButton(
            onClick = {
                navController.navigate("InicioCarga")
            },

        ) {
            Icon(
                Icons.Filled.KeyboardArrowLeft, contentDescription = "Izquierda",
                tint = Color.White,
                modifier = Modifier
                    .size(size = 50.dp)

            )
        }
        Spacer(
            modifier = modifier
                .padding(50.dp)
        )
        Text(
            text = "Bienvenido",
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = modifier
                .padding(55.dp)
        )
        Text(
            text = "Iniciar Sesión",
            fontSize = 25.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        var text1 by remember { mutableStateOf("example@gmail.com") }
        OutlinedTextField(
            value = text1,
            onValueChange = { text1 = it },
            label = { Text(
                "Email",
                color = Color.Black) },
            modifier = modifier
                .fillMaxWidth()
        )
        var text2 by remember { mutableStateOf("example123") }
        OutlinedTextField(
            value = text2,
            onValueChange = {
                text2 = it
            },
            label = {
                Text(
                    "Contraseña",
                    color = Color.Black)
            },
            modifier = modifier
                .fillMaxWidth()
        )
        TextButton(onClick = {
            navController.navigate("OlvidoContrasena")
        },
            modifier = modifier
                .align(Alignment.End),

        ) {
            Text(text = "Olvidaste la contraseña",
                fontSize = 15.sp,
                color = Color(0xff4e7029),
                textAlign = TextAlign.End,
                modifier = modifier
                    .fillMaxWidth())
        }

        Spacer(
            Modifier.padding(50.dp)
        )
        Button(
            onClick = {
                navController.navigate("Principal")
            },

            modifier = modifier
                .width(140.dp)
                .height(70.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color( 0xff4e7029)),
        ) {
            Text(
                text = "Entrar",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 22.sp
            )
        }
        Spacer(
            Modifier.padding(10.dp)
        )
        Button(
            onClick = {
                navController.navigate("Registro")
            },

            modifier = modifier
                .width(140.dp)
                .height(70.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color( 0xff4e7029))
        ) {
            Text(
                text = "Registrar",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 22.sp
            )
        }


    }
}