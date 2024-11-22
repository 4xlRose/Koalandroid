package com.example.koadex.ViewModels

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.R
import com.example.koadex.clases.User
import com.example.koadex.navigate.La_navegacion
import com.example.koadex.navigate.sampleUser

class Principal_ViewModel : ViewModel(

) {
    var user = sampleUser



    @Composable
    public fun Bienvenida_Agregar_formulario(
        `intro-base`: String,
        user: User,
        navController: NavHostController,
        buttonColor: Color = Color(0xFF4E7029)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(top = 40.dp)
        ) {

            // Mensaje de bienvenida

            Column(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center

                ) {
                    Text(
                        text = `intro-base` + " ${user.username}",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        lineHeight = 60.sp
                    )
                }


            }


            // El boton
            FloatingActionButton(
                onClick = { navController.navigate("FormularioGeneral") },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = 60.dp)
                    .size(80.dp)
                ,

                containerColor = buttonColor
            ) {
                Icon(
                    Icons.Default.Add,
                    "Add",
                    tint = Color.White,
                    modifier = Modifier.size(60.dp)
                )
            }


        }
    }

    ////// LA ADVERTENCIA /////
    @Composable
    public fun Advertencia(user: User) {

        val rojo_1 = colorResource(R.color.rojo_1)
        val rojo_2 = colorResource(R.color.rojo_2)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = rojo_2),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Warning,
                    contentDescription = "Warning",
                    tint = rojo_1
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Advertencia",
                        color = rojo_1,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Tienes ${user.locallyStoredForms} formularios sin subir a la nube",
                        color = rojo_1
                    )
                }
            }
        }
    }
///////////////////////////

    ////// LOGO Y PERFIL /////
    @Composable
    public fun Logo_perfil(
        navigation: NavHostController = rememberNavController()
    ) {
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
                        onClick = { navigation.navigate("Profile") },
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
    public fun Contador_formularios(
        user: User,
        `formulario-base`: String
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp)
        ) {
            if (user.totalForms > 0) {

                Text(
                    text = "${user.uploadedForms + user.locallyStoredForms} " + `formulario-base`,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                CircularProgressIndicator(
                    user,
                    modifier = Modifier
                        .padding(20.dp)
                )
            }else {
                Text(
                    text = "No hay formularios disponibles",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
    @Composable
    public fun CircularProgressIndicator(
        user: User,
        modifier: Modifier = Modifier
    ) {

        val verde_1 = colorResource(R.color.verde_1)
        val verde_oscuro_1 = colorResource(R.color.verde_oscuro_1)
        val rojo_1 = colorResource(R.color.rojo_1)
        val progress = user.uploadedForms.toFloat() / (user.uploadedForms + user.locallyStoredForms)

        Box(modifier = modifier) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val strokeWidth = 30.dp.toPx()
                val diameter = size.minDimension - strokeWidth
                val radius = diameter / 2
                val center = Offset(size.width / 2, size.height / 2)

                // Draw progress arc
                drawArc(
                    color = verde_1,
                    startAngle = -90f,
                    sweepAngle = progress * 360,
                    useCenter = false,
                    topLeft = Offset(center.x - radius, center.y - radius),
                    size = Size(diameter, diameter),
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .fillMaxHeight()
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                if(user.uploadedForms > 0) {
                    Text("${user.uploadedForms} Forms", fontWeight = FontWeight.Bold)
                    Text("Subidos", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = verde_1)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                if(user.locallyStoredForms > 0) {
                    Text("${user.locallyStoredForms} Forms", fontWeight = FontWeight.Bold)
                    Text("Guardados", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = rojo_1)
                }
            }
        }
    }

    @Composable
    public  fun nav_Bar(navController: NavHostController){
        La_navegacion(navController, true, false, false)
    }
}