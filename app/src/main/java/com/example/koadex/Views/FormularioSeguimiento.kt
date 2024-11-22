package com.example.koadex.Views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
fun FormularioSeguimiento(
    //activity: MainActivity,
    navController: NavHostController,
    //modifier: Modifier = Modifier,
    //viewModel: KoadexViewModel = viewModel(factory = AppViewModelProvider.Factory)
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
        FormularioSeguimientoScreen(
            //activity = activity,
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun FormularioSeguimientoScreen(
    //activity: MainActivity,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    // Estado para los botones seleccionados
    val coberturaSeleccionada = remember { mutableStateOf(-1) }
    val disturbioSeleccionado = remember { mutableStateOf(-1) }
    var isFileSelected by remember { mutableStateOf(false) }
    val seguimientoSeleccionado = remember { mutableStateOf(-1) } // Estado para los botones de Seguimiento
    val cambioSeleccionado = remember { mutableStateOf(-1) } // Estado para los botones de Cambió

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        // Campo de Código
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Código") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sección de Seguimiento y Cambio (más centrada)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Seguimiento", style = MaterialTheme.typography.titleMedium)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(
                        onClick = { seguimientoSeleccionado.value = 0 }, // Marca el botón como seleccionado
                        border = BorderStroke(1.dp, Color.Gray),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (seguimientoSeleccionado.value == 0) Color(0xFF4E7029) else Color.White,
                            contentColor = if (seguimientoSeleccionado.value == 0) Color.White else Color.Black
                        )
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Check")
                    }
                    OutlinedButton(
                        onClick = { seguimientoSeleccionado.value = 1 }, // Marca el botón como seleccionado
                        border = BorderStroke(1.dp, Color.Gray),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (seguimientoSeleccionado.value == 1) Color(0xFF4E7029) else Color.White,
                            contentColor = if (seguimientoSeleccionado.value == 1) Color.White else Color.Black
                        )
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Cambió", style = MaterialTheme.typography.titleMedium)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(
                        onClick = { cambioSeleccionado.value = 0 }, // Marca el botón como seleccionado
                        border = BorderStroke(1.dp, Color.Gray),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (cambioSeleccionado.value == 0) Color(0xFF4E7029) else Color.White,
                            contentColor = if (cambioSeleccionado.value == 0) Color.White else Color.Black
                        )
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Check")
                    }
                    OutlinedButton(
                        onClick = { cambioSeleccionado.value = 1 }, // Marca el botón como seleccionado
                        border = BorderStroke(1.dp, Color.Gray),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (cambioSeleccionado.value == 1) Color(0xFF4E7029) else Color.White,
                            contentColor = if (cambioSeleccionado.value == 1) Color.White else Color.Black
                        )
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sección de Cobertura
        Text("Cobertura", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(10.dp))

        Column {
            // Primera fila de botones de Cobertura
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Botones(
                    icon = R.drawable.bd_cobertura,
                    text = "",
                    isSelected = coberturaSeleccionada.value == 0,
                    onClick = { coberturaSeleccionada.value = 0 }
                )
                Botones(
                    icon = R.drawable.ra_cobertura,
                    text = "",
                    isSelected = coberturaSeleccionada.value == 1,
                    onClick = { coberturaSeleccionada.value = 1 }
                )
                Botones(
                    icon = R.drawable.rb_cobertura,
                    text = "",
                    isSelected = coberturaSeleccionada.value == 2,
                    onClick = { coberturaSeleccionada.value = 2 }
                )
                Botones(
                    icon = R.drawable.pa_cobertura,
                    text = "",
                    isSelected = coberturaSeleccionada.value == 3,
                    onClick = { coberturaSeleccionada.value = 3 }
                )
                Botones(
                    icon = R.drawable.pl_cobertura,
                    text = "",
                    isSelected = coberturaSeleccionada.value == 4,
                    onClick = { coberturaSeleccionada.value = 4 }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Segunda fila de botones de Cobertura
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Botones(
                    icon = R.drawable.cp_cobertura,
                    text = "",
                    isSelected = coberturaSeleccionada.value == 5,
                    onClick = { coberturaSeleccionada.value = 5 }
                )
                Botones(
                    icon = R.drawable.ct_cobertura,
                    text = "",
                    isSelected = coberturaSeleccionada.value == 6,
                    onClick = { coberturaSeleccionada.value = 6 }
                )
                Botones(
                    icon = R.drawable.vh_cobertura,
                    text = "",
                    isSelected = coberturaSeleccionada.value == 7,
                    onClick = { coberturaSeleccionada.value = 7 }
                )
                Botones(
                    icon = R.drawable.td_cobertura,
                    text = "",
                    isSelected = coberturaSeleccionada.value == 8,
                    onClick = { coberturaSeleccionada.value = 8 }
                )
                Botones(
                    icon = R.drawable.if_cobertura,
                    text = "",
                    isSelected = coberturaSeleccionada.value == 9,
                    onClick = { coberturaSeleccionada.value = 9 }
                )
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Campo de Tipos de Cultivo
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Tipos de Cultivo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Seccion de Disturbio
        Text("Disturbio", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(10.dp))

        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Botones(
                    icon = R.drawable.inundacion_1,
                    text = "Inundación",
                    isSelected = disturbioSeleccionado.value == 0,
                    onClick = { disturbioSeleccionado.value = 0 }
                )
                Botones(
                    icon = R.drawable.hoguera_1,
                    text = "Quema",
                    isSelected = disturbioSeleccionado.value == 1,
                    onClick = { disturbioSeleccionado.value = 1 }
                )
                Botones(
                    icon = R.drawable.tala_de_arboles_1,
                    text = "Tala",
                    isSelected = disturbioSeleccionado.value == 2,
                    onClick = { disturbioSeleccionado.value = 2 }
                )
                Botones(
                    icon = R.drawable.polvo_1,
                    text = "Erosión",
                    isSelected = disturbioSeleccionado.value == 3,
                    onClick = { disturbioSeleccionado.value = 3 }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Botones(
                    icon = R.drawable.mineria_1,
                    text = "Minería",
                    isSelected = disturbioSeleccionado.value == 4,
                    onClick = { disturbioSeleccionado.value = 4 }
                )
                Botones(
                    icon = R.drawable.camino_1,
                    text = "Carretera",
                    isSelected = disturbioSeleccionado.value == 5,
                    onClick = { disturbioSeleccionado.value = 5 }
                )
                Botones(
                    icon = R.drawable.algas_marinas_1,
                    text = "Plantas acuáticas",
                    isSelected = disturbioSeleccionado.value == 6,
                    onClick = { disturbioSeleccionado.value = 6 }
                )
                Botones(
                    icon = R.drawable.otrodisturbio,
                    text = "",
                    isSelected = disturbioSeleccionado.value == 7,
                    onClick = { disturbioSeleccionado.value = 7 }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sección de Evidencias
        Text("Evidencias", style = MaterialTheme.typography.titleMedium)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029)),
                modifier = Modifier.weight(1f)
            ) {
                Text("Elegir archivo")
            }
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029)),
                modifier = Modifier.weight(1f)
            ) {
                Text("Tomar foto")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de Observaciones
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Observaciones") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botones Atrás y Enviar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { navController.navigateUp() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029)),
                modifier = Modifier.weight(1f)
            ) {
                Text("ATRÁS")
            }
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029)),
                modifier = Modifier.weight(1f)
            ) {
                Text("ENVIAR")
            }
        }
    }
}

@Composable
fun CoberturaButton(
    index: Int,
    icon: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val buttonColor = if (isSelected) Color(0xFF4E7029) else Color.White
    val textColor = if (isSelected) Color.White else Color.Black

    Button(
        onClick = onClick,
        modifier = Modifier
            .size(80.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = textColor
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Cobertura $index",
                modifier = Modifier.size(60.dp)
            )
        }
    }
}

@Composable
fun Botones(
    icon: Int,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val buttonColor = if (isSelected) Color(0xFF4E7029) else Color.White
    val textColor = if (isSelected) Color.White else Color.Black

    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .height(120.dp)
            .aspectRatio(1f),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Gray),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = buttonColor,
            contentColor = textColor
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = text,
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(device = "spec:width=800px,height=1340px,dpi=300")
@Composable
fun PreviewFormularioSeguimiento(){
    FormularioSeguimiento(
        //activity = MainActivity(),
    navController = rememberNavController()
    )

}