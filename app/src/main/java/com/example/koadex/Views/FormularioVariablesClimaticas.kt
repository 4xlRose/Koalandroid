package com.example.koadex.Views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.draw.clip


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
    val scrollState = rememberScrollState()
    val isFileLoaded = remember { mutableStateOf(false) }
    val (currentZone, setCurrentZone) = remember { mutableStateOf("") } // Estado para la zona seleccionada

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
            onFileLoaded = { isFileLoaded.value = it },
            modifier = Modifier.fillMaxWidth()
        )

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
                    .width(140.dp),
                onClick = {
                     },
            ) {
                Text("Atrás", fontWeight = FontWeight.Bold)
            }
            Button(
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4E7029)
                ),
                modifier = Modifier
                    .width(140.dp),
                onClick = {
                    },
                enabled = isFileLoaded.value
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
    val isSelected = currentZone == type // Determina si el botón está seleccionado

    Box(
        modifier = Modifier
            .size(buttonSize)
            .padding(4.dp)
            .clip(RoundedCornerShape(12.dp)) // Aplica las esquinas redondeadas al fondo
            .background(
                color = if (isSelected) Color(0xFF4E7029) else Color.White // Fondo dinámico
            )
            .border(
                width = 2.dp,
                color = Color(0xFF4E7029) , // Verde si está seleccionado, más suave si no
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
    enabled: Boolean = true,
    onFileLoaded: (Boolean) -> Unit
) {
    val (pluviosidad, setPluviosidad) = remember { mutableStateOf("") }
    val (temperaturaMaxima, setTemperaturaMaxima) = remember { mutableStateOf("") }
    val (temperaturaMinima, setTemperaturaMinima) = remember { mutableStateOf("") }
    val (humedadMaxima, setHumedadMaxima) = remember { mutableStateOf("") }
    val (humedadMinima, setHumedadMinima) = remember { mutableStateOf("") }
    val (nivelQuebrada, setNivelQuebrada) = remember { mutableStateOf("") }
    val (observaciones, setObservaciones) = remember { mutableStateOf("") }
    var isFileSelected by remember { mutableStateOf(false) }
    val actionButtonColors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029))

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

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                // Simular carga de archivo
                isFileSelected = true
                onFileLoaded(isFileSelected)
            }, colors = actionButtonColors) {
                Text("Elige archivo")
            }
            Button(onClick = {
                // Simular tomar foto
                isFileSelected = true
                onFileLoaded(isFileSelected)
            }, colors = actionButtonColors) {
                Text("Tomar foto")
            }
        }

        OutlinedTextField(
            value = observaciones,
            label = { Text("Observaciones") },
            onValueChange = setObservaciones,
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

