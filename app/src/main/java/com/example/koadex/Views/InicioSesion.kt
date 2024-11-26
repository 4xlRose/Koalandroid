package com.example.koadex.Views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.SolidColor
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
            Principal(navController, model.loggedUser)
        } else {
            IniciarSesionLogInContenido(
                navController = navController,
                account = account,
                model = model,
                onLoginSuccess = {
                    credentials = it
                    loggedIn = true
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
    val green900 = colorResource(id = R.color.verde_1) // Assuming you have this color defined

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        // Boton para regresar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { navController.navigate("InicioCarga") },
                modifier = Modifier.size(50.dp)
            ) {
                Icon(
                    Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "Regresar",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = stringResource(R.string.iniciar_sesion2),
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Email TextField with Email Icon
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                leadingIcon = {
                    Icon(
                        Icons.Filled.Email,
                        contentDescription = "Email",
                        tint = green700
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password TextField with Lock Icon
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                leadingIcon = {
                    Icon(
                        Icons.Filled.Lock,
                        contentDescription = "Contraseña",
                        tint = green700
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Forgot Password Link
            TextButton(
                onClick = { navController.navigate("OlvidoContrasena") },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    color = green700,
                    fontSize = 18.sp,
                )
            }

            // Error Message
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Login Button with Elevated Style
            ElevatedButton(
                onClick = {
                    loginWithUsernamePassword(
                        account = account,
                        model = model,
                        username = email,
                        password = password,
                        onSuccess = onLoginSuccess,
                        onError = { message ->
                            errorMessage = message
                        }
                    )
                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = green700,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 2.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Iniciar Sesión",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Register Button with Outlined Style
            OutlinedButton(
                onClick = { navController.navigate("Registro") },
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = SolidColor(green900)
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = green900
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Registrarse",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
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
                onSuccess(result)
            }

            override fun onFailure(error: AuthenticationException) {
                // Imprime el error completo en los logs para ver más detalles
                Log.e("AuthError", "Error de autenticación: ${error.getDescription()}")
                onError(error.message ?: "Error desconocido")
            }
        })
}

