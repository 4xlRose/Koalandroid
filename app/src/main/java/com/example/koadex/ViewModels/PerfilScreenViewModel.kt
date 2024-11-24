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
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.koadex.clases.User
import com.example.koadex.data.UserEntity
import kotlinx.coroutines.launch

class PerfilScreenViewModel: ViewModel() {

    @Composable
    fun ProfileHeader(user: UserEntity) {
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
                    text = user.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Activo desde " + user.startDate,
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }

    /*
    fun formatDate(date: String): String {
        val months = listOf(
            "Ene.", "Feb.", "Mar.", "Abr.", "May.", "Jun.",
            "Jul.", "Ago.", "Sep.", "Oct.", "Nov.", "Dic."
        )
        val parts = date.split("-")
        val day = parts[2].toInt()
        val month = parts[1].toInt()
        val year = parts[0].toInt()

        return "$day de ${months[month - 1]} $year"
    }*/

    @Composable
    fun ProfileInfo(user: UserEntity) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            ProfileInfoRow(Icons.Default.Email, user.email)
            Divider(color = Color.Gray, thickness = 0.5.dp)
            ProfileInfoRow(Icons.Default.Visibility, user.password, true)
            Divider(color = Color.Gray, thickness = 0.5.dp)
            ProfileInfoRow(Icons.Default.Phone, user.phone)
            Divider(color = Color.Gray, thickness = 0.5.dp)
        }
    }

    @Composable
    fun ProfileInfoRow(icon: ImageVector, infoText: String, isPassword: Boolean = false) {
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
            
            Text(
                text = infoText,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }

    @Composable
    fun ProfileTextField(label: String, value: String, isPassword: Boolean = false): String {
        var text by remember { mutableStateOf(value) }

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text(
                text = label,
                color = Color(0xFF4E7029),
                style = TextStyle(fontWeight = FontWeight.Bold)
            )},
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
        )
        return text
    }
}