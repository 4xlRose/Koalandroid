package com.example.koadex.Views

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
import androidx.compose.ui.tooling.preview.Preview
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.rememberNavController
import com.example.koadex.AppViewModelProvider
import com.example.koadex.clases.User
import com.example.koadex.data.FormStateEntity
import com.example.koadex.data.GeneralFormEntity
import com.example.koadex.data.UserEntity
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


@Composable
fun Koadex(
    navController: NavHostController,
    modifier: Modifier = Modifier,

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
            viewModel,
            navController

        )
    }

}

@Composable
/*Hugo
fun KoadexPantalla(modifier: Modifier,
                   viewModel: KoadexViewModel
) {
    val koadexUiState by viewModel.koadexUiState.collectAsState()
    val userList by viewModel.getAllUsers.collectAsState(initial = null)
    val formStates by viewModel.getAllFormStates.collectAsState(initial = null)
*/
fun KoadexPantalla(
    modifier: Modifier,
    viewModel: KoadexViewModel,
    navController: NavHostController

) {
    val koadexUiState by viewModel.koadexUiState.collectAsState()
    val koadexGeneralUiState by viewModel.koadexGeneralUiState.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        KoadexContenido(
            //formList = koadexUiState.koadexList,
/*Hugo
            userList = userList,
            formStates = formStates,
        )
*/
            General_formList = koadexGeneralUiState.koadexGeneralList,
            navController,
            modifier,

        )
    }
}

@Composable
fun KoadexContenido(
/*Hugo
    formList: List<GeneralFormEntity>,
    userList: List<UserEntity>?,
    formStates: List<FormStateEntity>?,
    modifier: Modifier = Modifier
*/
    //formList: List<FormEntity>,
    General_formList: List<GeneralFormEntity> = listOf(),
    navController: NavHostController,
    modifier: Modifier = Modifier,

) {
    Column (
        modifier = Modifier
            //.padding(bottom = 24.dp)
    ){
        var selected by remember { mutableStateOf("Todos") }
        var forms_number : Int = General_formList.size

        Spacer(modifier = Modifier.height(110.dp))

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
/*Hugo
            if (formList.isEmpty()) {
                Text(
                    text = "No hay formularios guardados",
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                FormList(
                    formList = formList,
                    userList = userList,
                    formStates = formStates,
                    modifier = modifier,
                    filter = selected
                )
*/
            if (forms_number == 0) {
                No_forms(General_formList, navController)
            } else {
                FormList(
                    //formList = formList,
                    filter = selected,
                    new_formList = General_formList)
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
            .fillMaxHeight(0.7f)
            .padding(16.dp),
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
    //userList: List<UserEntity>?,
    //formStates: List<FormStateEntity>?,
    modifier: Modifier = Modifier,
    filter: String = "Todos",
    new_formList: List<GeneralFormEntity> = listOf()
) {
/*Hugo
    LazyColumn(modifier = modifier) {
        items(items = formList) { item ->
            val user = userList?.find { it.id == item.idUser }
            val state = formStates?.find { it.idGeneralForm == item.id }

            val cardShowing = when (filter) {
                "Todos" -> true
                "Guardados" -> item.id == state?.idGeneralForm && user?.id == state.idUser && !state.isUploaded
                "Subidos" -> item.id == state?.idGeneralForm && user?.id == state.idUser && state.isUploaded
                else -> false
            }

            FormInfo(
                form = item,
                user = user,
                cardShowing = cardShowing
            )
        }
    }
}
*/
    // ejemplos de formularios
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
    )

     */

    Column(
        modifier = Modifier
            .padding(bottom = 100.dp)
    ){
    for (current_form in new_formList) {
        FormInfo(
            //form_old = form_example2
            new_form = current_form
        )
    }
    }

/*Hugo
@Composable
fun FormInfo(
    form: GeneralFormEntity,
    user: UserEntity?,
    cardShowing: Boolean,
    modifier: Modifier = Modifier
) {
    if(cardShowing) {
*/

}

@Composable
fun FormInfo(
    //form_old: FormEntity,
    new_form: GeneralFormEntity,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    val form = new_form

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
                // ID, hora y botón de edición
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        //Hugo
                        //text = "ID: ${form.id}",

                        text = "ID: ${Get_id(form)}",
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
/*Hugo
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

                        IconButton(
                            onClick = { /* Acción de edición */ },
                            modifier = Modifier.size(29.dp)
*/
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
                                    text = Get_hour(form),
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            }
                        }
                        IconButton(
                            onClick = { /* Acción de edición */ },
                            modifier = Modifier.size(29.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Borrar",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        IconButton(
                            onClick = { /* Acción de edición */ },
                            modifier = Modifier.size(29.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
/*Hugo


                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Nombre del formulario
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
                            text = form.place,
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
*/
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = Get_name2(form),
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
                            text = Get_place(form),
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
                            text = Get_date(form),
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

@Composable
fun resumen_Formulario(form: GeneralFormEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp),
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

            ResumenItem("Temporada:", Get_season(form))
            ResumenItem("Clima:", Get_weather(form))
            ResumenItem("Fecha:", Get_date(form))
            ResumenItem("Hora:", Get_hour(form))
            ResumenItem("Ubicación:", Get_place(form))
        }
    }
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
@Composable
fun Get_id(form: GeneralFormEntity): Int {
    return form.id
}
@Composable
fun Get_name2(form: GeneralFormEntity): String {
    // aca se implementa la funcionalidad de regresar un nombre en funcion de un id
    return "Juan"
}

@Composable
fun Get_date(form: GeneralFormEntity): String {
    return form.date
}

@Composable
fun Get_hour(form: GeneralFormEntity): String {
    return form.hour
}

@Composable
fun Get_place(form: GeneralFormEntity): String {
    return form.place
}

@Composable
fun Get_weather(form: GeneralFormEntity): String {
    val nuber = form.idWeather

    val diccionario = mapOf(
        1 to "Soleado",
        2 to "Nublado",
        3 to "Lluvioso"
    )
    return diccionario[nuber] ?: "Sin registro de clima"
}

@Composable
fun Get_season(form: GeneralFormEntity): String {
    val nuber = form.idSeason

    val diccionario = mapOf(
        1 to "Verano",
        2 to "Primavera",
        3 to "Invierno",
        4 to "Otoño"
    )
    return diccionario[nuber] ?: "Sin registro de temporada"
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
