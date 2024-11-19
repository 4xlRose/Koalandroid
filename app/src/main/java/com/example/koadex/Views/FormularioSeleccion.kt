package com.example.koadex.Views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.koadex.AppViewModelProvider
import com.example.koadex.MainActivity
import com.example.koadex.R
import com.example.koadex.ui.principal.KoadexViewModel

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioSeleccion(
    activity: MainActivity,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: KoadexViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.formulario)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.atras)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFB4D68F)
                )
            )
        }
    ) { paddingValues ->
        FormularioSeleccionScreen(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun FormularioSeleccionScreen(
    navController: androidx.navigation.NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .padding(top = 100.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp) // Espacio entre items
    ) {
        Text(
            text = "Tipo de Registro",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black
        )
        // Container for buttons with max width of 800dp
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .width(800.dp)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Primera fila
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OptionButtonTwoIcons(
                    icon1 = R.drawable.fauna,
                    icon2 = R.drawable.ruta,
                    text = "Fauna en Transectos",
                    onClick = { navController.navigate("FormularioEspecies") },
                    modifier = Modifier.weight(1f)
                )
                OptionButtonTwoIcons(
                    icon1 = R.drawable.fauna,
                    icon2 = R.drawable.aritmetica,
                    text = "Fauna en Punto de Conteo",
                    onClick = { navController.navigate("FormularioFaunaPuntoConteo") },
                    modifier = Modifier.weight(1f)
                )
            }

            // Segunda fila
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OptionButtonTwoIcons(
                    icon1 = R.drawable.fauna,
                    icon2 = R.drawable.search,
                    text = "Fauna Búsqueda Libre",
                    onClick = { navController.navigate("FormularioFaunaBusquedaLibre") },
                    modifier = Modifier.weight(1f)
                )
                OptionButton(
                    icon = R.drawable.cobertura,
                    text = "Validación de Cobertura",
                    onClick = { navController.navigate("FormularioSeguimiento") },
                    modifier = Modifier.weight(1f)
                )
            }

            // Tercera fila
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OptionButton(
                    icon = R.drawable.vegetacion,
                    text = "Parcela de Vegetación",
                    onClick = { navController.navigate("FormularioCuadrante") },
                    modifier = Modifier.weight(1f)
                )
                OptionButton(
                    icon = R.drawable.camara,
                    text = "Cámaras Trampa",
                    onClick = { navController.navigate("CamarasTrampa") },
                    modifier = Modifier.weight(1f)
                )
            }
            OptionButton(
                icon = R.drawable.temporada,
                text = "Variables Climáticas",
                onClick = { navController.navigate("FormularioVariablesClimaticas") },
                modifier = Modifier.fillMaxWidth()

            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { navController.navigate("siguiente") },
            modifier = Modifier
                .width(800.dp)
                .height(70.dp)
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4E7029)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                "SIGUIENTE",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun OptionButton(
    icon: Int,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp), // Increased height
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = text,
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun OptionButtonTwoIcons(
    icon1: Int,
    icon2: Int,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Contenedor para los dos iconos
            Box(
                modifier = Modifier
                    .width(100.dp) // Espacio entre los iconos
                    .height(64.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                // Primer icono
                Image(
                    painter = painterResource(id = icon1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterStart)
                )
                // Segundo icono
                Image(
                    painter = painterResource(id = icon2),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterEnd)
                )
            }
            Text(
                text = text,
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}