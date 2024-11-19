import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.R
import com.example.koadex.navigate.La_navegacion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Principal") }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF97B96E))
            )
        },
        bottomBar = {
            La_navegacion(navController, false, false, true)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileHeader()
            Spacer(modifier = Modifier.height(32.dp))
            ProfileInfo()
            Spacer(modifier = Modifier.height(32.dp))
            EditButton()
        }
    }
}

@Composable
fun ProfileHeader() {
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
                painter = painterResource(id = R.drawable.profilepicture),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.White, CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
                    .padding(2.dp)
                    .clip(CircleShape)
                    .fillMaxSize()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Samantha Smith",
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
fun EditButton() {
    Button(
        onClick = { /* Acción de editar */ },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(48.dp)
    ) {
        Text("EDITAR", color = Color.White, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    PerfilScreen(navController = rememberNavController())
}
