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
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SwitchCamera
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.koadex.AppViewModelProvider
import com.example.koadex.ViewModels.FomularioEspecies_ViewModel
import com.example.koadex.ui.principal.KoadexViewModel

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioCamaraTrampa(
    activity: MainActivity,
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
            navController = navController
        )
    }
}
//@Preview(device = "spec:width=800px,height=1340px,dpi=300", showBackground = true)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun FormularioScreen(
    activity: MainActivity,
    modifier: Modifier = Modifier,
    navController: NavHostController
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
        val scrollState = rememberScrollState()
        val viewModel = FomularioEspecies_ViewModel()
        val green700 = colorResource(id = R.color.green_700)


        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .verticalScroll(rememberScrollState())
                .background(Color.White)

        ) {
            // Codigo de la camara
            Spacer(modifier = Modifier.height(6.dp))

            Text(
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

            Spacer(modifier = Modifier.height(16.dp))

            // Zona de la camara
            Text(
                text = stringResource(R.string.zona),
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
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
                                width = 2.dp,
                                color = Color(0xFF97B96E),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(
                                if (selectedZona == "bosque") Color(0xFF97B96E) else Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                    )
                   //Text(text = "Bosque")
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
                              width = 2.dp,
                                color = Color(0xFF97B96E),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(
                                if (selectedZona == "agroforestal") Color(0xFF97B96E) else Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                    )
                    //Text(text = "Arreglo")
                    //Text(text = "Agroforestal")
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
                                width = 2.dp,
                                color = Color(0xFF97B96E),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(
                                if (selectedZona == "transitorios") Color(0xFF97B96E) else Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                    )
                    //Text(text = "Cultivos")
                    //Text(text = "Transitorios")
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
                                width = 2.dp,
                                color = Color(0xFF97B96E),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(
                                if (selectedZona == "permanentes") Color(0xFF97B96E) else Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                    )
                    //Text(text = "Cultivos")
                    //Text(text = "Permanentes")
                }
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
                    value = placaGuaya,
                    onValueChange = { placaGuaya = it },
                    label = { Text(stringResource(R.string.placa_guaya), color = Color.DarkGray) },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = anchoCamino,
                    onValueChange = { anchoCamino = it },
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
                    value = distanciaObjetivo,
                    onValueChange = { distanciaObjetivo = it },
                    label = { Text(stringResource(R.string.distancia_objetivo), color = Color.DarkGray) },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = alturaLente,
                    onValueChange = { alturaLente = it },
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

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.weight(0.9f)
                        ) {
                            CheckboxItem(
                                checked = checklist[0],
                                onCheckedChange = { checklist[0] = it },
                                text = stringResource(R.string.instalada),
                                color = Color.Black // Color negro para el texto
                            )
                            CheckboxItem(
                                checked = checklist[1],
                                onCheckedChange = { checklist[1] = it },
                                text = stringResource(R.string.programada),
                                color = Color.Black // Color negro para el texto
                            )
                        }

                        Column(
                            modifier = Modifier.weight(0.8f)
                        ) {
                            CheckboxItem(
                                checked = checklist[2],
                                onCheckedChange = { checklist[2] = it },
                                text = stringResource(R.string.memoria),
                                color = Color.Black // Color negro para el texto
                            )
                            CheckboxItem(
                                checked = checklist[3],
                                onCheckedChange = { checklist[3] = it },
                                text = stringResource(R.string.prendida),
                                color = Color.Black // Color negro para el texto
                            )
                        }

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            CheckboxItem(
                                checked = checklist[4],
                                onCheckedChange = { checklist[4] = it },
                                text = stringResource(R.string.prueba_gateo),
                                color = Color.Black // Color negro para el texto
                            )
                            CheckboxItem(
                                checked = checklist[5],
                                onCheckedChange = { checklist[5] = it },
                                text = stringResource(R.string.letrero_camara),
                                color = Color.Black // Color negro para el texto
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            Text("Evidencias", style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start),
                color = Color.Black)
            viewModel.Botones_captura(green700)

            // Observaciones
            OutlinedTextField(
                value = observaciones,
                onValueChange = { observaciones = it }, // Actualizar el estado
                label = { Text("Observaciones", color = Color.DarkGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            viewModel.Atras_enviar(navController, green700)

            Spacer(modifier = Modifier.height(50.dp))

        }
    }
}

@Composable
fun CheckboxItem(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: String,
    color: Color = Color.Black // Color por defecto negro
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.size(20.dp),
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF4E7029)
            )
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 4.dp),
            color = Color.Black
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