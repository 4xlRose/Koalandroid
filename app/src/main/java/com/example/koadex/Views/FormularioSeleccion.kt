package com.example.koadex.Views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.R
import com.example.koadex.ui.principal.KoadexViewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioSeleccion(
    //activity: MainActivity,
    navController: NavHostController,
    //modifier: Modifier = Modifier,
    //viewModel: KoadexViewModel = viewModel(factory = AppViewModelProvider.Factory)

)
{
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = {
            TopAppBar(
                title = { Text("Formulario",
                    color = Color.White,
                    fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Principal") }) {
                        Icon(Icons.Filled.ArrowBack,
                            contentDescription = "Atrás",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4E7029))
            )
        }
    ) { paddingValues ->
        FormularioSeleccionScreen(
            navController = navController,
            modifier = Modifier
                .padding(paddingValues)
                .background(Color.White)
        )
    }
}

@Composable
fun FormularioSeleccionScreen(
    navController: androidx.navigation.NavController,
    modifier: Modifier = Modifier
) {
    var selectedButton by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tipo de Registro",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OptionButton(
                icon = R.drawable.fauna,
                text = "Fauna en Transectos",
                isSelected = selectedButton == "Fauna en Transectos",
                onClick = {
                    selectedButton = "Fauna en Transectos" // Cambiar el botón seleccionado
                    navController.navigate("FormularioEspecies")
                }
            )

            OptionButton(
                icon = R.drawable.aritmetica,
                text = "Fauna en Punto de Conteo",
                isSelected = selectedButton == "Fauna en Punto de Conteo",
                onClick = {
                    selectedButton = "Fauna en Punto de Conteo"
                    navController.navigate("FormularioFaunaPuntoConteo")
                }
            )

            OptionButton(
                icon = R.drawable.search,
                text = "Fauna Búsqueda Libre",
                isSelected = selectedButton == "Fauna Búsqueda Libre",
                onClick = {
                    selectedButton = "Fauna Búsqueda Libre"
                    navController.navigate("FormularioFaunaBusquedaLibre")
                }
            )

            OptionButton(
                icon = R.drawable.cobertura,
                text = "Validación de Cobertura",
                isSelected = selectedButton == "Validación de Cobertura",
                onClick = {
                    selectedButton = "Validación de Cobertura"
                    navController.navigate("FormularioSeguimiento")
                }
            )

            OptionButton(
                icon = R.drawable.vegetacion,
                text = "Parcela de Vegetación",
                isSelected = selectedButton == "Parcela de Vegetación",
                onClick = {
                    selectedButton = "Parcela de Vegetación"
                    navController.navigate("FormularioCuadrante")
                }
            )

            OptionButton(
                icon = R.drawable.camara,
                text = "Cámaras Trampa",
                isSelected = selectedButton == "Cámaras Trampa",
                onClick = {
                    selectedButton = "Cámaras Trampa"
                    navController.navigate("CamarasTrampa")
                }
            )

            OptionButton(
                icon = R.drawable.temporada,
                text = "Variables Climáticas",
                isSelected = selectedButton == "Variables Climáticas",
                onClick = {
                    selectedButton = "Variables Climáticas"
                    navController.navigate("FormularioVariablesClimaticas")
                }
            )
        }

        Spacer(modifier = Modifier.height(60.dp))
    }
}

@Composable
fun OptionButton(
    icon: Int,
    text: String,
    isSelected: Boolean, // Estado de selección
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .height(90.dp)
            .fillMaxWidth()
            .background(if (isSelected) Color(0xFF97B96E) else Color.White), // Fondo dinámico
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(22.dp))
            Text(
                text = text,
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun OptionButtonTwoIcons( //Botones con 2 iconos
    icon1: Int,
    icon2: Int,
    text: String,
    onClick: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .height(160.dp)
            .padding(vertical = 16.dp)
            .background(if (isSelected) Color(0xFF97B96E) else Color.Transparent)
        ,
        shape = RoundedCornerShape(8.dp),

        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 1.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(88.dp)
                    .height(70.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Image(
                    painter = painterResource(id = icon1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(44.dp)
                        .align(Alignment.CenterStart)
                )
                Image(
                    painter = painterResource(id = icon2),
                    contentDescription = null,
                    modifier = Modifier
                        .size(44.dp)
                        .align(Alignment.CenterEnd)
                )
            }
            Text(
                text = text,
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(device = "spec:width=800px,height=1340px,dpi=300")
@Composable
fun PreviewFormularioSeleccion(){
    FormularioSeleccion(
        navController = rememberNavController())

}
