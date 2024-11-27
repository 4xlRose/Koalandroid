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
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
import com.example.koadex.ui.form.FollowUpFormDetails
import com.example.koadex.ui.form.FollowUpFormUiState
import com.example.koadex.ui.form.FormFollowDBViewModel
import com.example.koadex.ui.principal.KoadexViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

val isFileSelectedFS: MutableState<Boolean> = mutableStateOf(false)

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioSeguimiento(
    //activity: MainActivity,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: FormFollowDBViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

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
        },
        containerColor = Color.White
    ) { paddingValues ->
        FormularioSeguimientoScreen(
            //activity = activity,
            navController = navController,
            formUiState = viewModel.formFollowUiState,
            onFormValueChange = viewModel::updateFollowUpFormUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveFollowForm()
                    navController.navigate("TiposForms")
                }
            },
            modifier = Modifier.padding(paddingValues),
        )
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun FormularioSeguimientoScreen(
    //activity: MainActivity,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    formUiState: FollowUpFormUiState,
    onFormValueChange: (FollowUpFormDetails) -> Unit,
    onSaveClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    // Estado para los botones seleccionados
    /*val coberturaSeleccionada = remember { mutableStateOf(-1) }
    val disturbioSeleccionado = remember { mutableStateOf(-1) }
    val seguimientoSeleccionado = remember { mutableStateOf(-1) } // Estado para los botones de Seguimiento
    val cambioSeleccionado = remember { mutableStateOf(-1) } // Estado para los botones de Cambió*/
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

        /*// Campo de Código
        OutlinedTextField(
            value = codigo,
            onValueChange = { codigo = it},
            label = { Text("Código", color = Color.DarkGray) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))*/

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
                        onClick = { onFormValueChange(formUiState.formsFollowUpDetails.copy(followUp = true)) }, // Marca el botón como seleccionado
                        border = BorderStroke(1.dp, Color.Gray),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (formUiState.formsFollowUpDetails.followUp) Color(0xFF97B96E) else Color.White,
                            contentColor = if (formUiState.formsFollowUpDetails.followUp) Color.White else Color.Black
                        )
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Check")
                    }
                    OutlinedButton(
                        onClick = { onFormValueChange(formUiState.formsFollowUpDetails.copy(followUp = false)) }, // Marca el botón como seleccionado
                        border = BorderStroke(1.dp, Color.Gray),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (!formUiState.formsFollowUpDetails.followUp) Color(0xFF97B96E) else Color.White,
                            contentColor = if (!formUiState.formsFollowUpDetails.followUp) Color.White else Color.Black
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
                        onClick = { onFormValueChange(formUiState.formsFollowUpDetails.copy(change = true)) }, // Marca el botón como seleccionado
                        border = BorderStroke(1.dp, Color.Gray),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (formUiState.formsFollowUpDetails.change) Color(0xFF4E7029) else Color.White,
                            contentColor = if (formUiState.formsFollowUpDetails.change) Color.White else Color.Black
                        )
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Check")
                    }
                    OutlinedButton(
                        onClick = { onFormValueChange(formUiState.formsFollowUpDetails.copy(change = false)) }, // Marca el botón como seleccionado
                        border = BorderStroke(1.dp, Color.Gray),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (!formUiState.formsFollowUpDetails.change) Color(0xFF4E7029) else Color.White,
                            contentColor = if (!formUiState.formsFollowUpDetails.change) Color.White else Color.Black
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
                    (1..5).forEach { index -> // Iterar por los botones de la fila
                        CoberturaButton(
                            index = index,
                            icon = when (index) {
                                1 -> R.drawable.bd_cobertura
                                2 -> R.drawable.ra_cobertura
                                3 -> R.drawable.rb_cobertura
                                4 -> R.drawable.pa_cobertura
                                else -> R.drawable.pl_cobertura
                            },
                            isSelected = formUiState.formsFollowUpDetails.idCoverage == index,
                            onClick = { onFormValueChange(formUiState.formsFollowUpDetails.copy(idCoverage = index)) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Segunda fila de botones de Cobertura
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    (6..10).forEach { index -> // Iterar por los botones de la fila
                        CoberturaButton(
                            index = index,
                            icon = when (index) {
                                6 -> R.drawable.cp_cobertura
                                7 -> R.drawable.ct_cobertura
                                8 -> R.drawable.vh_cobertura
                                9 -> R.drawable.td_cobertura
                                else -> R.drawable.if_cobertura
                            },
                            isSelected = formUiState.formsFollowUpDetails.idCoverage == index,
                            onClick = { onFormValueChange(formUiState.formsFollowUpDetails.copy(idCoverage = index)) }
                        )
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        // CAMPO TIPO DE CULTIVO
        OutlinedTextField(
            value = formUiState.formsFollowUpDetails.cropType,
            onValueChange = { onFormValueChange(formUiState.formsFollowUpDetails.copy(cropType = it)) },
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
                        isSelected = formUiState.formsFollowUpDetails.idDisturbance == 1,
                        onClick = { onFormValueChange(formUiState.formsFollowUpDetails.copy(idDisturbance = 1)) }
                    )
                    DisturbioButton(
                        icon = R.drawable.hoguera_1,
                        text = "Quema",
                        isSelected = formUiState.formsFollowUpDetails.idDisturbance == 2,
                        onClick = { onFormValueChange(formUiState.formsFollowUpDetails.copy(idDisturbance = 2)) }
                    )
                    DisturbioButton(
                        icon = R.drawable.tala_de_arboles_1,
                        text = "Tala",
                        isSelected = formUiState.formsFollowUpDetails.idDisturbance == 3,
                        onClick = { onFormValueChange(formUiState.formsFollowUpDetails.copy(idDisturbance = 3)) }
                    )
                    DisturbioButton(
                        icon = R.drawable.polvo_1,
                        text = "Erosión",
                        isSelected = formUiState.formsFollowUpDetails.idDisturbance == 4,
                        onClick = { onFormValueChange(formUiState.formsFollowUpDetails.copy(idDisturbance = 4)) }
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
                        isSelected = formUiState.formsFollowUpDetails.idDisturbance == 5,
                        onClick = { onFormValueChange(formUiState.formsFollowUpDetails.copy(idDisturbance = 5)) }
                    )
                    DisturbioButton(
                        icon = R.drawable.camino_1,
                        text = "Carretera",
                        isSelected = formUiState.formsFollowUpDetails.idDisturbance == 6,
                        onClick = { onFormValueChange(formUiState.formsFollowUpDetails.copy(idDisturbance = 6)) }
                    )
                    DisturbioButton(
                        icon = R.drawable.algas_marinas_1,
                        text = "Plantas \nacuáticas",
                        isSelected = formUiState.formsFollowUpDetails.idDisturbance == 7,
                        onClick = { onFormValueChange(formUiState.formsFollowUpDetails.copy(idDisturbance = 7)) }
                    )
                    DisturbioButton(
                        text = "Otro",
                        isSelected = formUiState.formsFollowUpDetails.idDisturbance == 8,
                        onClick = { onFormValueChange(formUiState.formsFollowUpDetails.copy(idDisturbance = 8)) }
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
        Botones_capturaFS(green700)


        Spacer(modifier = Modifier.height(16.dp))

        // Observaciones
        OutlinedTextField(
            value = formUiState.formsFollowUpDetails.observations,
            onValueChange = { onFormValueChange(formUiState.formsFollowUpDetails.copy(observations = it)) },
            label = { Text("Observaciones", color = Color.DarkGray) },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        viewModel.Atras_enviar(navController, green700)

        /*// Submit button -PARA PRUEBAS-
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
                    .padding(40.dp)
                    .fillMaxWidth(),
                onClick = {
                    onSaveClick()

                },
                // enabled = formUiState.isEntryValid
            ) {
                Text("SIGUIENTE", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }*/

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

@Composable
public fun Botones_capturaFS(green700: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Boton_seleccionar_archivoFS(green700)
        Boton_abrir_camaraFS(green700)
    }
}

@Composable
public fun Boton_abrir_camaraFS(green700: Color) {
    Button(
        onClick = { /* Handle photo capture */ isFileSelectedFS.value = true },
        colors = ButtonDefaults.buttonColors(containerColor = green700)
    ) {
        Icon(Icons.Default.Camera, contentDescription = "Tomar foto", tint = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        Text("Tomar foto", color = Color.White)
    }
}

@Composable
public fun Boton_seleccionar_archivoFS(green700: Color) {
    Button(
        onClick = { /* Handle file selection */ isFileSelectedFS.value = true},
        colors = ButtonDefaults.buttonColors(containerColor = green700)
    ) {
        Icon(Icons.Default.FileOpen, contentDescription = "Seleccionar archivo", tint = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        Text("Elige archivo", color = Color.White)
    }
}
