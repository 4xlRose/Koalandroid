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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.auth0.android.Auth0
import com.example.koadex.MainActivity
import com.example.koadex.R

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
import com.example.koadex.clases.User
import com.example.koadex.Views.PerfilScreen

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
    NavHost(navController = navController, startDestination = "Principal") {
        composable("InicioCarga") {
            InicioCarga(navController = navController)
        }
        composable("InicioSesion") {
            InicioSesion(navController = navController,account)
        }

        composable("Koadex") {
            Koadex(navController = navController)
        }
        composable("Principal") {
            Principal(navController = navController)
        }
        composable("Registro") {
            Registro(navController = navController)
        }
        /*composable("SeleccionForm") {
            SeleccionForm(navController = navController)
        }*/
        composable("Configuracion"){
            Configuracion(navController = navController)
        }

        composable("OlvidoContrasena"){
            OlvidoContrasena(navController = navController)
        }
        composable("Verificacion"){
            Verificacion(navController = navController)
        }

        // Perfiles
        composable("PerfilScreen") {
            PerfilScreen(navController = navController, user = sampleUser)
        }
        composable("EditProfileScreen") {
            EditProfileScreen(navController = navController, user = sampleUser)
        }

        //Tipos de formulario
        composable("FormularioGeneral") {
            FormularioGeneral(navController = navController)
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
///////////////////////////
val sampleUser = User(
    id = 1,
    username = "Koalandroidcito",
    email = "koalandroid@tec.mx",
    password = "KoalAndroid*2025",
    totalForms = 10,
    uploadedForms = 7,
    locallyStoredForms = 3,
    posts = 15,
    following = 200,
    followers = 150,
    isloggedIn = true,
    profilePicture = R.drawable.profilepicture // Recurso de imagen predeterminado
)
///////////////////