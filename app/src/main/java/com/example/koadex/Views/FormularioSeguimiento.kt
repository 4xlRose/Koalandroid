package com.example.koadex.Views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.AppViewModelProvider
import com.example.koadex.R
import com.example.koadex.ViewModels.FomularioEspecies_ViewModel
import com.example.koadex.ui.principal.KoadexViewModel

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioSeguimiento(
    //activity: MainActivity,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    //viewModel: KoadexViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.formulario), color = Color.White) },
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
        FormularioSeguimientoScreen(
            //activity = activity,
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}
//@Preview(device = "spec:width=800px,height=1340px,dpi=300", showBackground = true)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun FormularioSeguimientoScreen(
    //activity: MainActivity,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    // Estado para los botones seleccionados
    val coberturaSeleccionada = remember { mutableStateOf(-1) }
    val disturbioSeleccionado = remember { mutableStateOf(-1) }
    val seguimientoSeleccionado = remember { mutableStateOf(-1) } // Estado para los botones de Seguimiento
    val cambioSeleccionado = remember { mutableStateOf(-1) } // Estado para los botones de Cambió
    val viewModel = FomularioEspecies_ViewModel()
    val green700 = colorResource(id = R.color.green_700)
    var codigo by remember { mutableStateOf("") } // Estado para Observaciones
    var tipoCultivo by remember { mutableStateOf("") } // Estado para Observaciones
    var observaciones by remember { mutableStateOf("") } // Estado para Observaciones

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        // Campo de Código
        OutlinedTextField(
            value = codigo,
            onValueChange = { codigo = it},
            label = { Text("Código", color = Color.DarkGray) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // SECCION SEGUIMIENTO Y CAMBIO
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Seguimiento",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(
                        onClick = { seguimientoSeleccionado.value = 0 }, // Marca el botón como seleccionado
                        border = BorderStroke(1.dp, Color.Gray),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (seguimientoSeleccionado.value == 0) Color(0xFF97B96E) else Color.White,
                            contentColor = if (seguimientoSeleccionado.value == 0) Color.White else Color.Black
                        )
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Check")
                    }
                    OutlinedButton(
                        onClick = { seguimientoSeleccionado.value = 1 }, // Marca el botón como seleccionado
                        border = BorderStroke(1.dp, Color.Gray),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (seguimientoSeleccionado.value == 1) Color(0xFF97B96E) else Color.White,
                            contentColor = if (seguimientoSeleccionado.value == 1) Color.White else Color.Black
                        )
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Cambió",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black)
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

        // SECCION COBERTURA
        Text("Cobertura",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black)

        Spacer(modifier = Modifier.height(10.dp))

        val coberturaSeleccionada = remember { mutableIntStateOf(-1) } // Estado compartido

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(IntrinsicSize.Min)
            ) {
                // Primera fila de botones de Cobertura
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    (0..4).forEach { index -> // Iterar por los botones de la fila
                        CoberturaButton(
                            index = index + 1, // Índice visible comienza en 1
                            icon = when (index) {
                                0 -> R.drawable.bd_cobertura
                                1 -> R.drawable.ra_cobertura
                                2 -> R.drawable.rb_cobertura
                                3 -> R.drawable.pa_cobertura
                                else -> R.drawable.pl_cobertura
                            },
                            isSelected = coberturaSeleccionada.intValue == index,
                            onClick = { coberturaSeleccionada.intValue = index }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Segunda fila de botones de Cobertura
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    (5..9).forEach { index -> // Iterar por los botones de la fila
                        CoberturaButton(
                            index = index + 1,
                            icon = when (index) {
                                5 -> R.drawable.cp_cobertura
                                6 -> R.drawable.ct_cobertura
                                7 -> R.drawable.vh_cobertura
                                8 -> R.drawable.td_cobertura
                                else -> R.drawable.if_cobertura
                            },
                            isSelected = coberturaSeleccionada.intValue == index,
                            onClick = { coberturaSeleccionada.intValue = index }
                        )
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        // CAMPO TIPO DE CULTIVO
        OutlinedTextField(
            value = tipoCultivo,
            onValueChange = {tipoCultivo = it },
            label = { Text("Tipos de Cultivo", color = Color.DarkGray) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // SECCION DISTURBIO
        Text("Disturbio",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color.Black)

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(IntrinsicSize.Min)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    DisturbioButton(
                        icon = R.drawable.inundacion_1,
                        text = "Inundación",
                        isSelected = disturbioSeleccionado.value == 0,
                        onClick = { disturbioSeleccionado.value = 0 }
                    )
                    DisturbioButton(
                        icon = R.drawable.hoguera_1,
                        text = "Quema",
                        isSelected = disturbioSeleccionado.value == 1,
                        onClick = { disturbioSeleccionado.value = 1 }
                    )
                    DisturbioButton(
                        icon = R.drawable.tala_de_arboles_1,
                        text = "Tala",
                        isSelected = disturbioSeleccionado.value == 2,
                        onClick = { disturbioSeleccionado.value = 2 }
                    )
                    DisturbioButton(
                        icon = R.drawable.polvo_1,
                        text = "Erosión",
                        isSelected = disturbioSeleccionado.value == 3,
                        onClick = { disturbioSeleccionado.value = 3 }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    DisturbioButton(
                        icon = R.drawable.mineria_1,
                        text = "Minería",
                        isSelected = disturbioSeleccionado.value == 4,
                        onClick = { disturbioSeleccionado.value = 4 }
                    )
                    DisturbioButton(
                        icon = R.drawable.camino_1,
                        text = "Carretera",
                        isSelected = disturbioSeleccionado.value == 5,
                        onClick = { disturbioSeleccionado.value = 5 }
                    )
                    DisturbioButton(
                        icon = R.drawable.algas_marinas_1,
                        text = "Plantas \nacuáticas",
                        isSelected = disturbioSeleccionado.value == 6,
                        onClick = { disturbioSeleccionado.value = 6 }
                    )
                    DisturbioButton(
                        text = "Otro",
                        isSelected = disturbioSeleccionado.value == 7,
                        onClick = { disturbioSeleccionado.value = 7 }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sección de Evidencias
        Text("Evidencias",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Start),
            color = Color.Black)
        viewModel.Botones_captura(green700)


        Spacer(modifier = Modifier.height(16.dp))

        // Observaciones
        OutlinedTextField(
            value = observaciones,
            onValueChange = { observaciones = it }, // Actualizar el estado
            label = { Text("Observaciones", color = Color.DarkGray) },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        viewModel.Atras_enviar(navController, green700)

        Spacer(modifier = Modifier.height(50.dp))

    }
}

@Composable
fun CoberturaButton(
    index: Int,
    icon: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val buttonColor = if (isSelected) Color(0xFF97B96E) else Color.White
    val textColor = if (isSelected) Color.White else Color.Black

    Button(
        onClick = onClick,
        modifier = Modifier
            .size(70.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .fillMaxSize(), // Asegura que el fondo cubra
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = textColor
        ),
        contentPadding = PaddingValues(2.dp),
        shape = RoundedCornerShape(8.dp) // Forma cuadrada con bordes redondeados
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "Cobertura $index",
            modifier = Modifier.size(60.dp)
        )
    }
}

@Composable
fun DisturbioButton(
    icon: Int? = null,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val buttonColor = if (isSelected) Color(0xFF97B96E) else Color.White
    val textColor = if (isSelected) Color.White else Color.Black
    val buttonSize = 85.dp
    val iconSize = 46.dp

    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .size(buttonSize)
            .aspectRatio(1f),
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(1.dp, Color.Gray),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = buttonColor,
            contentColor = textColor
        ),
        contentPadding = PaddingValues(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (icon != null) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = text,
                    modifier = Modifier
                        .size(iconSize)
                        .padding(bottom = 4.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            Text(
                text = text,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                maxLines = 2,
                lineHeight = 8.5.sp
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