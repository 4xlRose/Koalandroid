package com.example.koadex.Views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.AppViewModelProvider
import com.example.koadex.R
import com.example.koadex.data.FormEntity
import com.example.koadex.navigate.La_navegacion
import com.example.koadex.ui.principal.KoadexViewModel
import com.example.koadex.ui.theme.Gray300
import com.example.koadex.ui.theme.Green100
import com.example.koadex.ui.theme.Green700
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Help
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import com.example.koadex.clases.User
import com.example.koadex.navigate.sampleUser

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=500dp,height=800dp")
@Composable
fun KoadexPreview() {
    var form_example = FormEntity(
        id = 1,
        name = "Juan",
        date = "12/03/2023",
        place = "Monterrey",
        hour = "12:00",
        weather = "Soleado",
        season = "Verano"
    )
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

        FormInfo(form_example)
    }

}

@Composable
fun Koadex(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: KoadexViewModel = viewModel(factory = AppViewModelProvider.Factory),
    user: User = sampleUser
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
            La_navegacion(navController, false, true, false)
        }

    ) {
            innerPadding ->
        KoadexPantalla(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .background(Color.White)
            ,
            viewModel
        )
    }

}

@Composable
fun KoadexPantalla(modifier: Modifier,
                   viewModel: KoadexViewModel,
) {
    val koadexUiState by viewModel.koadexUiState.collectAsState()
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center
    ) {

        KoadexContenido(formList = koadexUiState.koadexList)

    }
}

@Composable
fun KoadexContenido(
    formList: List<FormEntity>,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = Modifier
            .padding(bottom = 110.dp)
    ){
        var selected by remember { mutableStateOf("Todos") }

        Spacer(modifier = Modifier.height(110.dp))

        // Opciones de selección
        selected = Filtro_seleccion(selected)

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de formularios
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (formList.isEmpty()) {
                Text(
                    text = "No hay formularios guardados",
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                FormList(formList, modifier, selected)
            }
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
    formList: List<FormEntity>,
    modifier: Modifier = Modifier,
    filter: String = "Todos"
) {
    LazyColumn(modifier = modifier) {
        items(items = formList) { item ->
            FormInfo(
                form = item,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun FormInfo(
    form: FormEntity,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.verde_1)
        )
        ,
        onClick = {
            // Acción al hacer clic en el formulario
        }
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



                }
            }

            Spacer(modifier = Modifier.height(12.dp))


                Text(
                    text = Get_name(form),
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
    }
}

@Composable
private fun Get_name(form: FormEntity): String {
    // aca se implementa la funcionalidad de regresar un nombre en funcion de un id
    return form.name
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
@Composable
fun BottomNavBar() {
    var navBarSelect by remember { mutableStateOf("Búsqueda") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(Green100),
        verticalAlignment = Alignment.Top,
    ) {
        Row(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            val buttonSize = 40.dp
            val textSize = 15.sp

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(100.dp)
                    .clip(RoundedCornerShape(20.dp, 20.dp))
            ) {
                val text = "Inicio"
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = if (navBarSelect == text)
                        Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                    else
                        Modifier.background(Color.Transparent)
                ) {
                    IconToggleButton(
                        modifier = Modifier.size(buttonSize),
                        checked = false,
                        onCheckedChange = { navBarSelect = text }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = text,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                Text(text = text, fontSize = textSize)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(100.dp)
                    .clip(RoundedCornerShape(20.dp, 20.dp))
            ) {
                val text = "Búsqueda"
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = if (navBarSelect == text)
                        Modifier
                            .background(Color(0xB4D68F))
                            .fillMaxWidth()
                    else
                        Modifier.background(Color.Transparent)
                ) {
                    IconToggleButton(
                        modifier = Modifier.size(buttonSize),
                        checked = false,
                        onCheckedChange = { navBarSelect = text }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = text,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                Text(text = text, fontSize = textSize)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(100.dp)
                    .clip(RoundedCornerShape(20.dp, 20.dp))
            ) {
                val text = "Configuración"
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = if (navBarSelect == text)
                        Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                    else
                        Modifier.background(Color.Transparent)
                ) {
                    IconToggleButton(
                        modifier = Modifier.size(buttonSize),
                        checked = false,
                        onCheckedChange = { navBarSelect = text }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = text,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                Text(text = text, fontSize = textSize)
            }
        }
    }
}
*/