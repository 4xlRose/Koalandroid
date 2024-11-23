package com.example.koadex.Views

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.koadex.AppViewModelProvider
import com.example.koadex.R
import com.example.koadex.ViewModels.NavigationModel
import com.example.koadex.data.UserEntity
import com.example.koadex.navigate.sampleUser
import com.example.koadex.ui.form.FormGeneralDBViewModel
import kotlinx.coroutines.launch

@Composable
fun Registro(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    model: NavigationModel
) {
    RegistroFondo(navController, modifier, model)
}
@Composable
fun RegistroFondo(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    model: NavigationModel
) {
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
        RegistroContenido(navController, modifier, model)
    }
}

@Composable
fun RegistroContenido(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    model: NavigationModel
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, top = 50.dp, end = 16.dp, bottom = 16.dp)
    ) {
        val user = sampleUser
        val coroutineScope = rememberCoroutineScope()
        val insertUser = { newUser: UserEntity ->
            coroutineScope.launch {
                model.insertUser(newUser)
            }
        }

        IconButton(
            onClick = {
                navController.navigate("InicioSesion")
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
            text = "Crea una cuenta",
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = modifier
                .padding(55.dp)
        )
        Text(
            text = "Registrate",
            fontSize = 25.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        var text3 by remember { mutableStateOf("Pepe") }
        OutlinedTextField(
            value = text3,
            onValueChange = {
                text3 = it
                user.name = it
            },
            label = { Text(
                "Nombre",
                color = Color.Black) },
            modifier = modifier
                .fillMaxWidth()
        )
        var text1 by remember { mutableStateOf("example@gmail.com") }
        OutlinedTextField(
            value = text1,
            onValueChange = {
                text1 = it
                user.email = it
                            },
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
                user.password = it
            },
            label = {
                Text(
                    "Contraseña",
                    color = Color.Black)
            },
            modifier = modifier
                .fillMaxWidth()
        )

        Spacer(
            Modifier.padding(90.dp)
        )
        Button(
            onClick = {
                insertUser(user)
                navController.navigate("InicioSesion")
            },

            modifier = modifier
                .width(140.dp)
                .height(70.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color( 0xff4e7029)),
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