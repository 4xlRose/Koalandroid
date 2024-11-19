package com.example.koadex.ViewModels

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.example.koadex.R
import com.example.koadex.Views.EditProfileScreen
import com.example.koadex.clases.User
import dagger.hilt.android.lifecycle.HiltViewModel

class PerfilScreenViewModel: ViewModel() {

    @Composable
    fun ProfileHeader(user: User) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF97B96E), Color(0xFF4E7029))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = user.profilePicture),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color.White, CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                        .padding(2.dp)
                        .clip(CircleShape)
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = user.username,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Activo desde Ene. 2024",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }

    @Composable
    fun ProfileInfo() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            ProfileInfoRow(Icons.Default.Email, "samanthasmith@gmail.com")
            Divider(color = Color.Gray, thickness = 0.5.dp)
            ProfileInfoRow(Icons.Default.Visibility, "Contraseña")
            Divider(color = Color.Gray, thickness = 0.5.dp)
            ProfileInfoRow(Icons.Default.Phone, "+57 312 345 6789")
            Divider(color = Color.Gray, thickness = 0.5.dp)
        }
    }

    @Composable
    fun ProfileInfoRow(icon: ImageVector, infoText: String) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = infoText, fontSize = 16.sp, color = Color.Black)
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
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
        )
    }


}