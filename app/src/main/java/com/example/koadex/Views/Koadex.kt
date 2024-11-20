package com.example.koadex.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
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
import com.example.koadex.navigate.La_navegacion
import com.example.koadex.ui.principal.KoadexViewModel
import com.example.koadex.ui.theme.Gray300
import com.example.koadex.ui.theme.Green100
import com.example.koadex.ui.theme.Green700

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun KoadexPreview() {
    Koadex(
        navController = rememberNavController(),
        modifier = Modifier,

    )
}

@Composable
fun Koadex(
    navController: NavHostController,
    modifier: Modifier = Modifier,

) {
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

        )
    }

}

@Composable
fun KoadexPantalla(modifier: Modifier,

) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        KoadexContenido()

    }
}

@Composable
fun KoadexContenido(
    modifier: Modifier = Modifier
)
{
    Column (
        //modifier = Modifier

    ){
        Spacer(modifier = Modifier.height(90.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val textSize = 20.sp
            var selected by remember { mutableStateOf("Todos") }

            Column {
                val text = "Todos"
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    onClick = { selected = text }
                ) {
                    Text(
                        text = text,
                        fontSize = textSize,
                        fontWeight = FontWeight.Bold,
                        textDecoration = if (selected == text)
                            TextDecoration.Underline
                        else
                            TextDecoration.None,
                        color = if (selected == text) Green700 else Gray300
                    )

                }
            }

            Column {
                val text = "Guardados"
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    onClick = { selected = text }
                ) {
                    Text(
                        text = text,
                        fontSize = textSize,
                        fontWeight = FontWeight.Bold,
                        textDecoration = if (selected == text)
                            TextDecoration.Underline
                        else
                            TextDecoration.None,
                        color = if (selected == text) Green700 else Gray300
                    )
                }
            }

            Column {
                val text = "Subidos"
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    onClick = { selected = text }
                ) {
                    Text(
                        text = text,
                        fontSize = textSize,
                        fontWeight = FontWeight.Bold,
                        textDecoration = if (selected == text)
                            TextDecoration.Underline
                        else
                            TextDecoration.None,
                        color = if (selected == text) Green700 else Gray300
                    )
                }
            }
        }

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {


                Text(
                    text = "No hay formularios guardados",
                    modifier = Modifier.fillMaxSize()
                )

        }
    }

}

@Composable
fun TopNavBar(navController: NavHostController) {

    val context = LocalContext.current.applicationContext
    val verde_1 = Color(colorResource(R.color.verde_1).toArgb())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(verde_1)

        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(verde_1)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                modifier = Modifier
                    .padding(20.dp)
                    .size(30.dp),
                onClick = { navController.navigate("Principal") }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .fillMaxSize()

                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .width(170.dp)
            ) {
                Button(
                    modifier = Modifier.size(50.dp),
                    contentPadding = PaddingValues(
                        horizontal = 0.dp,
                        vertical = 0.dp
                    ),
                    onClick = { Toast.makeText(context, "Koadex", Toast.LENGTH_SHORT).show() }
                ) {
                    Image(
                        painter = painterResource(R.drawable.koadex),
                        contentDescription = "Koadex Logo"
                    )
                }

                Text(text = "Koadex", fontSize = 30.sp, fontWeight = FontWeight.Medium, color = Color.White)
            }
            IconButton(
                modifier = Modifier
                    .padding(20.dp)
                    .size(30.dp),
                onClick = { Toast.makeText(context, "Koadex", Toast.LENGTH_SHORT).show() }
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More",
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

}

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
