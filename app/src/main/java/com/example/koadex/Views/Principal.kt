package com.example.koadex.Views

import android.media.Spatializer
import android.widget.Space
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.clases.User

@Composable
fun Principal(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    user: User = User("Samantha", 5, 3, 2)
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        // logo y boton para perfil
        Logo_perfil()

        // Contenido principal
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // La cosa girando
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {

                // Centered greeting
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Hola,\n${user.username}",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        lineHeight = 40.sp
                    )
                }

                // Add button at the bottom
                FloatingActionButton(
                    onClick = { navController.navigate("FormularioEspecies") },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 20.dp),
                    containerColor = Color(0xFF4E7029)
                ) {
                    Icon(Icons.Default.Add, "Add", tint = Color.White)
                }
            }

            // Dashboard title
            Text(
                text = "Dashboard",
                modifier = Modifier.padding(start = 16.dp, top = 32.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            // Warning message if needed
            if (user.locallyStoredForms > 1) {
                Advertencia(user)
            }

            // Formularios en total
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${user.totalForms} Forms",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(text = "En Total", fontSize = 16.sp)

                    CircularProgressIndicator(
                        user,
                        modifier = Modifier
                            .size(200.dp)
                            .padding(16.dp)
                    )
                }
            }

            // Bottom navigation
            La_navegacion()
        }
    }
}
// SECCION DE NAVEGACION //
@Composable
private fun La_navegacion() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BottomNavItem(Icons.Default.Home, "Inicio", true, navigation = rememberNavController(),destino = "Principal")
            BottomNavItem(Icons.Default.Search, "Búsqueda", false, navigation = rememberNavController(),destino = "Koadex")
            BottomNavItem(Icons.Default.Settings, "Configuración", false, navigation = rememberNavController(),destino = "Perfil")
        }
    }
}
@Composable
private fun BottomNavItem(icon: ImageVector, label: String, selected: Boolean, destino: String = "", navigation: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {
        IconButton(
            onClick = { navigation.navigate(destino) }
        ) {
            Icon(
                icon,
                contentDescription = label,
                tint = if (selected) Color(0xFF4E7029) else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = label,
            color = if (selected) Color(0xFF4E7029) else Color.Gray,
            fontSize = 12.sp
        )
    }
}
///////////////////////////

////// LA ADVERTENCIA /////
@Composable
private fun Advertencia(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE4E4)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Warning,
                contentDescription = "Warning",
                tint = Color(0xFFD32F2F)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Emergencia",
                    color = Color(0xFFD32F2F),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Tienes ${user.locallyStoredForms} formularios sin subir a la nube",
                    color = Color(0xFFD32F2F)
                )
            }
        }
    }
}
///////////////////////////

////// LOGO Y PERFIL /////
@Composable
private fun Logo_perfil() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Logo a la izquierda
                IconButton(
                    onClick = { /* No action */ },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Code, // Falta ponerle el de AWAQ
                        contentDescription = "Logo",
                        tint = Color(0xFF4E7029)
                    )
                }

                // Botón de perfil a la derecha
                IconButton(
                    onClick = { /* No action */ },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF4E7029))
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = Color.White
                    )
                }
            }
        }


    }
}
///////////////////////////

////// CIRCULAR PROGRESS INDICATOR /////
@Composable
private fun CircularProgressIndicator(user: User, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 20.dp.toPx()
            val diameter = size.minDimension - strokeWidth
            val radius = diameter / 2
            val center = Offset(size.width / 2, size.height / 2)

            // Draw progress arc
            drawArc(
                color = Color(0xFF4E7029),
                startAngle = -90f,
                sweepAngle = 240f, // Adjust this value based on your needs
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(diameter, diameter),
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("${user.uploadedForms} Forms", fontWeight = FontWeight.Bold)
            Text("Subidos", fontSize = 12.sp, color = Color(0xFF4E7029))
            Spacer(modifier = Modifier.height(8.dp))
            Text("${user.locallyStoredForms} Forms", fontWeight = FontWeight.Bold)
            Text("Guardados", fontSize = 12.sp, color = Color.Red)
        }
    }
}
////////////////////////////////////////

@Preview(showBackground = true)
@Composable
fun PreviewPrincipal() {
    Principal(
        navController = rememberNavController(),
        user = User("Samantha", 5, 3, 2)
    )
}