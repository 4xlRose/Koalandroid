package com.example.koadex.Views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
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

@Composable
fun FormularioGeneral(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, top = 50.dp, end = 16.dp, bottom = 16.dp)
            .background(Color.White)
    ) {
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
            Button(
                contentPadding = PaddingValues(
                    horizontal = 3.dp,
                    vertical = 3.dp
                ),
                modifier = Modifier
                    .offset(x = 5.dp)
                    .size(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0),
                    contentColor = Color(0xFF000000)
                ),
                onClick = {
                    navController.navigate("Principal")
                }
            ) {
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

        var nombre by remember { mutableStateOf("") }
        OutlinedTextField(
            value = "",
            label = { Text("Nombre") },
            onValueChange = { nombre = it },
            modifier = Modifier
                .padding(10.dp)
                .width(320.dp)
        )

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        )
        {
            var fecha by remember { mutableStateOf("") }
            OutlinedTextField(
                value = "",
                label = { Text("Fecha") },
                onValueChange = { fecha = it },
                modifier = Modifier
                    .width(180.dp)
                    .offset(26.dp)
            )
            Button(
                contentPadding = PaddingValues(
                    horizontal = 3.dp,
                    vertical = 3.dp
                ),
                modifier = Modifier
                    .offset(x = 40.dp, y = 3.dp)
                    .size(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0),
                    contentColor = Color(0xFF000000)
                ),
                onClick = {}
            ) {
                Icon(
                    Icons.Rounded.DateRange,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        )
        {
            var localidad by remember { mutableStateOf("") }
            OutlinedTextField(
                value = "",
                label = { Text("Localidad") },
                onValueChange = { localidad = it },
                modifier = Modifier
                    .width(262.dp)
                    .offset(26.dp)
            )
            Button(
                contentPadding = PaddingValues(
                    horizontal = 6.dp,
                    vertical = 6.dp
                ),
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                    .offset(x = 40.dp, y = 3.dp)
                    .size(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4E7029)
                ),
                onClick = {}
            ) {
                Icon(
                    Icons.Rounded.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        var hora by remember { mutableStateOf("") }
        OutlinedTextField(
            value = "",
            label = { Text("Hora") },
            onValueChange = { hora = it },
            modifier = Modifier
                .padding(10.dp)
                .width(320.dp)
        )

        Row(
            modifier = textModifier,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "Estado del Tiempo",
                modifier = modifier
                    .offset(x = 30.dp),
                fontSize = 18.sp
            )
        }

        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            var state by remember { mutableStateOf("nublado") }
            val buttonSize = 80.dp
            OutlinedIconToggleButton(
                checked = false,
                onCheckedChange = {
                    state = "soleado"
                },
                shape = RoundedCornerShape(12.dp),
                colors = IconButtonDefaults.iconToggleButtonColors(
                    containerColor = if (state == "soleado") Color(0xFFCDE4B4) else Color.White
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (state == "soleado") Color(0xFF4E7029) else Color.Black
                ),
                modifier = Modifier
                    .size(buttonSize)
            ) {
                Image(
                    painter = painterResource(R.drawable.soleado),
                    contentDescription = null
                )
            }
            OutlinedIconToggleButton(
                checked = false,
                onCheckedChange = {
                    state = "nublado"
                },
                shape = RoundedCornerShape(12.dp),
                colors = IconButtonDefaults.iconToggleButtonColors(
                    containerColor = if (state == "nublado") Color(0xFFCDE4B4) else Color.White
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (state == "nublado") Color(0xFF4E7029) else Color.Black
                ),
                modifier = Modifier
                    .size(buttonSize)
            ) {
                Image(
                    painter = painterResource(R.drawable.nublado),
                    contentDescription = null
                )
            }
            OutlinedIconToggleButton(
                checked = false,
                onCheckedChange = {
                    state = "lluvioso"
                },
                shape = RoundedCornerShape(12.dp),
                colors = IconButtonDefaults.iconToggleButtonColors(
                    containerColor = if (state == "lluvioso") Color(0xFFCDE4B4) else Color.White
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (state == "lluvioso") Color(0xFF4E7029) else Color.Black
                ),
                modifier = Modifier
                    .size(buttonSize)
            ) {
                Image(
                    painter = painterResource(R.drawable.lluvioso),
                    contentDescription = null
                )
            }
        }

        Row(
            modifier = textModifier,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "Época",
                modifier = modifier
                    .offset(x = 30.dp),
                fontSize = 18.sp
            )
        }

        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            var state by remember { mutableStateOf("") }
            val buttonSize = 80.dp
            OutlinedIconToggleButton(
                checked = false,
                onCheckedChange = {
                    state = "verano"
                },
                shape = RoundedCornerShape(12.dp),
                colors = IconButtonDefaults.iconToggleButtonColors(
                    containerColor = if (state == "verano") Color(0xFFCDE4B4) else Color.White
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (state == "verano") Color(0xFF4E7029) else Color.Black
                ),
                modifier = Modifier
                    .size(buttonSize)
            ) {
                Image(
                    painter = painterResource(R.drawable.verano),
                    contentDescription = null
                )
            }
            OutlinedIconToggleButton(
                checked = false,
                onCheckedChange = {
                    state = "invierno"
                },
                shape = RoundedCornerShape(12.dp),
                colors = IconButtonDefaults.iconToggleButtonColors(
                    containerColor = if (state == "invierno") Color(0xFFCDE4B4) else Color.White
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (state == "invierno") Color(0xFF4E7029) else Color.Black
                ),
                modifier = Modifier
                    .size(buttonSize)
            ) {
                Image(
                    painter = painterResource(R.drawable.invierno),
                    contentDescription = null
                )
            }
        }

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
                onClick = {}
            ) {
                Text("SIGUIENTE", fontWeight = FontWeight.Bold)
            }
        }

    }
}