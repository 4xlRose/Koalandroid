package com.example.koadex.Views


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavHostController

import com.example.koadex.R



import com.example.koadex.ui.form.FormEntryViewModel


import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.koadex.AppViewModelProvider
import com.example.koadex.ui.form.FormDetails


import com.example.koadex.ui.form.FormUiState


import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioGeneral(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: FormEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
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
        FormularioGeneralEntry(
            navController = navController,
            formUiState = viewModel.formUiState,
            onFormValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveForm()
                    navController.navigate("TiposForms")
                }
            },
            modifier = modifier.padding(paddingValues)
        )
    }

}

@Composable
fun FormularioGeneralEntry(
    navController: NavHostController,
    formUiState: FormUiState,
    onFormValueChange: (FormDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp,top=52.dp)
            .fillMaxSize()
            .background(Color.White)

    ) {
        // ... (keep existing header code)
        val textModifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(40.dp)
        FormInputForm(
            formDetails = formUiState.formDetails,
            onFormValueChange = onFormValueChange,
            modifier = Modifier
        )

        // Weather section
        Row(
            modifier = textModifier,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "Estado del Tiempo",
                modifier = modifier.offset(x = 30.dp),
                fontSize = 18.sp
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            var weather by remember { mutableStateOf(formUiState.formDetails.weather) }
            val buttonSize = 80.dp

            // Weather buttons
            WeatherButton(
                type = "soleado",
                currentWeather = weather,
                onWeatherChange = {
                    weather = it
                    onFormValueChange(formUiState.formDetails.copy(weather = it))
                },
                buttonSize = buttonSize
            )

            WeatherButton(
                type = "nublado",
                currentWeather = weather,
                onWeatherChange = {
                    weather = it
                    onFormValueChange(formUiState.formDetails.copy(weather = it))
                },
                buttonSize = buttonSize
            )

            WeatherButton(
                type = "lluvioso",
                currentWeather = weather,
                onWeatherChange = {
                    weather = it
                    onFormValueChange(formUiState.formDetails.copy(weather = it))
                },
                buttonSize = buttonSize
            )
        }

        // Season section
        Row(
            modifier = textModifier,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "Época",
                modifier = modifier.offset(x = 30.dp),
                fontSize = 18.sp
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            var season by remember { mutableStateOf(formUiState.formDetails.season) }
            val buttonSize = 80.dp

            // Season buttons
            SeasonButton(
                type = "verano",
                currentSeason = season,
                onSeasonChange = {
                    season = it
                    onFormValueChange(formUiState.formDetails.copy(season = it))
                },
                buttonSize = buttonSize
            )

            SeasonButton(
                type = "invierno",
                currentSeason = season,
                onSeasonChange = {
                    season = it
                    onFormValueChange(formUiState.formDetails.copy(season = it))
                },
                buttonSize = buttonSize
            )
        }

        // Submit button
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
                    .width(300.dp),
                onClick = { onSaveClick()
                     },
                enabled = formUiState.isEntryValid
            ) {
                Text("SIGUIENTE", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun FormInputForm(
    formDetails: FormDetails,
    onFormValueChange: (FormDetails) -> Unit,
    modifier: Modifier,
    enabled: Boolean = true
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
    ) {
        OutlinedTextField(
            value = formDetails.name,
            label = { Text("Nombre") },
            onValueChange = { onFormValueChange(formDetails.copy(name = it)) },
            modifier = Modifier
                .width(320.dp),
            enabled = enabled
        )
    }

    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
    ) {
        OutlinedTextField(
            value = formDetails.date,
            label = { Text("Fecha") },
            onValueChange = { onFormValueChange(formDetails.copy(date = it)) },
            modifier = Modifier
                .width(180.dp)
                .offset(26.dp)
        )

    }

    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),

    ) {
        OutlinedTextField(
            value = formDetails.place,
            label = { Text("Localidad") },
            onValueChange = { onFormValueChange(formDetails.copy(place = it)) },
            modifier = Modifier
                .width(262.dp)
                .offset(26.dp)
        )

    }

    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        //verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            value = formDetails.hour,
            label = { Text("Hora") },
            onValueChange = { onFormValueChange(formDetails.copy(hour = it)) },
            modifier = Modifier
                .offset(26.dp)
                .width(320.dp)
        )
    }
}

@Composable
fun WeatherButton(
    type: String,
    currentWeather: String,
    onWeatherChange: (String) -> Unit,
    buttonSize: Dp
) {
    OutlinedIconToggleButton(
        checked = currentWeather == type,
        onCheckedChange = { onWeatherChange(type) },
        shape = RoundedCornerShape(12.dp),
        colors = IconButtonDefaults.iconToggleButtonColors(
            containerColor = if (currentWeather == type) Color(0xFFCDE4B4) else Color.White
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (currentWeather == type) Color(0xFF4E7029) else Color.Black
        ),
        modifier = Modifier.size(buttonSize)
    ) {
        Image(
            painter = painterResource(
                when (type) {
                    "soleado" -> R.drawable.soleado
                    "nublado" -> R.drawable.nublado
                    else -> R.drawable.lluvioso
                }
            ),
            contentDescription = null
        )
    }
}

@Composable
fun SeasonButton(
    type: String,
    currentSeason: String,
    onSeasonChange: (String) -> Unit,
    buttonSize: Dp
) {
    OutlinedIconToggleButton(
        checked = currentSeason == type,
        onCheckedChange = { onSeasonChange(type) },
        shape = RoundedCornerShape(12.dp),
        colors = IconButtonDefaults.iconToggleButtonColors(
            containerColor = if (currentSeason == type) Color(0xFFCDE4B4) else Color.White
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (currentSeason == type) Color(0xFF4E7029) else Color.Black
        ),
        modifier = Modifier.size(buttonSize)
    ) {
        Image(
            painter = painterResource(
                when (type) {
                    "verano" -> R.drawable.verano
                    else -> R.drawable.invierno
                }
            ),
            contentDescription = null
        )
    }
}
