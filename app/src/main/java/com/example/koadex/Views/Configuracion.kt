package com.example.koadex.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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

@Composable
fun Configuracion(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    user: User = User("Samantha Smith", 5, 3, 2, 10, 20, 15)
) {
    // Estado para manejar las notificaciones
    var notificacionesActivas by remember { mutableStateOf(Configuracion.getInstance().notificacionesActivas)}

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        Header_ajustes(navController)

        // Contenido principal
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // aqui se maneja las notificaciones, cambiar contraseña, etc
            Preferencias_perfil(notificacionesActivas, navController)


            // Botón de cerrar sesión
            Button(
                onClick = { /* Implementar diálogo de confirmación */ },
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
        }

        Spacer(Modifier.weight(1f))
        // barra de navegacion
        La_navegacion(navController, false, false, true)
    }
}

@Composable
private fun Preferencias_perfil(
    notificacionesActivas: Boolean,
    navController: NavHostController
) {
    // Toggle para notificaciones
    var notificacionesActivas1 = notificacionesActivas
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        IconButton(
            onClick = { /* No action */ },
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = colorResource(R.color.verde_1),

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
        destino = "Perfil"
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
    Configuracion(rememberNavController(), user = User("Samantha Smith", 5, 3, 2, 10, 20, 15))
}