package com.example.koadex.Views

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.koadex.R
import com.example.koadex.navigate.La_navegacion
import com.example.koadex.ui.theme.Gray300
import com.example.koadex.ui.theme.Green700
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Help
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import androidx.navigation.compose.rememberNavController
import com.example.koadex.AppViewModelProvider
import com.example.koadex.ViewModels.NavigationModel
import com.example.koadex.data.FollowUpFormEntity
import com.example.koadex.data.FormStateEntity
import com.example.koadex.data.GeneralFormEntity
import com.example.koadex.data.QuadrantFormEntity
import com.example.koadex.data.RouteFormEntity
import com.example.koadex.data.SpecieFormEntity
import com.example.koadex.data.UserEntity
import com.example.koadex.data.WeatherFormEntity
import com.example.koadex.navigate.sampleUser
import com.example.koadex.ui.principal.KoadexViewModel

/*
@Preview(showBackground = true, showSystemUi = true)
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.rememberNavController
import com.example.koadex.clases.User
import com.example.koadex.navigate.sampleUser
/*
*
*  koalandroid@tec.mx
*  KoalAndroid*2025
*
* */
|
 */
/*
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=500dp,height=800dp")
@Composable
fun KoadexPreview() {
    var form_example = GeneralFormEntity(
        id = 1,
        date = "2023-07-01",
        hour = "12:00:00",
        idUser = 1,
        idWeather = 1,
        idSeason = 1,
        place = "Ciudad de México"
    )
    /*var form_example2 = FormEntity(
        id = 1,
        name = "Juan",
        date = "2023-07-01",
        place = "Ciudad de México",
        hour = "12:00:00",
        weather = "Soleado",
        season = "Verano"
    )*/

    var formList = listOf(form_example)

    var selected by remember { mutableStateOf("Todos") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        TopNavBar(rememberNavController())
        selected = Filtro_seleccion(selected)

        FormInfo(
            //form_old = form_example2,
            new_form = form_example)
    }

}
*/

@Composable
fun Koadex(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    navModel: NavigationModel,
    viewModel: KoadexViewModel = viewModel(factory = AppViewModelProvider.Factory),
    user: UserEntity = sampleUser

) {
    var current_scroll by remember { mutableStateOf(0f) }

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
        ,
        topBar = {
            TopNavBar(navController)
        },
        bottomBar = {
            La_navegacion(navController, firstSelected = false, secondSelected = true, thirdSelected = false)
        }

    ) {
            innerPadding ->
        KoadexPantalla(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .background(Color.White),
            viewModel = viewModel,
            navController = navController,
            user = user,
            navModel = navModel

        )
    }

}

@Composable
fun KoadexPantalla(
    modifier: Modifier,
    viewModel: KoadexViewModel,
    navController: NavHostController,
    user: UserEntity,
    navModel: NavigationModel
) {
    val koadexGeneralUiState by viewModel.getAllForms.collectAsState()
    val userList by viewModel.getAllUsers.collectAsState(initial = null)
    val formStates by viewModel.getAllFormStates.collectAsState(initial = null)

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        KoadexContenido(
            //formList = koadexUiState.koadexList,
            userList = userList,
            currentUser = user,
            formStates = formStates,
            General_formList = koadexGeneralUiState.koadexGeneralList,
            navController = navController,
            modifier = modifier,
            viewModel = viewModel,
            navModel = navModel
        )
    }
}

@Composable
fun KoadexContenido(
    //formList: List<FormEntity>,
    userList: List<UserEntity>?,
    formStates: List<FormStateEntity>?,
    General_formList: List<GeneralFormEntity> = listOf(),
    navController: NavHostController,
    modifier: Modifier = Modifier,
    currentUser: UserEntity,
    viewModel: KoadexViewModel,
    navModel: NavigationModel
) {
    Column (
        modifier = Modifier
            //.padding(bottom = 24.dp)
    ){
        var selected by remember { mutableStateOf("Todos") }
        val forms_number : Int = General_formList.size

        Spacer(modifier = Modifier.height(110.dp))
        //Text(user.name, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        // Opciones de selección
        selected = Filtro_seleccion(selected)

        //Spacer(modifier = Modifier.height(16.dp))

        // Lista de formularios
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (forms_number == 0) {
                No_forms(General_formList, navController)
            } else {
                FormList(
                    //formList = formList,
                    navController = navController,
                    filter = selected,
                    new_formList = General_formList,
                    userList = userList,
                    currentUser = currentUser,
                    formStates = formStates,
                    viewModel = viewModel,
                    navModel = navModel
                )
            }
        }
    }
}

// Funcion para la pantalla sin formularios
// (solo es un boton que lleva a la pantalla de formulario general)
@Composable
private fun No_forms(
    form_list: List<GeneralFormEntity> = listOf(),
    navigation: NavHostController = rememberNavController()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)

        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Círculo con icono
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(
                    color = colorResource(R.color.verde_1).copy(alpha = 0.1f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "No forms icon",
                modifier = Modifier.size(40.dp),
                tint = colorResource(R.color.verde_1)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Título
        Text(
            text = "No hay formularios guardados",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Mensaje descriptivo
        Text(
            text = "Comienza creando un nuevo formulario para ver tus registros aquí",
            style = TextStyle(
                fontSize = 16.sp,
                color = Gray300
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón de acción
        Button(
            onClick = {
                navigation.navigate("FormularioGeneral")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.verde_1)
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(48.dp)
        ) {
            Text(
                text = "Crear Formulario",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

/*
* Funcion para la barra de filtros
*/
@Composable
private fun Filtro_seleccion(selected: String): String {
    var selected1 by remember { mutableStateOf(selected) } // Remember the selected
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val options = listOf("Todos", "Guardados", "Subidos")

            options.forEach { option ->
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (selected1 == option) Green700 else Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Button(
                        onClick = { selected1 = option },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = option,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (selected1 == option) Color.White else Gray300,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
    return selected1
}


/*
*  Funcion para la lista de formularios y las tarjetas
*/
@Composable
fun FormList(
    //formList: List<GeneralFormEntity>,
    navController: NavHostController,
    userList: List<UserEntity>?,
    currentUser: UserEntity,
    formStates: List<FormStateEntity>?,
    filter: String = "Todos",
    new_formList: List<GeneralFormEntity> = listOf(),
    viewModel: KoadexViewModel,
    navModel: NavigationModel
) {
    Column (
        modifier = Modifier
            .padding(bottom = 100.dp)
    ) {
        new_formList.forEach(action = { item ->
            val user = userList?.find { it.id == item.idUser }
            val state = formStates?.find { it.idGeneralForm == item.id }

            val cardShowing = when (filter) {
                "Todos" -> true
                "Guardados" -> item.id == state?.idGeneralForm && user?.id == state.idUser && !state.isUploaded
                "Subidos" -> item.id == state?.idGeneralForm && user?.id == state.idUser && state.isUploaded
                else -> false
            }

            FormInfo(
                navController = navController,
                new_form = item,
                user = user,
                loggedUser = currentUser,
                cardShowing = cardShowing,
                viewModel = viewModel,
                navModel = navModel
            )
        })
    }
}


@Composable
fun FormInfo(
    //form_old: FormEntity,
    navController: NavHostController,
    new_form: GeneralFormEntity,
    user: UserEntity? = sampleUser,
    loggedUser: UserEntity,
    modifier: Modifier = Modifier,
    cardShowing: Boolean,
    viewModel: KoadexViewModel,
    navModel: NavigationModel
) {
    LaunchedEffect(Unit) {
        navModel.savedFormId = 0
    }
    val showDeleteWarning = remember { mutableStateOf(false) }

    if (cardShowing) {
        var isExpanded by remember { mutableStateOf(false) }
        val form = new_form

        val stateFlow = viewModel.getFormStateByFormId(form.id)
        val state = stateFlow.collectAsState(initial = null).value?.isUploaded

        if (showDeleteWarning.value == true) {
            deleteFormWarning(
                onConfirmDelete = {
                    if (state == true) {
                        user?.uploadedForms = user?.uploadedForms?.minus(1) ?: 0
                    }
                    else {
                        user?.locallyStoredForms = user?.locallyStoredForms?.minus(1) ?: 0
                    }
                    viewModel.updateUser(user ?: sampleUser)
                    viewModel.deleteForm(form)
                    isExpanded = !isExpanded
                    showDeleteWarning.value = false
                },
                onDismiss = {
                    showDeleteWarning.value = false
                }
            )
        }
        Column {
            Card(
                modifier = modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.verde_1)
                ),
                onClick = { isExpanded = !isExpanded }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // ID, hora y botón de edición y eliminación
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "ID: ${form.id}",
                            color = colorResource(R.color.green_100),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Hora
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = colorResource(R.color.verde_oscuro_1)
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Schedule,
                                        contentDescription = "Clock Icon",
                                        tint = Color.White,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = form.hour,
                                        color = Color.White,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                            // Botones de edición y eliminación
                            if (form.idUser == loggedUser.id) {
                                IconButton( // Borrar
                                    onClick = {
                                        showDeleteWarning.value = true
                                    },
                                    modifier = Modifier.size(29.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Borrar",
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                IconButton( // Editar
                                    onClick = {
                                        navModel.savedFormId = form.id
                                        navController.navigate("FormularioGeneral")
                                              },
                                    modifier = Modifier.size(29.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Editar",
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = user?.name ?: "Desconocido",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Lugar y fecha
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Lugar
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Location",
                                tint = colorResource(R.color.green_100),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Tomada desde el dispositivo. ${form.place}",
                                color = colorResource(R.color.green_100),
                                fontSize = 14.sp,
                                maxLines = 1
                            )
                        }

                        // Fecha
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = colorResource(R.color.green_100)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = form.date,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                color = colorResource(R.color.verde_oscuro_1),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            // Resumen expandible
            if (isExpanded) {
                resumen_Formulario(form)
            }
        }
    }
}

@Composable
fun resumen_Formulario(
    form: GeneralFormEntity,
    model: KoadexViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {

    // valores de prueba
    var SpecireFormEntity = SpecieFormEntity(
        id = 1,
        transect = "Transecto 1",
        idZoneType = 1,
        idAnimalType = 1,
        animalName = "León",
        scientificName = "Panthera leo",
        quantity = 10,
        idObservType = 1,
        idHeightType = 1,
        observations = "Observaciones"
    )
    var FollowUpFormEntity = FollowUpFormEntity(
        id = 1,
        followUp = true,
        change = true,
        idCoverage = 1,
        cropType = "Tipo de cultivo",
        idDisturbance = 1,
        observations = "Observaciones"
    )
    var QuadrantFormEntity = QuadrantFormEntity (
        id = 1,
        idSuperQuadrant = 1,
        idMidQuadrant = 1,
        idSubQuadrant = 1,
        specieName = "Nombre de la especie",
        scientificName = "Nombre científico",
        idHabitat = 1,
        circumference = 10,
        biomonitorMtSize = 10,
        distanceMt = 10,
        observations = "Observaciones",
        heightMt = 10,
    )
    var RouteFormEntity = RouteFormEntity (
        id = 1,
        idZoneType = 1,
        guayaPlate = 1,
        routeWidth = 10,
        targetDistance = 10,
        lensHeight = 10,
        idCheckList = 1,
        observations = "Observaciones"
    )
    var WeatherFormEntity = WeatherFormEntity (
        id = 1,
        idZoneType = 1,
        rainfall = 1.5,
        maxTemperature = 25.0,
        maxHumidity = 80.0,
        minTemperature = 18.0,
        minHumidity = 60.0,
        streamMtLevel = 1.0,
        observations = "Observaciones"
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp)
            .offset(y = (-20).dp)
            .zIndex(zIndex = -1f)
        ,
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.verde_oscuro_1)
        ),
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Resumen del Formulario",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Divider(
                color = colorResource(R.color.green_100),
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            val season = model.getSeasonById(form.idSeason).collectAsState(initial = null)
            season.value?.let {
                ResumenItem("Temporada:", it.type)
            }

            val weather = model.getWeatherById(form.idWeather).collectAsState(initial = null)
            weather.value?.let {
                ResumenItem("Clima:", it.type)
            }

            ResumenItem("Lugar:", "Tomada desde el dispositivo. ${form.place}")
            ResumenItem("Fecha:", form.date)
            ResumenItem("Hora:", form.hour)

            Divider(
                color = colorResource(R.color.green_100),
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            if(form.idSpecieForm == 0){
                var form_detail = SpecireFormEntity
                show_resumen_especies(form_detail)
            }
            if(form.idFollowUpForm != 0){
                var form_detail = FollowUpFormEntity
                show_resumen_seguimiento(form_detail)
            }
            if(form.idQuadrantForm != 0){
                var form_detail = QuadrantFormEntity
                show_resumen_cuadrantes(form_detail)
            }
            if(form.idRouteForm != 0){
                var form_detail = RouteFormEntity
                show_resumen_ruta(form_detail)
            }
            if(form.idWeatherForm != 0){
                var form_detail = WeatherFormEntity
                show_resumen_clima(form_detail)
            }
        }
    }
}

@Composable
fun deleteFormWarning(
    onConfirmDelete: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Eliminar Formulario",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },
        text = {
            Text(
                text = "¿Estás seguro de que quieres eliminar este formulario? Esta acción no se puede deshacer.",
                color = Gray300
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmDelete()
                    onDismiss()
                },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.Red
                )
            ) {
                Text("Eliminar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Cancelar")
            }
        },
        containerColor = Color.White,
        iconContentColor = colorResource(R.color.verde_1),
        textContentColor = Color.Black
    )
}
@Composable
private fun ResumenItem(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = colorResource(R.color.green_100),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

            Text(
                text = value,
                color = Color.White,
                fontSize = 14.sp
            )


    }
}

/*
* Funciones para la barra de navegacion
*/
@Composable
fun TopNavBar(navController: NavHostController) {
    val context = LocalContext.current.applicationContext
    val verde_1 = Color(colorResource(R.color.verde_1).toArgb())

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(verde_1)
    ) {


        // Contenido de la barra de navegación
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Botón Atrás
            IconButton(
                onClick = { navController.navigate("Principal") },
                modifier = Modifier
                    .size(40.dp)

            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atrás",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Logo y título centrales
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(8.dp)
            ) {
                // Logo con sombra y animación
                Card(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                    ,

                ) {
                    Image(
                        painter = painterResource(R.drawable.koadex),
                        contentDescription = "Koadex Logo",
                        modifier = Modifier
                            .fillMaxSize()

                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Título con estilo mejorado
                Text(
                    text = "Koadex",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            // Menú de opciones con diseño mejorado
            Box() {
                var showMenu by remember { mutableStateOf(false) }

                showMenu = Dropdown_(showMenu)
            }
        }
    }
}

/*
* Funciones para el menu de 3 botones
*/
@Composable
private fun Dropdown_(showMenu: Boolean): Boolean {
    var showMenu1 by remember { mutableStateOf(showMenu) }
    IconButton(
        onClick = { showMenu1 = true },
        modifier = Modifier
            .size(40.dp)


    ) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "Más opciones",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }

    DropdownMenu(
        expanded = showMenu1,
        onDismissRequest = { showMenu1 = false },
        modifier = Modifier
            .background(Color.White)
            .width(200.dp)
    ) {
        MenuOption(
            text = "Configuración",
            icon = Icons.Default.Settings
        ) {
            showMenu1 = false
            // Implementar navegación a configuración
        }

        MenuOption(
            text = "Compartir",
            icon = Icons.Default.Share
        ) {
            showMenu1 = false
            // Implementar función de compartir
        }

        MenuOption(
            text = "Ayuda",
            icon = Icons.Default.Help
        ) {
            showMenu1 = false
            // Implementar navegación a ayuda
        }
    }
    return showMenu1
}

@Composable
private fun MenuOption(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    DropdownMenuItem(
        text = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = colorResource(R.color.verde_1),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = text,
                    color = colorResource(R.color.verde_1),
                    fontSize = 16.sp
                )
            }
        },
        onClick = onClick,
        modifier = Modifier.padding(horizontal = 8.dp)
    )
}

/*
* Funciones para mostrar el detalle de los formularios
*/
@Composable
fun show_resumen_especies(form_detail: SpecieFormEntity) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Detalles de Especies",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Divider(
            color = colorResource(R.color.green_100),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        ResumenItem("Transecto:", form_detail.transect)
        ResumenItem("Nombre Animal:", form_detail.animalName)
        ResumenItem("Nombre Científico:", form_detail.scientificName)
        ResumenItem("Cantidad:", form_detail.quantity.toString())

        if (form_detail.observations.isNotBlank()) {
            ResumenItem("Observaciones:", form_detail.observations)
        }
    }
}

@Composable
fun show_resumen_seguimiento(form_detail: FollowUpFormEntity) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Detalles de Seguimiento",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Divider(
            color = colorResource(R.color.green_100),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        ResumenItem("Seguimiento:", if (form_detail.followUp) "Sí" else "No")
        ResumenItem("Cambio:", if (form_detail.change) "Sí" else "No")
        ResumenItem("Tipo de Cultivo:", form_detail.cropType)

        if (form_detail.observations.isNotBlank()) {
            ResumenItem("Observaciones:", form_detail.observations)
        }
    }
}

@Composable
fun show_resumen_cuadrantes(form_detail: QuadrantFormEntity) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Detalles de Cuadrantes",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Divider(
            color = colorResource(R.color.green_100),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        ResumenItem("Nombre Especie:", form_detail.specieName)
        ResumenItem("Nombre Científico:", form_detail.scientificName)
        ResumenItem("Circunferencia:", "${form_detail.circumference} m")
        ResumenItem("Distancia:", "${form_detail.distanceMt} m")
        ResumenItem("Altura:", "${form_detail.heightMt} m")

        if (form_detail.observations.isNotBlank()) {
            ResumenItem("Observaciones:", form_detail.observations)
        }
    }
}

@Composable
fun show_resumen_ruta(form_detail: RouteFormEntity) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Detalles de Ruta",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Divider(
            color = colorResource(R.color.green_100),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        ResumenItem("Placa Guaya:", form_detail.guayaPlate.toString())
        ResumenItem("Ancho de Ruta:", "${form_detail.routeWidth} m")
        ResumenItem("Distancia Objetivo:", "${form_detail.targetDistance} m")
        ResumenItem("Altura de Lente:", "${form_detail.lensHeight} m")

        if (form_detail.observations.isNotBlank()) {
            ResumenItem("Observaciones:", form_detail.observations)
        }
    }
}

@Composable
fun show_resumen_clima(form_detail: WeatherFormEntity) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Detalles de Clima",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Divider(
            color = colorResource(R.color.green_100),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        ResumenItem("Precipitación:", "${form_detail.rainfall} mm")
        ResumenItem("Temperatura Máxima:", "${form_detail.maxTemperature}°C")
        ResumenItem("Temperatura Mínima:", "${form_detail.minTemperature}°C")
        ResumenItem("Humedad Máxima:", "${form_detail.maxHumidity}%")
        ResumenItem("Humedad Mínima:", "${form_detail.minHumidity}%")
        ResumenItem("Nivel de Corriente:", "${form_detail.streamMtLevel} m")

        if (form_detail.observations.isNotBlank()) {
            ResumenItem("Observaciones:", form_detail.observations)
        }
    }
}
