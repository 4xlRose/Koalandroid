package com.example.koadex.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.R
import com.example.koadex.clases.User


@Composable
fun EditProfileScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    user: User
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Botón de regreso
        IconButton(
            onClick = { /* Acción al presionar volver */ },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(contentAlignment = Alignment.BottomEnd) {
            Image(
                painter = painterResource(id = user.profilePicture),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { /* Acción para cambiar imagen */ },
                modifier = Modifier
                    .size(36.dp)
                    .background(Color(0xFF4E7029), CircleShape)
            ) {
                Icon(
                    Icons.Default.CameraAlt,
                    contentDescription = "Change Picture",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Campos de entrada
        ProfileTextField("Nombre", user.username)
        ProfileTextField("Email", "samanthasmith@gmail.com") // Puedes añadir un campo de email en `User`
        ProfileTextField("Contraseña", "********", isPassword = true)
        ProfileTextField("Teléfono", "+57 312 345 6789") // Puedes añadir teléfono a `User`

        Spacer(modifier = Modifier.height(32.dp))

        // Botón Guardar
        Button(
            onClick = { /* Acción para guardar */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "GUARDAR",
                color = Color.White,
                modifier = Modifier.padding(8.dp),
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun ProfileTextField(label: String, value: String, isPassword: Boolean = false) {
    var text by remember { mutableStateOf(value) }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(
            text = label,
            color = Color(0xFF4E7029),
            style = TextStyle(fontWeight = FontWeight.Bold))
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    val sampleUser = User(
        username = "Samantha Smith",
        totalForms = 10,
        uploadedForms = 7,
        locallyStoredForms = 3,
        posts = 15,
        following = 200,
        followers = 150,
        isloggedIn = true,
        profilePicture = R.drawable.profilepicture // Recurso de imagen predeterminado
    )

    EditProfileScreen(navController = rememberNavController(), user = sampleUser)
}


