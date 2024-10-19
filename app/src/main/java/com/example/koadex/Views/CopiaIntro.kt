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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.koadex.R


@Composable
fun CopiaIntro(navController: NavHostController){
    Fondo_vista(modifier = Modifier)
    Intro_title()
    Botton_inincio_sesion(navController = navController)
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
fun Botton_inincio_sesion(navController: NavHostController, modifier: Modifier = Modifier) {
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
                    navController.navigate("InicioSesion") // Navigate to InicioSesion
                },
                modifier = Modifier.padding(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.verde_1)
                )
            ) {
                Text(text = stringResource(R.string.iniciar_sesion))
            }
        }
    }
}
