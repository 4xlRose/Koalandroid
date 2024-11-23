package com.example.koadex.Views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.R
import kotlinx.coroutines.launch
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import com.example.koadex.ViewModels.FomularioEspecies_ViewModel


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
                title = { Text(text = stringResource(id = R.string.formulario),
                    color = Color.White,
                    fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("TiposForms") }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.atras),
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4E7029)
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
    val scrollState = rememberScrollState()
    val (currentZone, setCurrentZone) = remember { mutableStateOf("") } // Estado para la zona seleccionada
    val viewModel = FomularioEspecies_ViewModel()
    val green700 = colorResource(id = R.color.green_700)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState), // Habilitar scroll
        verticalArrangement = Arrangement.spacedBy(16.dp)
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
            ZoneButton(
                type = "bosque",
                currentZone = currentZone,
                onZoneChange = setCurrentZone,
                buttonSize = buttonSize
            )
            ZoneButton(
                type = "arreglo",
                currentZone = currentZone,
                onZoneChange = setCurrentZone,
                buttonSize = buttonSize
            )
            ZoneButton(
                type = "transitorio",
                currentZone = currentZone,
                onZoneChange = setCurrentZone,
                buttonSize = buttonSize
            )
            ZoneButton(
                type = "permanente",
                currentZone = currentZone,
                onZoneChange = setCurrentZone,
                buttonSize = buttonSize
            )
        }

        ClimaInputForm(
            modifier = Modifier.fillMaxWidth()
        )

        viewModel.Atras_enviar(navController, green700)

        Spacer(modifier = Modifier.height(50.dp))

    }
}

@Composable
fun ZoneButton(
    type: String,
    currentZone: String,
    onZoneChange: (String) -> Unit,
    buttonSize: Dp
) {
    val isSelected = currentZone == type // Determina si el botón está seleccionado

    Box(
        modifier = Modifier
            .size(buttonSize)
            .padding(3.dp)
            .clip(RoundedCornerShape(12.dp)) // Aplica las esquinas redondeadas al fondo
            .background(
                color = if (isSelected) Color(0xFF97B96E) else Color.White // Fondo dinámico
            )
            .border(
                width = 2.dp,
                color = Color(0xFF97B96E) , // Verde si está seleccionado, más suave si no
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onZoneChange(type) },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(
                when (type) {
                    "bosque" -> R.drawable.bosque
                    "arreglo" -> R.drawable.arreglo_agroforestal
                    "transitorio" -> R.drawable.cultivos_transitorios
                    else -> R.drawable.cultivos_permanentes
                }
            ),
            contentDescription = null,
            modifier = Modifier.size(60.dp) // Aumenta el tamaño de la imagen
        )
    }
}


@Composable
fun ClimaInputForm(
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val (pluviosidad, setPluviosidad) = remember { mutableStateOf("") }
    val (temperaturaMaxima, setTemperaturaMaxima) = remember { mutableStateOf("") }
    val (temperaturaMinima, setTemperaturaMinima) = remember { mutableStateOf("") }
    val (humedadMaxima, setHumedadMaxima) = remember { mutableStateOf("") }
    val (humedadMinima, setHumedadMinima) = remember { mutableStateOf("") }
    val (nivelQuebrada, setNivelQuebrada) = remember { mutableStateOf("") }
    val (observaciones, setObservaciones) = remember { mutableStateOf("") }
    val actionButtonColors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029))
    val viewModel = FomularioEspecies_ViewModel()
    val green700 = colorResource(id = R.color.green_700)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = pluviosidad,
            label = { Text("Pluviosidad (mm)") },
            onValueChange = setPluviosidad,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = temperaturaMaxima,
            label = { Text("Temperatura máxima") },
            onValueChange = setTemperaturaMaxima,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = temperaturaMinima,
            label = { Text("Temperatura mínima") },
            onValueChange = setTemperaturaMinima,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = humedadMaxima,
            label = { Text("Humedad máxima") },
            onValueChange = setHumedadMaxima,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = humedadMinima,
            label = { Text("Humedad mínima") },
            onValueChange = setHumedadMinima,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = nivelQuebrada,
            label = { Text("Nivel Quebrada (mt)") },
            onValueChange = setNivelQuebrada,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        Text("Evidencias", style = MaterialTheme.typography.titleMedium)
        viewModel.Botones_captura(green700)

        // Observaciones
        OutlinedTextField(
            value = "", onValueChange = { /* Actualizar estado */ },
            label = { Text("Observaciones") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(device = "spec:width=800px,height=1340px,dpi=300")
@Composable
fun VariableClimaticasPreview(){
    FormularioVariablesClimaticas(navController = rememberNavController())
}
