package com.example.koadex.Views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.koadex.MainActivity
import com.example.koadex.R
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SwitchCamera
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.koadex.AppViewModelProvider
import com.example.koadex.ViewModels.FomularioEspecies_ViewModel
import com.example.koadex.ui.form.FormRouteFormDBViewModel
import com.example.koadex.ui.form.RouteFormDetails
import com.example.koadex.ui.form.RouteFormUiState
import com.example.koadex.ui.principal.KoadexViewModel
import kotlinx.coroutines.launch

val isFileSelectedFCT: MutableState<Boolean> = mutableStateOf(false)
var CameraPermision: MutableState<Boolean> = mutableStateOf(false)


@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioCamaraTrampa(
    activity: MainActivity,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: FormRouteFormDBViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.formulario),
                    color = Color.White, fontWeight = FontWeight.Bold)
                    },
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
        FormularioScreen(
            activity = activity,
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            formUiState = viewModel.formRouteUiState,
            onFormValueChange = viewModel::updateRouteFormUiState,
            onZoneIdChange = viewModel::updateZoneTypeId,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveRouteFrom()
                    navController.navigate("TiposForms")
                }
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun FormularioScreen(
    activity: MainActivity,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    formUiState: RouteFormUiState,
    onFormValueChange: (RouteFormDetails) -> Unit,
    onZoneIdChange: (Int) -> Unit,
    onSaveClick: () -> Unit
) {
    if (CameraPermision.value) {
        CameraWindow(activity)
    } else {
        var codigo by remember { mutableStateOf("") }
        var selectedZona by remember { mutableStateOf<String?>(null) }
        var nombreCamara by remember { mutableStateOf("") }
        var placaCamara by remember { mutableStateOf("") }
        var placaGuaya by remember { mutableStateOf("") }
        var anchoCamino by remember { mutableStateOf("") }
        var fecha by remember { mutableStateOf("") }
        var distanciaObjetivo by remember { mutableStateOf("") }
        var alturaLente by remember { mutableStateOf("") }
        var observaciones by remember { mutableStateOf("") }

        val checklist = remember {
            mutableStateListOf(
                false, // instalada
                false, // programada
                false, // memoria
                false, // prendida
                false, // prueba de gateo
                false  // cerrojo camara
            )
        }

        val evidencias = remember { mutableStateListOf<String>() }
        val context = LocalContext.current
        val scrollState = rememberScrollState()
        val viewModel = FomularioEspecies_ViewModel()
        val green700 = colorResource(id = R.color.green_700)
        val (currentZone, setCurrentZone) = remember { mutableStateOf("") }

        val guayaPlateText = remember { mutableStateOf("")}
        val routeWidthText = remember { mutableStateOf("")}
        val targetDistanceText = remember { mutableStateOf("")}
        val lensHeightText = remember { mutableStateOf("")}

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .verticalScroll(rememberScrollState())
                .background(Color.White)

        ) {
            // Codigo de la camara
            Spacer(modifier = Modifier.height(6.dp))

            /*Text(
                text = stringResource(R.string.codigo),
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )

            OutlinedTextField(
                value = codigo,
                onValueChange = { codigo = it },
                label = { Text(stringResource(R.string.codigo_camara), color = Color.DarkGray) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))*/

            // Zona de la camara
            Text(
                text = stringResource(R.string.zona),
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )

            // Botones de zona
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                val buttonSize = 80.dp
                ZoneButtonCT(
                    type = "bosque",
                    currentZone = currentZone,
                    onZoneChange = setCurrentZone,
                    onZoneIdChange =onZoneIdChange,
                    buttonSize = buttonSize,
                )
                ZoneButtonCT(
                    type = "arreglo",
                    currentZone = currentZone,
                    onZoneChange = setCurrentZone,
                    onZoneIdChange =onZoneIdChange,
                    buttonSize = buttonSize
                )
                ZoneButtonCT(
                    type = "transitorio",
                    currentZone = currentZone,
                    onZoneChange = setCurrentZone,
                    onZoneIdChange =onZoneIdChange,
                    buttonSize = buttonSize
                )
                ZoneButtonCT(
                    type = "permanente",
                    currentZone = currentZone,
                    onZoneChange = setCurrentZone,
                    onZoneIdChange =onZoneIdChange,
                    buttonSize = buttonSize
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            // Informacion de la camara
            Text(
                text = stringResource(R.string.informacion),
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = nombreCamara,
                    onValueChange = { nombreCamara = it },
                    label = { Text(stringResource(R.string.nombre_camara), color = Color.DarkGray) },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = placaCamara,
                    onValueChange = { placaCamara = it },
                    label = { Text(stringResource(R.string.placa_camara), color = Color.DarkGray) },
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = guayaPlateText.value,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.matches(Regex("^\\d*$"))) {
                            guayaPlateText.value = newValue
                            val guayaPlate = newValue.toIntOrNull() ?: 0
                            onFormValueChange(formUiState.formsRouteDetails.copy(guayaPlate = guayaPlate))
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = routeWidthText.value,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.matches(Regex("^\\d*$"))) {
                            routeWidthText.value = newValue
                            val routeWidth = newValue.toIntOrNull() ?: 0
                            onFormValueChange(formUiState.formsRouteDetails.copy(routeWidth = routeWidth))
                        }
                    },
                    label = { Text(stringResource(R.string.ancho_camino), color = Color.DarkGray) },
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text(stringResource(R.string.fecha), color = Color.DarkGray) },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = targetDistanceText.value,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.matches(Regex("^\\d*$"))) {
                            targetDistanceText.value = newValue
                            val targetDistance = newValue.toIntOrNull() ?: 0
                            onFormValueChange(formUiState.formsRouteDetails.copy(targetDistance = targetDistance))
                        }
                    },
                    label = { Text(stringResource(R.string.distancia_objetivo), color = Color.DarkGray) },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = lensHeightText.value,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.matches(Regex("^\\d*$"))) {
                            lensHeightText.value = newValue
                            val lensHeight = newValue.toIntOrNull() ?: 0
                            onFormValueChange(formUiState.formsRouteDetails.copy(lensHeight = lensHeight))
                        }
                    },
                    label = { Text(stringResource(R.string.altura_lente), color = Color.DarkGray) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(

                    modifier = Modifier.weight(1f)
                ) {
                    // LISTA DE CHEQUEO
                    Text(
                        text = stringResource(R.string.lista_chequeo),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 4.dp),
                        color = Color.Black

                    )

                    val checklistSelected = remember { mutableStateListOf<Int>() } // Índices seleccionados

                    ButtonChecklistWithColumns(
                        selectedItems = checklistSelected,
                        onItemSelected = { index ->
                            if (checklistSelected.contains(index)) {
                                checklistSelected.remove(index) // Deseleccionar si ya está seleccionado
                            } else {
                                checklistSelected.add(index) // Agregar al seleccionar
                            }
                        },
                        buttonLabels = listOf(
                            stringResource(R.string.instalada),
                            stringResource(R.string.programada),
                            stringResource(R.string.memoria),
                            stringResource(R.string.prendida),
                            stringResource(R.string.prueba_gateo),
                            stringResource(R.string.letrero_camara)
                        ),
                        color = Color.Black, // Color de texto predeterminado
                        selectedColor = Color(0xFF4E7029) // Color verde al seleccionar
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            Text("Evidencias", style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start),
                color = Color.Black)
            Botones_capturaFCT(green700)

            // Observaciones
            OutlinedTextField(
                value = formUiState.formsRouteDetails.observations,
                onValueChange = { onFormValueChange(formUiState.formsRouteDetails.copy(observations = it)) },
                label = { Text("Observaciones", color = Color.DarkGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            //viewModel.Atras_enviar(navController, green700)

            // Botón para guardar el formulario
            Button(
                onClick = {
                    onSaveClick()
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029))
            ) {
                Text("Guardar", color = Color.White)
            }

            Spacer(modifier = Modifier.height(50.dp))

        }
    }
}

@Composable
fun ButtonChecklistWithColumns(
    selectedItems: MutableList<Int>, // Índices seleccionados
    onItemSelected: (Int) -> Unit,
    buttonLabels: List<String>, // Etiquetas de los botones
    color: Color = Color.Black,
    selectedColor: Color = Color(0xFF4E7029) // Color verde al seleccionar
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val columnSize = buttonLabels.size / 3 + if (buttonLabels.size % 3 > 0) 1 else 0

        // Primera columna
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            buttonLabels.take(columnSize).forEachIndexed { index, label ->
                ChecklistButton(
                    text = label,
                    isSelected = selectedItems.contains(index),
                    onClick = { onItemSelected(index) },
                    color = color,
                    selectedColor = selectedColor
                )
            }
        }

        // Segunda columna
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            buttonLabels.drop(columnSize).take(columnSize).forEachIndexed { index, label ->
                ChecklistButton(
                    text = label,
                    isSelected = selectedItems.contains(index + columnSize),
                    onClick = { onItemSelected(index + columnSize) },
                    color = color,
                    selectedColor = selectedColor
                )
            }
        }

        // Tercera columna
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            buttonLabels.drop(2 * columnSize).forEachIndexed { index, label ->
                ChecklistButton(
                    text = label,
                    isSelected = selectedItems.contains(index + 2 * columnSize),
                    onClick = { onItemSelected(index + 2 * columnSize) },
                    color = color,
                    selectedColor = selectedColor
                )
            }
        }
    }
}

@Composable
fun ChecklistButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    color: Color,
    selectedColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .clickable(onClick = onClick), // Hacer clic en cualquier parte del Row
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(25.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(if (isSelected) selectedColor else Color.White)
                .border(1.dp, selectedColor, RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(8.dp)) // Espaciado entre botón y texto

        Text(
            text = text,
            color = if (isSelected) selectedColor else color,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp, fontWeight = FontWeight.Medium)
        )
    }
}

@Composable
fun ZoneButtonCT(
    type: String,
    currentZone: String,
    onZoneChange: (String) -> Unit,
    onZoneIdChange: (Int) -> Unit,
    buttonSize: Dp
) {
    val isSelected = currentZone == type

    val zoneId = when (type) {
        "bosque" -> FomularioEspecies_ViewModel.ZoneTypeIds.bosque
        "arreglo" -> FomularioEspecies_ViewModel.ZoneTypeIds.arreglo
        "transitorio" -> FomularioEspecies_ViewModel.ZoneTypeIds.transitorio
        else -> FomularioEspecies_ViewModel.ZoneTypeIds.permanente
    }

    Box(
        modifier = Modifier
            .size(buttonSize)
            .padding(3.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                color = if (isSelected) Color(0xFF97B96E) else Color.White
            )
            .border(
                width = 2.dp,
                color = Color(0xFF97B96E),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {
                onZoneChange(type)
                onZoneIdChange(zoneId)
            },
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
            modifier = Modifier.size(60.dp)
        )
    }
}

@Composable
fun EvidenciaItem(
    nombreArchivo: String,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = nombreArchivo)
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Eliminar archivo"
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(device = "spec:width=800px,height=1340px,dpi=300")
@Composable
fun FormularioCamaraTrampaPreview() {
    FormularioCamaraTrampa(activity = MainActivity(), modifier = Modifier, navController = rememberNavController())
}

@Composable
public fun Botones_capturaFCT(green700: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Boton_seleccionar_archivoFCT(green700)
        Boton_abrir_camaraFCT(green700)
    }
}

@Composable
public fun Boton_abrir_camaraFCT(green700: Color) {
    Button(
        onClick = { CameraPermision.value = true; isFileSelectedFCT.value = true },
        colors = ButtonDefaults.buttonColors(containerColor = green700)
    ) {
        Icon(Icons.Default.Camera, contentDescription = "Tomar foto", tint = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        Text("Tomar foto", color = Color.White)
    }
}

@Composable
public fun Boton_seleccionar_archivoFCT(green700: Color) {
    Button(
        onClick = { /* Handle file selection */ isFileSelectedFCT.value = true},
        colors = ButtonDefaults.buttonColors(containerColor = green700)
    ) {
        Icon(Icons.Default.FileOpen, contentDescription = "Seleccionar archivo", tint = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        Text("Elige archivo", color = Color.White)
    }
}