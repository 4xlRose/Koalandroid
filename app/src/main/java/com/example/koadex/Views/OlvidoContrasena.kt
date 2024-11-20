package com.example.koadex.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.koadex.R

@Composable
fun OlvidoContrasena(navController: NavHostController, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()
        .padding(top = 30.dp)) {
        Image(
            painter = painterResource(R.drawable.ffigma),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        IconButton(
            onClick = {
                navController.navigate("InicioSesion")
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Volver",
                tint = Color.White,
                modifier = Modifier.size(50.dp)
            )
        }

        Text(
            text = "Olvidaste la contraseña?",
            fontSize = 34.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 24.dp, top = 80.dp),
            textAlign = TextAlign.Start,
            lineHeight = 30.sp
        )

        Text(
            text = "Por favor escribe tu email para poder recibir un código de verificación.",
            fontSize = 16.sp,
            color = Color(0xFF3F3F3F),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(start = 16.dp, top = 265.dp, end = 16.dp),
            textAlign = TextAlign.Center,
            lineHeight = 19.sp
        )

        OutlinedTextField(
            value = "example@gmail.com",
            onValueChange = { /* Accion al cambiar el texto */ },
            label = { Text("Email") },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 330.dp)
                .fillMaxWidth(0.9f), // Ancho del campo de texto
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true // Un solo renglon
        )

        Button(
            onClick = { navController.navigate("Verificacion") },
            shape = RoundedCornerShape(50), // Borde redondeado
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 83.dp)
                .fillMaxWidth(0.4f) // Ancho del botnn
        ) {
            Text(
                text = "ENVIAR",
                color = Color.White,
                fontSize = 23.sp
            )
        }
    }
}