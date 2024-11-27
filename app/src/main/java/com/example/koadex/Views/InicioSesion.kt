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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.result.Credentials
import com.example.koadex.R
import com.example.koadex.ViewModels.NavigationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
*
*  koalandroid@tec.mx
*  KoalAndroid*2025
*
*/
@Composable
fun InicioSesion(navController: NavHostController,
                 account: Auth0,
                 model: NavigationModel,
                 modifier: Modifier = Modifier
) {
    IniciarSesionFondo(navController,account, model, modifier)
}

@Composable
fun IniciarSesionFondo(navController: NavHostController,
                       account: Auth0,
                       model: NavigationModel,
                       modifier: Modifier = Modifier
) {
    val logged = remember { mutableStateOf(false) }
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
        if (!logged.value) {
            IniciarSesionLogInContenido(
                navController = navController,
                account = account,
                model = model,
                onLoginSuccess = {
                    navController.navigate("Principal")
                    logged.value = true
                },
                modifier = Modifier
            )
        }
    }
}

@Composable
fun IniciarSesionLogInContenido(
    navController: NavHostController,
    account: Auth0,
    model: NavigationModel,
    onLoginSuccess: (Credentials) -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val green700 = colorResource(id = R.color.green_700)
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
            value = email,
            onValueChange = { email = it },
            label = {
                Text(
                    stringResource(R.string.email),
                    color = Color.Black
                )
            },
            modifier = modifier
                .fillMaxWidth(),

            )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(
                    stringResource(R.string.contrasena),
                    color = Color.Black
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = modifier
                .fillMaxWidth()
        )
        TextButton(
            onClick = {
                navController.navigate("OlvidoContrasena")
            },
            modifier = modifier
                .align(Alignment.End),

            ) {
            Text(
                text = stringResource(R.string.olvidaste_contrasena),
                fontSize = 15.sp,
                color = Color(0xff4e7029),
                textAlign = TextAlign.End,
                modifier = modifier
                    .fillMaxWidth()
            )
        }
        // Mostrar mensaje de error si existe
        if (errorMessage.isNotEmpty())
            errorMessage.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Usuario o contraseña incorrectos", color = Color.Red)

            } else {
            Text(text = "                                ")
        }
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = {
                    loginWithUsernamePassword(
                        account = account,
                        model = model,
                        username = email,
                        password = password,
                        onSuccess = onLoginSuccess,
                        onError = { message ->
                            errorMessage =
                                message // Actualiza el mensaje de error si ocurre un problema
                        })
                },
                modifier = Modifier.padding(5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = green700,contentColor = Color.White)
                ,
            ) {
                Text(
                    text = stringResource(R.string.entrar),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Button(
                onClick = {
                    navController.navigate("Registro")
                },

                modifier = Modifier.padding(5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = green700,contentColor = Color.White)

            ) {
                Text(
                    text = stringResource(R.string.registrarse),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

private fun loginWithUsernamePassword(
    account: Auth0,
    model: NavigationModel,
    username: String,
    password: String,
    onSuccess: (Credentials) -> Unit,
    onError: (String) -> Unit,
) {

    val authentication = AuthenticationAPIClient(account)
    val coroutineScope = CoroutineScope(model.viewModelScope.coroutineContext)
    val processUser = { email: String ->
        coroutineScope.launch {
            model.getUserByEmail(email).collect { user ->
                model.loggedUser = user ?: model.loggedUser
            }
        }
    }

    authentication
        .login(username, password, "Username-Password-Authentication")
        .setConnection("Username-Password-Authentication")
        .validateClaims()
        .setScope("openid profile email")
        .start(object : Callback<Credentials, AuthenticationException> {
            override fun onSuccess(result: Credentials) {
                model.loggedUser.name = username.slice(IntRange(0, (username.indexOf('@') - 1)))
                model.loggedUser.email = username
                model.loggedUser.password = password
                model.loggedUser.isloggedIn = true

                processUser(model.loggedUser.email)
                println("Logged as " + model.loggedUser.email)

                model.accessToken = result.accessToken

                onSuccess(result)
            }

            override fun onFailure(error: AuthenticationException) {
                // Imprime el error completo en los logs para ver más detalles
                Log.e("AuthError", "Error de autenticación: ${error.getDescription()}")
                onError(error.message ?: "Error desconocido")
            }
        })
}

