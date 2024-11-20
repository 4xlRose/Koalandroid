package com.example.koadex.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.R
import com.example.koadex.ViewModels.PrincipalViewModel
import com.example.koadex.clases.User
import com.example.koadex.navigate.sampleUser
@Composable
fun Principal(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    user: User = sampleUser
) {
    var `intro-base` = stringResource(id = R.string.Intro_homepage)
    var `formulario-base` = stringResource(id = R.string.Formularios_base)

    val principal_vm = PrincipalViewModel()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            principal_vm.Logo_perfil(navController)
        },
        bottomBar = {
            principal_vm.nav_Bar(navController)
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            principal_vm.Bienvenida_Agregar_formulario(`intro-base`, user, navController)

            Spacer(modifier = Modifier.height(40.dp))

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
            } else {
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPrincipal() {
    Principal(
        navController = rememberNavController(),
        user = sampleUser
    )
}