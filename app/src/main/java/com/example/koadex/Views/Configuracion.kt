package com.example.koadex.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.R
import com.example.koadex.clases.User
import com.example.koadex.navigate.La_navegacion
import com.example.koadex.clases.Configuracion
import androidx.compose.material.icons.filled.ArrowBack
import com.example.koadex.navigate.sampleUser


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Configuracion(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    user: User = sampleUser
) {
    // Estado para manejar las notificaciones
    var showLogoutDialog by remember { mutableStateOf(false) }
    var notificacionesActivas by remember {
        mutableStateOf(Configuracion.getInstance().notificacionesActivas)
    }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Ajustes",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigate("Principal") }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.verde_1)
                )
            )
        },
        bottomBar = {
            La_navegacion(navController, false, false, true)
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Preferencias de perfil
            Preferencias_perfil(notificacionesActivas, navController)

            Button(
                onClick = {

                    showLogoutDialog = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.rojo_1)
                )
            ) {
                Text(
                    text = "CERRAR SESION",
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Logout Confirmation Dialog
            if (showLogoutDialog) {
                user.isLogged = false
                Alerta_cerrar_sesion(showLogoutDialog, navController)
            }
        }
    }
}

@Composable
private fun Alerta_cerrar_sesion(
    showLogoutDialog: Boolean,
    navController: NavHostController
) {
    var showLogoutDialog1 = showLogoutDialog
    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when clicking outside
            showLogoutDialog1 = false
        },
        title = {
            Text(
                text = "Cerrar Sesión",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text("¿Estás seguro de que quieres cerrar sesión?")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    // Implement logout logic here
                    // For example:
                    // viewModel.logout()
                    navController.navigate("InicioSesion") {
                        // Clear the back stack
                        popUpTo(0) { inclusive = true }
                    }
                    showLogoutDialog1 = false
                }
            ) {
                Text("Sí, cerrar sesión")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    showLogoutDialog1 = false
                    navController.navigate("Configuracion")
                }
            ) {
                Text("Cancelar")
            }
        },
        containerColor = Color.White,
        textContentColor = Color.Black,
        titleContentColor = Color.Black
    )
}


@Composable
private fun Preferencias_perfil(
    notificacionesActivas: Boolean,
    navController: NavHostController
) {
    // Recordar el estado de las notificaciones usando remember
    var notificacionesActivas1 by remember { mutableStateOf(notificacionesActivas) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                // Toggle the switch when icon is clicked
                notificacionesActivas1 = !notificacionesActivas1
                Configuracion.getInstance().notificacionesActivas = notificacionesActivas1
            },
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Toggle Notifications",
                tint = colorResource(R.color.verde_1)
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = "Notificaciones",
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(
                text = if (notificacionesActivas1) "Activadas" else "Desactivadas",
                fontSize = 16.sp
            )
        }
        Switch(
            checked = notificacionesActivas1,
            onCheckedChange = {
                notificacionesActivas1 = it
                Configuracion.getInstance().notificacionesActivas = it
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = colorResource(R.color.verde_1),
                checkedTrackColor = colorResource(R.color.verde_1).copy(alpha = 0.5f)
            )
        )
    }

    // Editar perfil
    ProfileItem(
        icon = Icons.Default.Edit,
        label = "Configuración de perfil",
        value = "Configurar mi perfil",
        navController = navController,
        destino = "PerfilScreen"
    )

    // Cambiar contraseña
    ProfileItem(
        icon = Icons.Default.Lock,
        label = "Seguridad",
        value = "Cambiar contraseña",
        navController = navController,
        destino = "Perfil"
    )
}

@Composable
private fun Header_ajustes(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.verde_1))
            .padding(16.dp)
            .padding(top = 16.dp)
    ) {
        IconButton(
            onClick = { navController.navigate("Principal") },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Text(
            text = "Ajustes",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )

    }
}

@Composable
private fun ProfileItem(
    icon: ImageVector,
    label: String,
    value: String,
    navController: NavHostController = rememberNavController(),
    destino: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
         onClick = { navController.navigate(destino) },
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colorResource(R.color.verde_1)
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(
                text = value,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConfiguracionPreview() {
    Configuracion(rememberNavController(), user = sampleUser)
}