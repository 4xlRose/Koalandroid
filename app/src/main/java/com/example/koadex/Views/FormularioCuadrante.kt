package com.example.koadex.views

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.FileOpen
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
import androidx.navigation.NavController
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
        FormularioCuadranteScreen(
            /*activity = activity,*/
            modifier = Modifier.padding(paddingValues)
        )
    }
}
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun FormularioCuadranteScreen(
    /*activity: MainActivity,*/
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

            Spacer(modifier = Modifier.height(90.dp))

            // Código
            OutlinedTextField(
                value = codigo,
                onValueChange = { codigo = it },
                label = { Text("Código") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Cuadrante
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Cuadrantes divididos en dos columnas
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Cuadrante", style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(8.dp))

                            BotonCuadranteAB(
                                text = "A",
                                isSelected = cuadranteSeleccionado == "A",
                                onClick = { cuadranteSeleccionado = "A" }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            BotonCuadranteAB(
                                text = "B",
                                isSelected = cuadranteSeleccionado == "B",
                                onClick = { cuadranteSeleccionado = "B" }
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(6.dp))

                            BotonCuadranteCG(
                                text = "C",
                                isSelected = cuadranteSeleccionado == "C",
                                onClick = { cuadranteSeleccionado = "C" }
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            BotonCuadranteCG(
                                text = "D",
                                isSelected = cuadranteSeleccionado == "D",
                                onClick = { cuadranteSeleccionado = "D" }
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            BotonCuadranteCG(
                                text = "E",
                                isSelected = cuadranteSeleccionado == "E",
                                onClick = { cuadranteSeleccionado = "E" }
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            BotonCuadranteCG(
                                text = "F",
                                isSelected = cuadranteSeleccionado == "F",
                                onClick = { cuadranteSeleccionado = "F" }
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            BotonCuadranteCG(
                                text = "G",
                                isSelected = cuadranteSeleccionado == "G",
                                onClick = { cuadranteSeleccionado = "G" }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Hábito de crecimiento
                Column(
                    modifier = Modifier.weight(1.1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Hábito de crecimiento", style = MaterialTheme.typography.titleMedium)
                    Row(
                        modifier = Modifier.fillMaxWidth()/*,
                        horizontalArrangement = Arrangement.SpaceEvenly*/
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))

                        CrecimientoBoton(
                            icon = R.drawable.arbusto,
                            text = "Arbusto",
                            altura = "< 1mt",
                            isSelected = habitoCrecimientoSeleccionado == "Arbusto",
                            onClick = { habitoCrecimientoSeleccionado = "Arbusto" }
                        )

                        CrecimientoBoton(
                            icon = R.drawable.arbolito,
                            text = "Arbolito",
                            altura = "1-3 mt",
                            isSelected = habitoCrecimientoSeleccionado == "Arbolito",
                            onClick = { habitoCrecimientoSeleccionado = "Arbolito" }
                        )

                        CrecimientoBoton(
                            icon = R.drawable.arbol,
                            text = "Árbol",
                            altura = "> 3mt",
                            isSelected = habitoCrecimientoSeleccionado == "Árbol",
                            onClick = { habitoCrecimientoSeleccionado = "Árbol" }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Sub-Cuadrante", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    BotonSubCuadrante(
                        text = "1",
                        isSelected = subCuadranteSeleccionado == "1",
                        onClick = { subCuadranteSeleccionado = "1" }
                    )
                    BotonSubCuadrante(
                        text = "2",
                        isSelected = subCuadranteSeleccionado == "2",
                        onClick = { subCuadranteSeleccionado = "2" }
                    )
                    BotonSubCuadrante(
                        text = "3",
                        isSelected = subCuadranteSeleccionado == "3",
                        onClick = { subCuadranteSeleccionado = "3" }
                    )
                    BotonSubCuadrante(
                        text = "4",
                        isSelected = subCuadranteSeleccionado == "4",
                        onClick = { subCuadranteSeleccionado = "4" }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de texto
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = nombreComun,
                    onValueChange = { nombreComun = it },
                    label = { Text("Nombre Común") }, //Especie
                    modifier = Modifier.weight(1f).padding(start = 2.dp)
                )

                OutlinedTextField(
                    value = nombreCientifico,
                    onValueChange = { nombreCientifico = it },
                    label = { Text("Nombre Científico") },
                    modifier = Modifier.weight(1f).padding(end = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = placa,
                onValueChange = { placa = it },
                label = { Text("Placa") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = circunferencia,
                    onValueChange = { circunferencia = it },
                    label = { Text("Circunferencia(CL)") }, //en cm
                    modifier = Modifier.weight(1f).padding(start = 2.dp)
                )

                OutlinedTextField(
                    value = distancia,
                    onValueChange = { distancia = it },
                    label = { Text("Distancia en mt") },
                    modifier = Modifier.weight(1f).padding(end = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = estaturaBiometrica,
                    onValueChange = { estaturaBiometrica = it },
                    label = { Text("Estatura Biométrica") }, // en mt
                    modifier = Modifier.weight(1f).padding(start = 2.dp)
                )
                OutlinedTextField(
                    value = altura,
                    onValueChange = { altura = it },
                    label = { Text("Altura en mt") },
                    modifier = Modifier.weight(1f).padding(end = 2.dp)
                )
            }

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
fun BotonCuadranteAB(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .height(60.dp)
            .width(80.dp)
            .padding(1.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, if (isSelected) Color(0xFF4E7029) else Color.Gray),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isSelected) Color(0xFF4E7029) else Color.White // Cambia el color aquí
        )
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Black,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun BotonCuadranteCG(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .height(30.dp)
            .width(30.dp),
        shape = RoundedCornerShape(1.dp),
        border = BorderStroke(0.7.dp, if (isSelected) Color(0xFF4E7029) else Color.Gray),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isSelected) Color(0xFFB4D68F) else Color.White
        )
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Black,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun BotonSubCuadrante(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .height(50.dp)
            .width(50.dp),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, if (isSelected) Color(0xFF4E7029) else Color.Gray),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isSelected) Color(0xFF4E7029) else Color.White // Cambia el color aquí
        )
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Black,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun CrecimientoBoton(
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
            .padding(8.dp)
            .background(
                color = if (isSelected) Color(0xFF4E7029) else Color.White, // Cambia el fondo
                shape = RoundedCornerShape(8.dp)
            ) // Cambia el fondo
            .border(
                width = 2.dp,
                color = if (isSelected) Color(0xFF4E7029) else Color.Gray, // Cambia el borde
                shape = RoundedCornerShape(8.dp)
            ) // Cambia el borde
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

@Composable
private fun Atras_enviar(
    navController: NavController,
    green700: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = green700)
        ) {
            Text("ATRAS")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = { /* Handle form submission */ },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = green700)
        ) {
            Text("ENVIAR")
        }
    }
}

//////////////// PARA LOS BOTONES DE CAPTURAR //////////////////
@Composable
private fun Botones_captura(green700: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Boton_seleccionar_archivo(green700)
        Boton_abrir_camara(green700)
    }
}

@Composable
private fun Boton_abrir_camara(green700: Color) {
    Button(
        onClick = { /* Handle photo capture */ },
        colors = ButtonDefaults.buttonColors(containerColor = green700)
    ) {
        Icon(Icons.Default.Camera, contentDescription = "Tomar foto")
        Spacer(modifier = Modifier.width(10.dp))
        Text("Tomar foto")
    }
}

@Composable
private fun Boton_seleccionar_archivo(green700: Color) {
    Button(
        onClick = { /* Handle file selection */ },
        colors = ButtonDefaults.buttonColors(containerColor = green700)
    ) {
        Icon(Icons.Default.FileOpen, contentDescription = "Seleccionar archivo")
        Spacer(modifier = Modifier.width(10.dp))
        Text("Elige archivo")
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(device = "spec:width=800px,height=1340px,dpi=300")
@Composable
fun FormularioCuadrantePreview() {
    FormularioCuadrante(navController = rememberNavController())
}