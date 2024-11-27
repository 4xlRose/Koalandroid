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
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.FileOpen
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
import androidx.compose.runtime.MutableState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.koadex.AppViewModelProvider
import com.example.koadex.MainActivity
import com.example.koadex.ViewModels.FomularioEspecies_ViewModel
import com.example.koadex.ViewModels.NavigationModel
import com.example.koadex.data.SeasonEntity
import com.example.koadex.data.WeatherEntity
import com.example.koadex.ui.form.FormGeneralDBViewModel
import com.example.koadex.ui.form.FormWeatherDBViewModel
import com.example.koadex.ui.form.GeneralFormsDetails
import com.example.koadex.ui.form.UserDetails
import com.example.koadex.ui.form.UserUiState
import com.example.koadex.ui.form.WeatherFormDetails
import com.example.koadex.ui.form.WeatherFormUiState
import kotlinx.coroutines.flow.Flow

val isFileSelectedFVC: MutableState<Boolean> = mutableStateOf(false)


@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioVariablesClimaticas(
    activity: MainActivity,
    navController: NavHostController,
    navModel: NavigationModel,
    //modifier: Modifier = Modifier,
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
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
        },
        containerColor = Color.White
    ) { paddingValues ->
        FormularioVariablesClimaticasScreen(
            activity = activity,
            navController = navController,
            navModel = navModel,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize() // Asegura que el contenido llene todo el espacio
                .background(Color.White) // Fondo blanco para todo
        )
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun FormularioVariablesClimaticasScreen(
    activity: MainActivity,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    navModel: NavigationModel,
    viewModel : FormWeatherDBViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    FormularioClimaEntry(
        navController = navController,
        formUiState = viewModel.formWeatherUiState,
        navModel = navModel,
        onFormValueChange = viewModel::updateWeatherUiState,
        onZoneIdChange = viewModel::updateZoneTypeId,
        onSaveClick = {
            coroutineScope.launch {
                viewModel.saveWeatherForm()
                //AGREGAR EL NAV CONTROLLER
            }
        },
        activity = activity,
        modifier = modifier)
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun FormularioClimaEntry(
    activity: MainActivity,
    navController: NavHostController,
    formUiState: WeatherFormUiState,
    navModel: NavigationModel,
    onFormValueChange: (WeatherFormDetails) -> Unit,
    onZoneIdChange: (Int) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (CameraPermision.value) {
        CameraWindow(activity)
    } else {
        val scrollState = rememberScrollState()
        val (currentZone, setCurrentZone) = remember { mutableStateOf("") } // Estado para la zona seleccionada
        val viewModel = FomularioEspecies_ViewModel()
        val green700 = colorResource(id = R.color.green_700)

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .verticalScroll(scrollState)
                .background(color = Color.White), // Habilitar scroll
            verticalArrangement = Arrangement.spacedBy(16.dp),

            ) {
            Spacer(modifier = Modifier.height(6.dp))

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
                    onZoneIdChange = onZoneIdChange,
                    buttonSize = buttonSize,
                )
                ZoneButton(
                    type = "arreglo",
                    currentZone = currentZone,
                    onZoneChange = setCurrentZone,
                    onZoneIdChange = onZoneIdChange,
                    buttonSize = buttonSize
                )
                ZoneButton(
                    type = "transitorio",
                    currentZone = currentZone,
                    onZoneChange = setCurrentZone,
                    onZoneIdChange = onZoneIdChange,
                    buttonSize = buttonSize
                )
                ZoneButton(
                    type = "permanente",
                    currentZone = currentZone,
                    onZoneChange = setCurrentZone,
                    onZoneIdChange = onZoneIdChange,
                    buttonSize = buttonSize
                )
            }

            ClimaInputForm(
                modifier = Modifier.fillMaxWidth(),
                formWeatherDetails = formUiState.formWeatherDetails,
                onFormValueChange = onFormValueChange
            )

            viewModel.Atras_enviar(navController, green700, navModel)

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
}

@Composable
fun ZoneButton(
    type: String,
    currentZone: String,
    onZoneChange: (String) -> Unit,
    onZoneIdChange: (Int) -> Unit,
    buttonSize: Dp
) {
    val isSelected = currentZone == type // Determina si el botón está seleccionado

    // Determinar el ID segun el tipo de zona
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
            .clip(RoundedCornerShape(12.dp)) // Aplica las esquinas redondeadas al fondo
            .background(
                color = if (isSelected) Color(0xFF97B96E) else Color.White // Fondo dinámico
            )
            .border(
                width = 2.dp,
                color = Color(0xFF97B96E), // Verde si está seleccionado, más suave si no
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {
                onZoneChange(type)
                onZoneIdChange(zoneId) // Se actualiza el ID
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
            modifier = Modifier.size(60.dp) // Aumenta el tamaño de la imagen
        )
    }
}


@Composable
fun ClimaInputForm(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    formWeatherDetails: WeatherFormDetails,
    onFormValueChange: (WeatherFormDetails) -> Unit
) {
    /*val (pluviosidad, setPluviosidad) = remember { mutableStateOf("") }
    val (temperaturaMaxima, setTemperaturaMaxima) = remember { mutableStateOf("") }
    val (temperaturaMinima, setTemperaturaMinima) = remember { mutableStateOf("") }
    val (humedadMaxima, setHumedadMaxima) = remember { mutableStateOf("") }
    val (humedadMinima, setHumedadMinima) = remember { mutableStateOf("") }
    val (nivelQuebrada, setNivelQuebrada) = remember { mutableStateOf("") }
    val (observaciones, setObservaciones) = remember { mutableStateOf("") }*/
    val actionButtonColors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029))
    val viewModel = FomularioEspecies_ViewModel()
    val green700 = colorResource(id = R.color.green_700)

    // Variables temporales para el texto ingresado
    val rainfallText = remember { mutableStateOf("") } // Maneja el texto ingresado
    val maxTemperatureText = remember { mutableStateOf("") }
    val maxHumidityText = remember { mutableStateOf("") }
    val minTemperatureText = remember { mutableStateOf("") }
    val minHumidityText = remember { mutableStateOf("") }
    val streamMtLevelText = remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        OutlinedTextField(
            value = rainfallText.value, // Usa el texto ingresado temporalmente
            label = { Text("Pluviosidad (mm)") },
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.matches(Regex("^\\d*(\\.\\d*)?$"))) {
                    rainfallText.value = newValue // Actualiza el texto temporal
                    val rainfall = newValue.toDoubleOrNull() ?: 0.0
                    onFormValueChange(formWeatherDetails.copy(rainfall = rainfall))
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )

        OutlinedTextField(
            value = maxTemperatureText.value,
            label = { Text("Temperatura máxima") },
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.matches(Regex("^\\d*(\\.\\d*)?$"))) {
                    maxTemperatureText.value = newValue
                    val maxTemperature = newValue.toDoubleOrNull() ?: 0.0
                    onFormValueChange(formWeatherDetails.copy(maxTemperature = maxTemperature))
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled

        )
        OutlinedTextField(
            value = minTemperatureText.value,
            label = { Text("Temperatura mínima") },
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.matches(Regex("^\\d*(\\.\\d*)?$"))) {
                    minTemperatureText.value = newValue
                    val minTemperature = newValue.toDoubleOrNull() ?: 0.0
                    onFormValueChange(formWeatherDetails.copy(minTemperature = minTemperature))
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = maxHumidityText.value,
            label = { Text("Humedad máxima") },
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.matches(Regex("^\\d*(\\.\\d*)?$"))) {
                    maxHumidityText.value = newValue
                    val maxHumidity = newValue.toDoubleOrNull() ?: 0.0
                    onFormValueChange(formWeatherDetails.copy(maxHumidity = maxHumidity))
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = minHumidityText.value,
            label = { Text("Humedad mínima") },
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.matches(Regex("^\\d*(\\.\\d*)?$"))) {
                    minHumidityText.value = newValue
                    val minHumidity = newValue.toDoubleOrNull() ?: 0.0
                    onFormValueChange(formWeatherDetails.copy(minHumidity = minHumidity))
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = streamMtLevelText.value,
            label = { Text("Nivel Quebrada (mt)") },
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.matches(Regex("^\\d*(\\.\\d*)?$"))) {
                    streamMtLevelText.value = newValue
                    val streamMtLevel = newValue.toDoubleOrNull() ?: 0.0
                    onFormValueChange(formWeatherDetails.copy(streamMtLevel = streamMtLevel))
                }
            },
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
/*
@RequiresApi(Build.VERSION_CODES.P)
@Preview(device = "spec:width=800px,height=1340px,dpi=300")
@Composable
fun VariableClimaticasPreview(){
    FormularioVariablesClimaticas(activity = MainActivity(),navController = rememberNavController())
}*/


@Composable
public fun Botones_capturaFVC(green700: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Boton_seleccionar_archivoFVC(green700)
        Boton_abrir_camaraFVC(green700)
    }
}

@Composable
public fun Boton_abrir_camaraFVC(green700: Color) {
    Button(
        onClick = { CameraPermision.value = true; isFileSelectedFVC.value = true },
        colors = ButtonDefaults.buttonColors(containerColor = green700)
    ) {
        Icon(Icons.Default.Camera, contentDescription = "Tomar foto", tint = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        Text("Tomar foto", color = Color.White)
    }
}

@Composable
public fun Boton_seleccionar_archivoFVC(green700: Color) {
    Button(
        onClick = { /* Handle file selection */ isFileSelectedFVC.value = true},
        colors = ButtonDefaults.buttonColors(containerColor = green700)
    ) {
        Icon(Icons.Default.FileOpen, contentDescription = "Seleccionar archivo", tint = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        Text("Elige archivo", color = Color.White)
    }
}

