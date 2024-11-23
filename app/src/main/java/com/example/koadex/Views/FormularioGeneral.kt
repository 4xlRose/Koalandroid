package com.example.koadex.Views


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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


import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.koadex.AppViewModelProvider
import com.example.koadex.clases.User

/*
import com.example.koadex.ui.form.GeneralFormDetails
import com.example.koadex.ui.form.GeneralFormUiState
import com.example.koadex.ui.form.SeasonDetails
import com.example.koadex.ui.form.WeatherDetails
*/


import com.example.koadex.data.SeasonEntity
import com.example.koadex.data.UserEntity
import com.example.koadex.data.WeatherEntity
import com.example.koadex.navigate.sampleUser
import com.example.koadex.ui.form.FormGeneralDBViewModel
import com.example.koadex.ui.form.GeneralFormUiState
import com.example.koadex.ui.form.GeneralFormsDetails
import com.example.koadex.ui.form.UserDetails
import com.example.koadex.ui.form.UserUiState


// TEST
import com.example.koadex.utils.DateValidator
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.launch
import kotlin.reflect.KSuspendFunction1

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormularioGeneral(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: FormGeneralDBViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    FormularioGeneralEntry(
        navController = navController,
        formUiState = viewModel.formGeneralUiState,
        onFormValueChange = viewModel::updateGeneraFormUiState,
        userUiState = viewModel.userUiState,
        onUserValueChange = viewModel::updateUserUiState,
        getWeatherById = viewModel::getWeatherById,
        getSeasonById = viewModel::getSeasonById,
        onSaveClick = {
            coroutineScope.launch {
                viewModel.saveGeneralForm()
                navController.navigate("TiposForms")
            }
        },
        onDateChange = { newDate ->
            viewModel.updateGeneraFormUiState(viewModel.formGeneralUiState.formsDetails)
        },
        modifier = modifier
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormularioGeneralEntry(
    navController: NavHostController,
    formUiState: GeneralFormUiState,
    onFormValueChange: (GeneralFormsDetails) -> Unit,
    userUiState: UserUiState,
    onUserValueChange: (UserDetails) -> Unit,
    getWeatherById: (Int) -> Flow<WeatherEntity?>,
    getSeasonById: (Int) -> Flow<SeasonEntity?>,
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
        val textModifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(40.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(67.dp)
                .background(Color(0xFF4E7029)),
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
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.White
                )
            }
            Text(
                text = "Formulario",
                modifier = modifier
                    .offset(x = 10.dp),
                fontSize = 25.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        FormInputForm(
            formDetails = formUiState.formsDetails,
            onFormValueChange = onFormValueChange,
            userDetails = userUiState.userDetails,
            onUserValueChange = onUserValueChange,
            onDateChange = { newDate ->
                onFormValueChange(formUiState.formsDetails.copy(date = newDate))
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
                fontSize = 18.sp,
                color = Color.Black
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            var weatherID by remember { mutableIntStateOf(formUiState.formsDetails.idWeather) }
            val weather = getWeatherById(weatherID).collectAsState(initial = null).value
            val buttonSize = 80.dp

            // Weather buttons
            WeatherButton(
                type = "soleado",
                currentWeather = weather?.weather ?: "soleado",
                onWeatherChange = {
                    onFormValueChange(formUiState.formsDetails.copy(idWeather = weatherID))
                },
                buttonSize = buttonSize
            )

            WeatherButton(
                type = "nublado",
                currentWeather = weather?.weather ?: "nublado",
                onWeatherChange = {
                    onFormValueChange(formUiState.formsDetails.copy(idWeather = weatherID))
                },
                buttonSize = buttonSize
            )

            WeatherButton(
                type = "lluvioso",
                currentWeather = weather?.weather ?: "lluvioso",
                onWeatherChange = {
                    onFormValueChange(formUiState.formsDetails.copy(idWeather = weatherID))
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
                fontSize = 18.sp,
                color = Color.Black
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            var seasonID by remember { mutableIntStateOf(formUiState.formsDetails.idSeason) }
            val season = getSeasonById(seasonID).collectAsState(initial = null).value
            val buttonSize = 80.dp

            // Season buttons
            SeasonButton(
                type = "verano",
                currentSeason = season?.season ?: "verano",
                onSeasonChange = {

                    onFormValueChange(formUiState.formsDetails.copy(idSeason = seasonID))
                },
                buttonSize = buttonSize
            )

            SeasonButton(
                type = "invierno",
                currentSeason = season?.season ?: "invierno",
                onSeasonChange = {
                    onFormValueChange(formUiState.formsDetails.copy(idSeason = seasonID))
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
                Text("SIGUIENTE", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormInputForm(
    formDetails: GeneralFormsDetails,
    onFormValueChange: (GeneralFormsDetails) -> Unit,
    userDetails: UserDetails,
    onUserValueChange: (UserDetails) -> Unit,
    onDateChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean = true
) {
    var dateText by remember { mutableStateOf(formDetails.date) }

    // Variables para el TEST
    val dateValidator = remember { DateValidator() }
    var dateError by remember { mutableStateOf<String?>(null) }

    // Formatear la fecha a dd/mm/aa
    fun formatFecha(input: String): String {
        if (input.length == 6) {
            val dia = input.substring(0, 2)
            val mes = input.substring(2, 4)
            val anio = input.substring(4, 6)
            return "$dia/$mes/$anio"
        }
        return input
    }
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ){
        OutlinedTextField(
            value = userDetails.name,
            label = { Text("Nombre", color = Color.DarkGray) },
            onValueChange = {
                onFormValueChange(formDetails.copy(idUser = userDetails.id))
                onUserValueChange(userDetails.copy(name = it))
                            },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
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
            label = { Text(stringResource(R.string.fecha), color = Color.DarkGray) },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
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
            label = { Text("Localidad", color = Color.DarkGray) },
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
            label = { Text("Hora", color = Color.DarkGray) },
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