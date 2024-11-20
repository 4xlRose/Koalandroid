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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.AppViewModelProvider
import com.example.koadex.MainActivity
import com.example.koadex.R
import com.example.koadex.ui.principal.KoadexViewModel

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioCuadrante(
    activity: MainActivity,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: KoadexViewModel = viewModel(factory = AppViewModelProvider.Factory)
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
        FormularioCuadranteScreen(
            activity = activity,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun FormularioCuadranteScreen(
    activity: MainActivity,
    modifier: Modifier = Modifier
) {
    var codigo by remember { mutableStateOf("") }
    var nombreComun by remember { mutableStateOf("") }
    var nombreCientifico by remember { mutableStateOf("") }
    var placa by remember { mutableStateOf("") }
    var circunferencia by remember { mutableStateOf("") }
    var distancia by remember { mutableStateOf("") }
    var estaturaBiometrica by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var observaciones by remember { mutableStateOf("") }

    // Estados para las selecciones
    var cuadranteSeleccionado by remember { mutableStateOf("") }
    var subCuadranteSeleccionado by remember { mutableStateOf("") }
    var habitoCrecimientoSeleccionado by remember { mutableStateOf("") }

    val actionButtonColors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029))
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
    ) {

        // Contenido desplazable
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Código
            OutlinedTextField(
                value = codigo,
                onValueChange = { codigo = it },
                label = { Text("Código") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Cuadrante
            Text("Cuadrante", style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf("A", "B", "C", "D", "E", "F", "G").forEach { letra ->
                    OutlinedButton(
                        onClick = { cuadranteSeleccionado = letra },
                        modifier = Modifier.size(40.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (cuadranteSeleccionado == letra) Color(0xFFB4D68F) else Color.White
                        )
                    ) {
                        Text(letra)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sub-Cuadrante
            Text("Sub-Cuadrante", style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                (1..4).forEach { numero ->
                    OutlinedButton(
                        onClick = { subCuadranteSeleccionado = numero.toString() },
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(40.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (subCuadranteSeleccionado == numero.toString()) Color(0xFFB4D68F) else Color.White
                        )
                    ) {
                        Text(numero.toString())
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Hábito de crecimiento
            Text("Hábito de crecimiento", style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                HabitoCrecimientoBoton(
                    icon = R.drawable.arbusto,
                    text = "Arbusto",
                    altura = "< 1mt",
                    isSelected = habitoCrecimientoSeleccionado == "Arbusto",
                    onClick = { habitoCrecimientoSeleccionado = "Arbusto" }
                )

                HabitoCrecimientoBoton(
                    icon = R.drawable.arbolito,
                    text = "Arbolito",
                    altura = "1-3 mt",
                    isSelected = habitoCrecimientoSeleccionado == "Arbolito",
                    onClick = { habitoCrecimientoSeleccionado = "Arbolito" }
                )

                HabitoCrecimientoBoton(
                    icon = R.drawable.arbol,
                    text = "Árbol",
                    altura = "> 3mt",
                    isSelected = habitoCrecimientoSeleccionado == "Árbol",
                    onClick = { habitoCrecimientoSeleccionado = "Árbol" }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de texto
            OutlinedTextField(
                value = nombreComun,
                onValueChange = { nombreComun = it },
                label = { Text("Nombre Común Especie") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = nombreCientifico,
                onValueChange = { nombreCientifico = it },
                label = { Text("Nombre Científico") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = placa,
                onValueChange = { placa = it },
                label = { Text("Placa") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = circunferencia,
                onValueChange = { circunferencia = it },
                label = { Text("Circunferencia en cm (CL)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = distancia,
                onValueChange = { distancia = it },
                label = { Text("Distancia en mt") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = estaturaBiometrica,
                onValueChange = { estaturaBiometrica = it },
                label = { Text("Estatura Biométrica en mt") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = altura,
                onValueChange = { altura = it },
                label = { Text("Altura en mt") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Evidencias
            Text("Evidencias", style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { /* Elegir archivo */ },
                    colors = actionButtonColors
                ) {
                    Text("Elige archivo")
                }
                Button(
                    onClick = { /* Tomar foto */ },
                    colors = actionButtonColors
                ) {
                    Text("Tomar foto")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Observaciones
            OutlinedTextField(
                value = observaciones,
                onValueChange = { observaciones = it },
                label = { Text("Observaciones") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botones de acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { /* Volver atrás */ },
                    colors = actionButtonColors,
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                ) {
                    Text("ATRAS", color = Color.White)
                }
                Button(
                    onClick = { /* Enviar formulario */ },
                    colors = actionButtonColors,
                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                ) {
                    Text("ENVIAR", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun HabitoCrecimientoBoton(
    icon: Int,
    text: String,
    altura: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .border(
                width = 1.dp,
                color = if (isSelected) Color(0xFF4E7029) else Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = text,
            modifier = Modifier.size(48.dp)
        )
        Text(text, style = MaterialTheme.typography.bodySmall)
        Text(altura, style = MaterialTheme.typography.bodySmall)
    }
}

