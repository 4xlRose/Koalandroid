package com.example.koadex.navigate

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.auth0.android.Auth0
import com.example.koadex.AppViewModelProvider
import com.example.koadex.MainActivity
import com.example.koadex.R
import com.example.koadex.ViewModels.NavigationModel

import com.example.koadex.Views.Configuracion
import com.example.koadex.Views.FormularioCamaraTrampa
import com.example.koadex.Views.FormularioCuadrante
import com.example.koadex.Views.FormularioEspecies
import com.example.koadex.Views.FormularioFaunaBusquedaLibre
import com.example.koadex.Views.FormularioFaunaPuntoConteo
import com.example.koadex.Views.FormularioGeneral
import com.example.koadex.Views.InicioCarga
import com.example.koadex.Views.InicioSesion
import com.example.koadex.Views.Koadex
import com.example.koadex.Views.OlvidoContrasena
import com.example.koadex.Views.Principal
import com.example.koadex.Views.Registro
import com.example.koadex.Views.Verificacion
import com.example.koadex.Views.FormularioSeguimiento
import com.example.koadex.Views.FormularioSeleccion
import com.example.koadex.Views.FormularioVariablesClimaticas
import com.example.koadex.Views.EditProfileScreen
import com.example.koadex.Views.PerfilScreen
import com.example.koadex.data.UserEntity
import com.example.koadex.ui.form.FormGeneralDBViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/*
*
*  koalandroid@tec.mx
*  KoalAndroid*2025
*
*/
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun Navigation(activity: MainActivity, account: Auth0, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val model: NavigationModel = viewModel(factory = AppViewModelProvider.Factory)
    
    //NavHost(navController = navController, startDestination = "Principal") {
    NavHost(navController = navController, startDestination = "InicioCarga") {
        composable("InicioCarga") {
            InicioCarga(navController = navController)
        }
        composable("InicioSesion") {
            InicioSesion(navController = navController, account = account, model = model)
        }

        composable("Koadex") {
            Koadex(navController = navController)
        }
        composable("Principal") {
            Principal(navController = navController, user = model.loggedUser)
        }
        composable("Registro") {
            Registro(navController = navController, account = account, model = model)
        }
        /*composable("SeleccionForm") {
            SeleccionForm(navController = navController)
        }*/
        composable("Configuracion"){
            Configuracion(navController = navController, model = model)
        }

        composable("OlvidoContrasena"){
            OlvidoContrasena(navController = navController)
        }
        composable("Verificacion"){
            Verificacion(navController = navController)
        }

        // Perfiles
        composable("PerfilScreen") {
            PerfilScreen(navController = navController, user = model.loggedUser)
        }
        composable("EditProfileScreen") {
            EditProfileScreen(navController = navController, user = model.loggedUser, navModel = model)
        }

        //Tipos de formulario
        composable("FormularioGeneral") {
            FormularioGeneral(navController = navController, user = model.loggedUser)
        }
        composable("TiposForms") {
            FormularioSeleccion(navController = navController)
        }
        composable("FormularioEspecies"){
            FormularioEspecies(navController = navController)
        }
        composable("FormularioFaunaPuntoConteo"){
            FormularioFaunaPuntoConteo(navController = navController)
        }
        composable("FormularioFaunaBusquedaLibre"){
            FormularioFaunaBusquedaLibre(navController = navController)
        }
        composable("FormularioCuadrante"){
            FormularioCuadrante(navController = navController)
        }
        composable("FormularioSeguimiento"){
            FormularioSeguimiento(navController = navController)
        }
        composable("CamarasTrampa") {
            FormularioCamaraTrampa(activity,navController = navController)
        }
        composable("FormularioVariablesClimaticas") {
            FormularioVariablesClimaticas(navController = navController)
        }
    }
}

// SECCION DE NAVEGACION //
@Composable
fun La_navegacion(
    navController: NavHostController = rememberNavController(),
    firstSelected: Boolean = false,
    secondSelected: Boolean = false,
    thirdSelected: Boolean = false
) {
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
            BottomNavItem(Icons.Default.Home, "Inicio", firstSelected, navigation = navController,destino = "Principal")
            BottomNavItem(Icons.Default.Search, "Búsqueda", secondSelected, navigation = navController,destino = "Koadex")
            BottomNavItem(Icons.Default.Settings, "Ajustes", thirdSelected, navigation = navController,destino = "Configuracion")
        }
    }
}
@Composable
fun BottomNavItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    destino: String = "",
    navigation: NavHostController
) {

    val verde_1 = colorResource(R.color.verde_1)

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
                tint = if (selected) verde_1 else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = label,
            color = if (selected) verde_1 else Color.Gray,
            fontSize = 12.sp
        )
    }
}

fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
    val date = dateFormat.format(Date())
    return date
}


// Usuarios predeterminados

///////////////////////////
val sampleUser = UserEntity(
    name = "Test",
    email = "example@gmail.com",
    phone = "+00 012 345 6789",
    password = "clave123",
    startDate = getCurrentDate(),
    uploadedForms = 0,
    locallyStoredForms = 0,
    posts = 0,
    following = 0,
    followers = 0,
    isloggedIn = false,
    idZone = 0,
    profilePicture = R.drawable.profilepicture // Recurso de imagen predeterminado
)
///////////////////