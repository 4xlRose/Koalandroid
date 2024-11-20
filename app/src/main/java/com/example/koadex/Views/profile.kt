
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.R
import com.example.koadex.clases.User
import com.example.koadex.navigate.La_navegacion
import com.example.koadex.ViewModels.PerfilScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    user: User = User("Samantha", 5, 3, 2)
) {

    val ProfileViewModel = PerfilScreenViewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Principal")}) {
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
            ProfileViewModel.ProfileHeader(user)
            Spacer(modifier = Modifier.height(32.dp))
            ProfileViewModel.ProfileInfo()
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { navController.navigate("EditProfileScreen")},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(48.dp)
            ) {
                Text("EDITAR", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val sampleUser = User(
        username = "Samantha Smith",
        totalForms = 10,
        uploadedForms = 7,
        locallyStoredForms = 3,
        posts = 15,
        following = 200,
        followers = 150,
        isloggedIn = true,
        profilePicture = R.drawable.profilepicture
    )

    PerfilScreen(navController = rememberNavController(), user = sampleUser)
}
