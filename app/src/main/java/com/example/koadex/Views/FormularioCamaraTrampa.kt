package com.example.koadex.Views

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cameraexample.ViewModels.CameraViewModel
import com.example.koadex.AppViewModelProvider
import com.example.koadex.MainActivity
import com.example.koadex.R
import com.example.koadex.ui.principal.KoadexViewModel
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SwitchCamera
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.rememberNavController

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioCamaraTrampa(
    activity: MainActivity,
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
        FormularioScreen(
            activity = activity,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun FormularioScreen(
    activity: MainActivity,
    modifier: Modifier = Modifier
) {
    var CameraPermision by remember { mutableStateOf(false) }
    if (CameraPermision) {
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

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Codigo de la camara
        Text(
            text = stringResource(R.string.codigo),
            style = MaterialTheme.typography.titleMedium

        )
        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = codigo,
            onValueChange = { codigo = it },
            label = { Text(stringResource(R.string.codigo_camara)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Zona de la camara
        Text(
            text = stringResource(R.string.zona),
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Bosque
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { selectedZona = "bosque" }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bosque),
                    contentDescription = "Bosque",
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { selectedZona = "bosque" }
                        .border(
                            width = if (selectedZona == "bosque") 2.dp else 0.dp,
                            color = Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            if (selectedZona == "bosque") Color(0xFF4E7029) else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                )
                Text(text = "Bosque")
            }

            // Arreglo Agroforestal
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { selectedZona = "agroforestal" }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arreglo_agroforestal),
                    contentDescription = "Arreglo Agroforestal",
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { selectedZona = "agroforestal" }
                        .border(
                            width = if (selectedZona == "agroforestal") 2.dp else 0.dp,
                            color = Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            if (selectedZona == "agroforestal") Color(0xFF4E7029) else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                )
                Text(text = "Arreglo")
                Text(text = "Agroforestal")
            }

            // Cultivos Transitorios
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { selectedZona = "transitorios" }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cultivos_transitorios),
                    contentDescription = "Cultivos Transitorios",
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { selectedZona = "transitorios" }
                        .border(
                            width = if (selectedZona == "transitorios") 2.dp else 0.dp,
                            color = Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            if (selectedZona == "transitorios") Color(0xFF4E7029) else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                )
                Text(text = "Cultivos")
                Text(text = "Transitorios")
            }

            // Cultivos Permanentes
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { selectedZona = "permanentes" }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cultivos_permanentes),
                    contentDescription = "Cultivos Permanentes",
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { selectedZona = "permanentes" }
                        .border(
                            width = if (selectedZona == "permanentes") 2.dp else 0.dp,
                            color = Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            if (selectedZona == "permanentes") Color(0xFF4E7029) else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                )
                Text(text = "Cultivos")
                Text(text = "Permanentes")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Informacion de la camara
        Text(
            text = stringResource(R.string.informacion),
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = nombreCamara,
                onValueChange = { nombreCamara = it },
                label = { Text(stringResource(R.string.nombre_camara)) },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = placaCamara,
                onValueChange = { placaCamara = it },
                label = { Text(stringResource(R.string.placa_camara)) },
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = placaGuaya,
                onValueChange = { placaGuaya = it },
                label = { Text(stringResource(R.string.placa_guaya)) },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = anchoCamino,
                onValueChange = { anchoCamino = it },
                label = { Text(stringResource(R.string.ancho_camino)) },
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
                label = { Text(stringResource(R.string.fecha)) },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = distanciaObjetivo,
                onValueChange = { distanciaObjetivo = it },
                label = { Text(stringResource(R.string.distancia_objetivo)) },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = alturaLente,
                onValueChange = { alturaLente = it },
                label = { Text(stringResource(R.string.altura_lente)) },
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
                // Lista de chequeo
                Text(
                    text = stringResource(R.string.lista_chequeo),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                checklist.forEachIndexed { index, isChecked ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = { checklist[index] = it },
                            modifier = Modifier.size(20.dp),
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFF4E7029)
                            )
                        )
                        Text(
                            text = when (index) {
                                0 -> stringResource(R.string.instalada)
                                1 -> stringResource(R.string.programada)
                                2 -> stringResource(R.string.memoria)
                                3 -> stringResource(R.string.prendida)
                                4 -> stringResource(R.string.prueba_gateo)
                                else -> stringResource(R.string.cerrojo_camara)
                            },
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
            }

            Row (
                modifier = Modifier.weight(1f)
            ) {
                // Evidencias
                Text(
                    text = stringResource(R.string.evidencias),
                    style = MaterialTheme.typography.titleMedium
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { /* Aqui va la selección de las fotos */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4E7029),
                            contentColor = Color.White
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.elegir_archivo))
                    }
                    Button(
                        onClick = {
                            CameraPermision = true
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4E7029),
                            contentColor = Color.White
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.tomar_foto))
                    }
                }

                evidencias.forEach { evidencia ->
                    EvidenciaItem(
                        nombreArchivo = evidencia,
                        onDelete = { evidencias.remove(evidencia) })
                }
            }
        }
    }

        // Observaciones
        Spacer(modifier = Modifier.height(12.dp))



        // Atras y Enviar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { /* Navegacion */},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4E7029),
                    contentColor = Color.White
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(R.string.atras))
            }
            Button(
                onClick = { /* Envio del formulario */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4E7029),
                    contentColor = Color.White
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(R.string.enviar))
            }
        }
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
@Preview(showBackground = true)
@Composable
fun FormularioCamaraTrampaPreview(){
    FormularioCamaraTrampa(activity = MainActivity(), navController = rememberNavController())
}