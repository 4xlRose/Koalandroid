package com.example.koadex.Views

import android.util.Log
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.result.Credentials
import com.example.koadex.R

@Composable
fun InicioSesion(navController: NavHostController, account: Auth0, modifier: Modifier = Modifier) {
    IniciarSesionFondo(navController,account, modifier)
}

@Composable
fun IniciarSesionFondo(navController: NavHostController,account: Auth0, modifier: Modifier = Modifier) {
    var loggedIn by remember { mutableStateOf(false) }
    var credentials by remember { mutableStateOf<Credentials?>(null) }

    val fondo = painterResource(R.drawable.login)
    Box (
        modifier = Modifier
    ) {
        Image (
            painter = fondo,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        if (loggedIn) {

        } else {
            IniciarSesionLogOutContenido(
                navController = navController,
                account = account,
                onLoginSuccess = {
                    credentials = it
                    loggedIn = true
                },
                modifier = Modifier)
        }

    }
}

@Composable
fun IniciarSesionLogOutContenido(
    navController: NavHostController,
    account: Auth0,
    onLoginSuccess: (Credentials) -> Unit,
    modifier: Modifier = Modifier
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

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
            text = stringResource(R.string.bienvenida),
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = modifier
                .padding(55.dp)
        )
        Text(
            text = stringResource(R.string.iniciar_sesion2),
            fontSize = 25.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(
                "Email",
                color = Color.Black) },
            modifier = modifier
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(
                    stringResource(R.string.contrasena),
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
            Text(text = stringResource(R.string.olvidaste_contrasena),
                fontSize = 15.sp,
                color = Color(0xff4e7029),
                textAlign = TextAlign.End,
                modifier = modifier
                    .fillMaxWidth())
        }
        // Mostrar mensaje de error si existe
        errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Error: $it", color = Color.Red)
        }
        Spacer(
            Modifier.padding(50.dp)
        )
        Button(
            onClick = {
                loginWithUsernamePassword(account, username, password, onError = { message ->
                    errorMessage = message // Actualiza el mensaje de error si ocurre un problema
                }, onSuccess = navController.navigate("Principal"))

            },

            modifier = modifier
                .width(140.dp)
                .height(70.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color( 0xff4e7029)),
        ) {
            Text(
                text = stringResource(R.string.entrar),
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
                text = stringResource(R.string.registrarse),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 22.sp
            )
        }


    }
}

private fun loginWithUsernamePassword(
    auth0: Auth0,
    username: String,
    password: String,
    onSuccess: Unit,
    onError: (String) -> Unit
) {

    val authentication = AuthenticationAPIClient(auth0)
    authentication
        .login(username, password, "Username-Password-Authentication")
        .setConnection("Username-Password-Authentication")
        .validateClaims()
        .setScope("openid profile email")
        .start(object : Callback<Credentials, AuthenticationException> {
            override fun onSuccess(result: Credentials) {
                onSuccess(result)
            }

            override fun onFailure(error: AuthenticationException) {
                // Imprime el error completo en los logs para ver más detalles
                Log.e("AuthError", "Error de autenticación: ${error.getDescription()}")
                onError(error.message ?: "Error desconocido")
            }
        })
}
