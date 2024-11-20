package com.example.koadex.Views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.AppViewModelProvider
import com.example.koadex.MainActivity
import com.example.koadex.R
import com.example.koadex.ui.principal.KoadexViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioVariablesClimaticas(
    //activity: MainActivity,
    navController: NavHostController,
    //modifier: Modifier = Modifier,
    //viewModel: KoadexViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.formulario)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("TiposForms") }) {
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
        FormularioVariablesClimaticasScreen(
            //activity = activity,
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun FormularioVariablesClimaticasScreen(
    //activity: MainActivity,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    FormularioClimaEntry(
        navController = navController,
        onSaveClick = {
            coroutineScope.launch {
            }
        },
        modifier = modifier)
}

@Composable
fun FormularioClimaEntry(
    navController: NavHostController,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp), // Espaciado general
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espaciado entre elementos
    ) {
        Text(
            text = "Zona",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        // Botones de zona
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            val buttonSize = 80.dp
            val zona = "zona"
            ZoneButton(
                type = "bosque",
                currentZone = zona,
                onZoneChange = {},
                buttonSize = buttonSize
            )
            ZoneButton(
                type = "arreglo",
                currentZone = zona,
                onZoneChange = {},
                buttonSize = buttonSize
            )
            ZoneButton(
                type = "transitorio",
                currentZone = zona,
                onZoneChange = {},
                buttonSize = buttonSize
            )
            ZoneButton(
                type = "permanente",
                currentZone = zona,
                onZoneChange = {},
                buttonSize = buttonSize
            )
        }

        // Campos de entrada
        ClimaInputForm()

        // Botón de envío
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4E7029)
                ),
                modifier = Modifier
                    .width(300.dp),
                onClick = {
                    onSaveClick()
                    navController.navigate("Principal")
                },
            ) {
                Text("Enviar", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ZoneButton(
    type: String,
    currentZone: String,
    onZoneChange: (String) -> Unit,
    buttonSize: Dp
) {
    OutlinedIconToggleButton(
        checked = currentZone == type,
        onCheckedChange = { onZoneChange(type) },
        shape = RoundedCornerShape(12.dp),
        colors = IconButtonDefaults.iconToggleButtonColors(
            containerColor = if (currentZone == type) Color(0xFFCDE4B4) else Color.White
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (currentZone == type) Color(0xFF4E7029) else Color.Black
        ),
        modifier = Modifier.size(buttonSize)
    ) {
        Image(
            painter = painterResource(
                when (type) {
                    "bosque" -> R.drawable.bosque
                    "arreglo" -> R.drawable.arreglo_agroforestal
                    "transitorios" -> R.drawable.cultivos_transitorios
                    else -> R.drawable.cultivos_permanentes
                }
            ),

            contentDescription = null
        )
    }
}

@Composable
fun ClimaInputForm(
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp) // Espaciado entre campos
    ) {
        OutlinedTextField(
            value = "pluviosidad",
            label = { Text("Pluviosidad (mm)") },
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = "maxtemp",
            label = { Text("Temperatura máxima") },
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = "mintemp",
            label = { Text("Temperatura mínima") },
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = "maxhum",
            label = { Text("Humedad máxima") },
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = "minhum",
            label = { Text("Humedad mínima") },
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = "quebrada",
            label = { Text("Nivel Quebrada (mt)") },
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = "observaciones",
            label = { Text("Observaciones") },
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun VariableClimaticasPreview(){
    FormularioVariablesClimaticas(navController = rememberNavController())
}
