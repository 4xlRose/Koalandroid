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
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.AppViewModelProvider
import com.example.koadex.R
import com.example.koadex.ViewModels.FomularioEspecies_ViewModel
import com.example.koadex.ui.form.FormQuadrantDBViewModel
import com.example.koadex.ui.form.FormRouteFormDBViewModel
import com.example.koadex.ui.form.QuadrantFormDetails
import com.example.koadex.ui.form.QuadrantFormUiState
import com.example.koadex.ui.form.RouteFormDetails
import com.example.koadex.ui.form.RouteFormUiState
import com.example.koadex.ui.principal.KoadexViewModel
import kotlinx.coroutines.launch

val isFileSelectedFC: MutableState<Boolean> = mutableStateOf(false)

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioCuadrante(
    //activity: MainActivity,
    navController: NavHostController,
    //modifier: Modifier = Modifier,
    viewModel: FormQuadrantDBViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val coroutineScope = rememberCoroutineScope()

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
        FormularioCuadranteScreen(
            /*activity = activity,*/
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            formUiState = viewModel.formQuadrantUiState,
            onFormValueChange = viewModel::updateQuadranteUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveQuadrantForm()
                    navController.navigate("TiposForms")
                }
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun FormularioCuadranteScreen(
    /*activity: MainActivity,*/
    modifier: Modifier = Modifier,
    navController: NavHostController,
    formUiState: QuadrantFormUiState,
    onFormValueChange: (QuadrantFormDetails) -> Unit,
    onSaveClick: () -> Unit
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

    val viewModel = FomularioEspecies_ViewModel()
    val green700 = colorResource(id = R.color.green_700)

    val actionButtonColors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029))
    val scrollState = rememberScrollState()

    val plateText = remember { mutableStateOf("")}
    val circumferenceText = remember { mutableStateOf("")}
    val biomonitorMtSizeText = remember { mutableStateOf("")}
    val distanceMtText = remember { mutableStateOf("")}
    val heightMtText = remember { mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .padding(32.dp)
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        // Contenido desplazable
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.Start
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            /*// Código
            OutlinedTextField(
                value = codigo,
                onValueChange = { codigo = it },
                label = { Text("Código") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))*/

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // CUADRANTE Y SUBCUADRANTE
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Cuadrante",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black)
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // A y B
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            BotonCuadranteAB(
                                text = "A",
                                isSelected = formUiState.formsQuadrantDetails.idSuperQuadrant == 1,
                                onClick = { onFormValueChange(formUiState.formsQuadrantDetails.copy(idSuperQuadrant = 1)) }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            BotonCuadranteAB(
                                text = "B",
                                isSelected = formUiState.formsQuadrantDetails.idSuperQuadrant == 2,
                                onClick = { onFormValueChange(formUiState.formsQuadrantDetails.copy(idSuperQuadrant = 2)) }
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 33.dp)
                        ){
                            Text(
                                text = "-",
                                fontSize = 50.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(1.dp)
                            )
                        }

                        // C a G
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(start = 15.dp)
                        ) {
                            //Spacer(modifier = Modifier.height(6.dp))

                            BotonCuadranteCG(
                                text = "C",
                                isSelected = formUiState.formsQuadrantDetails.idMidQuadrant == 1,
                                onClick = { onFormValueChange(formUiState.formsQuadrantDetails.copy(idMidQuadrant = 1)) }
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            BotonCuadranteCG(
                                text = "D",
                                isSelected = formUiState.formsQuadrantDetails.idMidQuadrant == 2,
                                onClick = { onFormValueChange(formUiState.formsQuadrantDetails.copy(idMidQuadrant = 2)) }
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            BotonCuadranteCG(
                                text = "E",
                                isSelected = formUiState.formsQuadrantDetails.idMidQuadrant == 3,
                                onClick = { onFormValueChange(formUiState.formsQuadrantDetails.copy(idMidQuadrant = 3)) }
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            BotonCuadranteCG(
                                text = "F",
                                isSelected = formUiState.formsQuadrantDetails.idMidQuadrant == 4,
                                onClick = { onFormValueChange(formUiState.formsQuadrantDetails.copy(idMidQuadrant = 4)) }
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            BotonCuadranteCG(
                                text = "G",
                                isSelected = formUiState.formsQuadrantDetails.idMidQuadrant == 5,
                                onClick = { onFormValueChange(formUiState.formsQuadrantDetails.copy(idMidQuadrant = 5)) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Subcuadrante
                    Text("Sub-Cuadrante",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black)
                    //Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        BotonSubCuadrante(
                            text = "1",
                            isSelected = formUiState.formsQuadrantDetails.idSubQuadrant == 1,
                            onClick = { onFormValueChange(formUiState.formsQuadrantDetails.copy(idSubQuadrant = 1)) }
                        )
                        BotonSubCuadrante(
                            text = "2",
                            isSelected = formUiState.formsQuadrantDetails.idSubQuadrant == 2,
                            onClick = { onFormValueChange(formUiState.formsQuadrantDetails.copy(idSubQuadrant = 2)) }
                        )
                        BotonSubCuadrante(
                            text = "3",
                            isSelected = formUiState.formsQuadrantDetails.idSubQuadrant == 3,
                            onClick = { onFormValueChange(formUiState.formsQuadrantDetails.copy(idSubQuadrant = 3)) }
                        )
                        BotonSubCuadrante(
                            text = "4",
                            isSelected = formUiState.formsQuadrantDetails.idSubQuadrant == 4,
                            onClick = { onFormValueChange(formUiState.formsQuadrantDetails.copy(idSubQuadrant = 4)) }
                        )
                    }
                }

                // HABITOS DE CRECIMIENTO
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(30.dp)) // Me apoyo para centrar
                    Text("Hábito de crecimiento",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))

                        CrecimientoBoton(
                            icon = R.drawable.arbusto,
                            text = "Arbusto",
                            altura = "< 1mt",
                            isSelected = formUiState.formsQuadrantDetails.idHabitat == 1,
                            onClick = { onFormValueChange(formUiState.formsQuadrantDetails.copy(idHabitat = 1)) }
                        )

                        CrecimientoBoton(
                            icon = R.drawable.arbolito,
                            text = "Arbolito",
                            altura = "1-3 mt",
                            isSelected = formUiState.formsQuadrantDetails.idHabitat == 2,
                            onClick = { onFormValueChange(formUiState.formsQuadrantDetails.copy(idHabitat = 2)) }
                        )

                        CrecimientoBoton(
                            icon = R.drawable.arbol,
                            text = "Árbol",
                            altura = "> 3mt",
                            isSelected = formUiState.formsQuadrantDetails.idHabitat == 3,
                            onClick = { onFormValueChange(formUiState.formsQuadrantDetails.copy(idHabitat = 3)) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // CAMPOS DE INFORMACION
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                OutlinedTextField(
                    value = formUiState.formsQuadrantDetails.specieName,
                    onValueChange = { onFormValueChange(formUiState.formsQuadrantDetails.copy(specieName =  it)) },
                    label = { Text("Nombre Común", color = Color.DarkGray) }, //Especie
                    modifier = Modifier.weight(1f)//.padding(start = 2.dp)
                )

                OutlinedTextField(
                    value = formUiState.formsQuadrantDetails.scientificName,
                    onValueChange = { onFormValueChange(formUiState.formsQuadrantDetails.copy(scientificName = it)) },
                    label = { Text("Nombre Científico") },
                    modifier = Modifier.weight(1f)//.padding(end = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = plateText.value,
                onValueChange = { newValue ->
                    if (newValue.isEmpty() || newValue.matches(Regex("^\\d*$"))) {
                        plateText.value = newValue
                        val plate = newValue.toIntOrNull() ?: 0
                        onFormValueChange(formUiState.formsQuadrantDetails.copy(plate = plate))
                    }
                },
                label = { Text("Placa") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                OutlinedTextField(
                    value = circumferenceText.value,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.matches(Regex("^\\d*$"))) {
                            circumferenceText.value = newValue
                            val circumference = newValue.toIntOrNull() ?: 0
                            onFormValueChange(formUiState.formsQuadrantDetails.copy(circumference = circumference))
                        }
                    },
                    label = { Text("Circunferencia(CL)") }, //en cm
                    modifier = Modifier.weight(1f)//.padding(start = 2.dp)
                )

                OutlinedTextField(
                    value = distanceMtText.value,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.matches(Regex("^\\d*$"))) {
                            distanceMtText.value = newValue
                            val distanceMt = newValue.toIntOrNull() ?: 0
                            onFormValueChange(formUiState.formsQuadrantDetails.copy(distanceMt = distanceMt))
                        }
                    },
                    label = { Text("Distancia en mt") },
                    modifier = Modifier.weight(1f)//.padding(end = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                OutlinedTextField(
                    value = biomonitorMtSizeText.value,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.matches(Regex("^\\d*$"))) {
                            biomonitorMtSizeText.value = newValue
                            val biomonitorMtSize = newValue.toIntOrNull() ?: 0
                            onFormValueChange(formUiState.formsQuadrantDetails.copy(biomonitorMtSize = biomonitorMtSize))
                        }
                    },
                    label = { Text("Estatura Biométrica") }, // en mt
                    modifier = Modifier.weight(1f)//.padding(start = 2.dp)
                )
                OutlinedTextField(
                    value = heightMtText.value,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.matches(Regex("^\\d*$"))) {
                            heightMtText.value = newValue
                            val heightMt = newValue.toIntOrNull() ?: 0
                            onFormValueChange(formUiState.formsQuadrantDetails.copy(heightMt = heightMt))
                        }
                    },
                    label = { Text("Altura en mt") },
                    modifier = Modifier.weight(1f)//.padding(end = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Evidencias
            Text("Evidencias", style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start),
                color = Color.Black)
            Botones_capturaFC(green700)

            Spacer(modifier = Modifier.height(16.dp))

            // Observaciones
            OutlinedTextField(
                value = formUiState.formsQuadrantDetails.observations,
                onValueChange = { onFormValueChange(formUiState.formsQuadrantDetails.copy(observations =  it)) },
                label = { Text("Observaciones") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botones de acción
            viewModel.Atras_enviar(navController, green700)

            /*// Botón para guardar el formulario
            Button(
                onClick = {
                    onSaveClick()
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029))
            ) {
                Text("Guardar", color = Color.White)
            }*/

            Spacer(modifier = Modifier.height(50.dp))

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
        border = BorderStroke(1.dp, if (isSelected) Color(0xFF97B96E) else Color.Gray),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isSelected) Color(0xFF97B96E) else Color.White // Cambia el color aquí
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
            .height(21.7.dp)
            .width(45.dp)
            .padding(1.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(0.7.dp, if (isSelected) Color(0xFF4E7029) else Color.Gray),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isSelected) Color(0xFF97B96E) else Color.White
        ),
        contentPadding = PaddingValues(1.dp)

    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Black,
            fontSize = 10.sp,
            textAlign = TextAlign.Center
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
            .height(26.dp)
            .width(40.dp)
            .padding(1.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, if (isSelected) Color(0xFF97B96E) else Color.Gray),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isSelected) Color(0xFF97B96E) else Color.White // Cambia el color aquí
        ),
        contentPadding = PaddingValues(2.dp)

    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Black,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(2.dp)
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
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(50.dp)
            .height(100.dp)
            .clickable(onClick = onClick)
            .padding(1.dp)
            .background(
                color = if (isSelected) Color(0xFF97B96E) else Color.White, // Cambia el fondo
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 1.dp,
                color = if (isSelected) Color(0xFF97B96E) else Color.Gray, // Cambia el borde
                shape = RoundedCornerShape(8.dp)
            ),
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = text,
            modifier = Modifier
                .size(40.dp)
                .padding(top = 2.dp)
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

@Composable
public fun Botones_capturaFC(green700: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Boton_seleccionar_archivoFC(green700)
        Boton_abrir_camaraFC(green700)
    }
}

@Composable
public fun Boton_abrir_camaraFC(green700: Color) {
    Button(
        onClick = { /* Handle photo capture */ isFileSelectedFC.value = true },
        colors = ButtonDefaults.buttonColors(containerColor = green700)
    ) {
        Icon(Icons.Default.Camera, contentDescription = "Tomar foto", tint = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        Text("Tomar foto", color = Color.White)
    }
}

@Composable
public fun Boton_seleccionar_archivoFC(green700: Color) {
    Button(
        onClick = { /* Handle file selection */ isFileSelectedFC.value = true},
        colors = ButtonDefaults.buttonColors(containerColor = green700)
    ) {
        Icon(Icons.Default.FileOpen, contentDescription = "Seleccionar archivo", tint = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        Text("Elige archivo", color = Color.White)
    }
}