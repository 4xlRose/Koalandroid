package com.example.koadex.Views


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
/*
import com.example.koadex.ui.form.GeneralFormDetails
import com.example.koadex.ui.form.GeneralFormUiState
import com.example.koadex.ui.form.SeasonDetails
import com.example.koadex.ui.form.WeatherDetails
*/
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button


import androidx.compose.material3.Text
import androidx.compose.ui.draw.scale


import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController


// TEST
import com.example.koadex.utils.DateValidator

import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioGeneral(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: FormEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
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
        onDateChange = { newDate ->
            viewModel.updateUiState(viewModel.formUiState.formDetails.copy(date = newDate))
        },
        modifier = modifier
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormularioGeneralEntry(
    navController: NavHostController,
    formUiState: FormUiState,
    onFormValueChange: (FormDetails) -> Unit,
    onDateChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier
) {
    val textModifier = Modifier // Define it here
        .fillMaxWidth()
        .padding(10.dp)
        .height(40.dp)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 32.dp)
            .background(Color.White)
            .fillMaxSize()
    ) {
        // ... (keep existing header code)
        val textModifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(40.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(67.dp)
                .background(color = colorResource(R.color.green_100)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .padding(20.dp)
                    .size(30.dp),
                onClick = { navController.navigate("Principal") }
            )
            {
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                text = "Formulario",
                modifier = modifier
                    .offset(x = 10.dp),
                fontSize = 25.sp
            )
        }

        FormInputForm(
            formDetails = formUiState.formDetails,
            onFormValueChange = onFormValueChange,
            onDateChange = { newDate ->
                onFormValueChange(formUiState.formDetails.copy(date = newDate))
            },
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
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

        Spacer(modifier = Modifier.weight(1f))

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
                    .fillMaxWidth(),
                onClick = {
                    onSaveClick()

                },
                // enabled = formUiState.isEntryValid
            ) {
                Text("SIGUIENTE", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormInputForm(
    formDetails: FormDetails,
    onFormValueChange: (FormDetails) -> Unit,
    onDateChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean = true
) {
    var dateText by remember { mutableStateOf(formDetails.date) }

    // Variables para el TEST
    val dateValidator = remember { DateValidator() }
    var dateError by remember { mutableStateOf<String?>(null) }

    /* Formatear la fecha a dd/mm/aa
    fun formatFecha(input: String): String {
        if (input.length == 6) {
            val dia = input.substring(0, 2)
            val mes = input.substring(2, 4)
            val anio = input.substring(4, 6)
            return "$dia/$mes/$anio"
        }
        return input
    }*/
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ){
        OutlinedTextField(
            value = formDetails.name,
            label = { Text("Nombre") },
            onValueChange = { onFormValueChange(formDetails.copy(name = it)) },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
            ,
            enabled = enabled
        )
    }

    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            value = dateText,
            onValueChange = { input ->
                if (input.length <= 8) { // Considerando los '/'
                    val formattedInput = if (input.replace("/", "").length <= 6) {
                        dateValidator.formatDate(input.replace("/", ""))
                    } else {
                        input
                    }

                    dateText = formattedInput

                    // Validar solo si el campo está completo
                    if (formattedInput.length == 8) { // dd/mm/yy
                        if (dateValidator.isValidDate(formattedInput)) {
                            dateError = null
                            onDateChange(formattedInput)
                        } else {
                            dateError = "Fecha inválida"
                        }
                    }
                }
            },
            label = { Text(stringResource(R.string.fecha)) },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                ,
            isError = dateError != null,
            supportingText = {
                dateError?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )
        // ... (keep date picker button)
    }

    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            value = formDetails.place,
            label = { Text("Localidad") },
            onValueChange = { onFormValueChange(formDetails.copy(place = it)) },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        // ... (keep location button)
    }
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ){
        OutlinedTextField(
            value = formDetails.hour,
            label = { Text("Hora") },
            onValueChange = { onFormValueChange(formDetails.copy(hour = it)) },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
    }


    //Spacer(modifier = Modifier.weight(1f))

}

@Composable
fun WeatherButton(
    type: String,
    currentWeather: String,
    onWeatherChange: (String) -> Unit,
    buttonSize: Dp
) {
    Button(
        onClick = { onWeatherChange(type) },
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (currentWeather == type) Color(0xFF4E7029) else Color.White
        ),
        modifier = Modifier
            .size(buttonSize)
            .border(
                BorderStroke(
                    width = 1.dp,
                    color = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(
                    when (type) {
                        "soleado" -> R.drawable.soleado
                        "nublado" -> R.drawable.nublado
                        else -> R.drawable.lluvioso
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .scale(4f)
                    .padding(5.dp)
            )
        }
    }
}

@Composable
fun SeasonButton(
    type: String,
    currentSeason: String,
    onSeasonChange: (String) -> Unit,
    buttonSize: Dp
) {
    Button(
        onClick = { onSeasonChange(type) },
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (currentSeason == type) Color(0xFF4E7029) else Color.White
        ),
        modifier = Modifier
            .size(buttonSize)
            .border(
                BorderStroke(
                    width = 1.dp,
                    color = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(
                    when (type) {
                        "verano" -> R.drawable.verano
                        else -> R.drawable.invierno
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .scale(4f)
                    .padding(5.dp)
            )
        }
    }
}







@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun PreviewFormularioGeneral(){
    FormularioGeneral(
        navController = rememberNavController(),
        modifier = Modifier,
        viewModel = viewModel(factory = AppViewModelProvider.Factory)

    )

}


/*
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioGeneral(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: FormEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    FormularioGeneralEntry(
        navController = navController,
        formUiState = viewModel.generalFormUiState,
        onFormValueChange = viewModel::generalFormUpdateUiState,
        onSaveClick = {
            coroutineScope.launch {
                viewModel
                navController.navigate("Principal")
            }
        },
        modifier = modifier
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormularioGeneralEntry(
    navController: NavHostController,
    formUiState: GeneralFormUiState,
    onFormValueChange: (GeneralFormDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier
) {
    val textModifier = Modifier // Define it here
        .fillMaxWidth()
        .padding(10.dp)
        .height(40.dp)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp,top=82.dp)
            .fillMaxSize()
            .background(Color.White)

    ) {
        // ... (keep existing header code)
        val textModifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(40.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(67.dp)
                .background(color = colorResource(R.color.green_100)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .padding(20.dp)
                    .size(30.dp),
                onClick = { navController.navigate("Principal") }
                )
             {
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                text = "Formulario",
                modifier = modifier
                    .offset(x = 10.dp),
                fontSize = 25.sp
            )
        }

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
            var weather by remember { mutableStateOf(WeatherDetails().weather) }
            val buttonSize = 80.dp

            // Weather buttons
            WeatherButton(
                type = "soleado",
                currentWeather = weather,
                onWeatherChange = {
                    weather = it
                    onFormValueChange(formUiState.formDetails.copy(serialCode = it)) // serial code es temporal
                },
                buttonSize = buttonSize
            )

            WeatherButton(
                type = "nublado",
                currentWeather = weather,
                onWeatherChange = {
                    weather = it
                    onFormValueChange(formUiState.formDetails.copy(serialCode = it))
                },
                buttonSize = buttonSize
            )

            WeatherButton(
                type = "lluvioso",
                currentWeather = weather,
                onWeatherChange = {
                    weather = it
                    onFormValueChange(formUiState.formDetails.copy(serialCode = it))
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
            var season by remember { mutableStateOf(SeasonDetails().season) }
            val buttonSize = 80.dp

            // Season buttons
            SeasonButton(
                type = "verano",
                currentSeason = season,
                onSeasonChange = {
                    season = it
                    onFormValueChange(formUiState.formDetails.copy(serialCode = it))
                },
                buttonSize = buttonSize
            )

            SeasonButton(
                type = "invierno",
                currentSeason = season,
                onSeasonChange = {
                    season = it
                    onFormValueChange(formUiState.formDetails.copy(serialCode = it))
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
                onClick = { onSaveClick
                    ;navController.navigate("TiposForms")
                     },
                enabled = formUiState.isEntryValid
            ) {
                Text("SIGUIENTE", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormInputForm(
    formDetails: FormDetails,
    onFormValueChange: (FormDetails) -> Unit,
    onDateChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean = true
) {
    var dateText by remember { mutableStateOf(formDetails.date) }

    // Variables para el TEST
    val dateValidator = remember { DateValidator() }
    var dateError by remember { mutableStateOf<String?>(null) }

    /* Formatear la fecha a dd/mm/aa
    fun formatFecha(input: String): String {
        if (input.length == 6) {
            val dia = input.substring(0, 2)
            val mes = input.substring(2, 4)
            val anio = input.substring(4, 6)
            return "$dia/$mes/$anio"
        }
        return input
    }*/


    OutlinedTextField(
        value = formDetails.name,
        label = { Text("Nombre") },
        onValueChange = {
            val it = 0
            onFormValueChange(formDetails.copy(idUser = it))
        },
        modifier = Modifier
            .padding(10.dp)
            .width(320.dp),
        enabled = enabled
    )

    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            value = dateText,
            onValueChange = { input ->
                if (input.length <= 8) { // Considerando los '/'
                    val formattedInput = if (input.replace("/", "").length <= 6) {
                        dateValidator.formatDate(input.replace("/", ""))
                    } else {
                        input
                    }

                    dateText = formattedInput

                    // Validar solo si el campo esta completo
                    if (formattedInput.length == 8) { // dd/mm/yy
                        if (dateValidator.isValidDate(formattedInput)) {
                            dateError = null
                            onDateChange(formattedInput)
                        } else {
                            dateError = "Fecha inválida"
                        }
                    }
                }
            },
            label = { Text(stringResource(R.string.fecha)) },
            modifier = Modifier
                .width(180.dp)
                .offset(26.dp),
            isError = dateError != null,
            supportingText = {
                dateError?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )
        // ... (keep date picker button)
    }

    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            value = formDetails.place,
            label = { Text("Localidad") },
            onValueChange = { onFormValueChange(formDetails.copy(place = it)) },
            modifier = Modifier
                .width(262.dp)

        )
        // ... (keep location button)
    }

    OutlinedTextField(
        value = formDetails.hour,
        label = { Text("Hora") },
        onValueChange = { onFormValueChange(formDetails.copy(hour = it)) },
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
*/