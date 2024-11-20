package com.example.koadex.Views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.R
import com.example.koadex.ViewModels.Principal_ViewModel
import com.example.koadex.clases.User
import com.example.koadex.navigate.La_navegacion

@Composable
fun Principal(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    user: User = User("Samantha", 5, 3, 2)
) {
    var `intro-base` = stringResource(id = R.string.Intro_homepage)
    var `formulario-base` = stringResource(id = R.string.Formularios_base)

    var principal_vm = Principal_ViewModel()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 16.dp)
    ) {

        // logo y boton para perfil
        Spacer(modifier = Modifier.height(48.dp))
        principal_vm.Logo_perfil(navController)

        // Contenido principal
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 
            principal_vm.Bienvenida_Agregar_formulario(`intro-base`, user, navController)

            // Dashboard title
            Text(
                text = stringResource(R.string.Dashboard_hompage),
                modifier = Modifier.padding(start = 16.dp, top = 32.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            // Warning message if needed
            if (user.locallyStoredForms > 0) {
                principal_vm.Advertencia(user)
            }else{
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Formularios en total
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                principal_vm.Contador_formularios(user, `formulario-base`)
            }

            // Bottom navigation
            La_navegacion(navController, true, false, false)
        }
    }
}

////////////////////////////////////////

@Preview(showBackground = true)
@Composable
fun PreviewPrincipal() {
    Principal(
        navController = rememberNavController(),
        user = User("Samantha", 5, 4, 6)
    )
}