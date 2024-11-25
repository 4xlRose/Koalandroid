package com.example.koadex.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.ViewModels.NavigationModel
import com.example.koadex.ViewModels.PerfilScreenViewModel
import com.example.koadex.data.UserEntity
import kotlinx.coroutines.launch


@Composable
fun EditProfileScreen(
    navController: NavHostController,
    user: UserEntity,
    navModel: NavigationModel,
    modifier: Modifier = Modifier
) {

    val model = PerfilScreenViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Botón de regreso
        IconButton(
            onClick = { navController.navigate("PerfilScreen") },
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
        val name = model.ProfileTextField("Nombre", user.name)
        val email = model.ProfileTextField("Email", user.email) // Puedes añadir un campo de email en `User`
        val password = model.ProfileTextField("Contraseña", user.password, isPassword = true)
        val phone = model.ProfileTextField("Teléfono", user.phone) // Puedes añadir teléfono a `User`

        Spacer(modifier = Modifier.height(32.dp))

        val couroutineScope = rememberCoroutineScope()
        val userUpdate = {
            couroutineScope.launch {
                navModel.updateUser(user)
            }
        }

        // Botón Guardar
        Button(
            onClick = {
                user.name = name
                //user.email = email
                //user.password = password
                user.phone = phone
                userUpdate()
                navController.navigate("PerfilScreen")
                      },
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

/*
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditProfileScreenPreview() {
    val sampleUser = UserEntity(
        id = 1,
        name = sampleUser.name,
        email = sampleUser.email,
        password = sampleUser.password,
        totalForms = sampleUser.totalForms,
        uploadedForms = sampleUser.uploadedForms,
        locallyStoredForms = sampleUser.locallyStoredForms,
        posts = sampleUser.posts,
        following = sampleUser.following,
        followers = sampleUser.followers,
        isloggedIn = sampleUser.isloggedIn,
        idZone = sampleUser.idZone,
        profilePicture = sampleUser.profilePicture
    )

    EditProfileScreen(navController = rememberNavController(), user = sampleUser, navModel = model)
}
*/


