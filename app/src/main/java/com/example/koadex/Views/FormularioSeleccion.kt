package com.example.koadex.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.koadex.R

@Composable
fun FormularioSeleccion(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text= "Tipo de Registro")
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
                onClick = {  }
            ) {
                Text("Fauna en Transectos", fontWeight = FontWeight.Bold)
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
                onClick = {  }/*onSaveClick,
                enabled = formUiState.isEntryValid*/
            ) {
                Text("Fauna en Punto de Conteo", fontWeight = FontWeight.Bold)
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
                onClick = { navController.navigate("FormularioSeleccion") }/*onSaveClick,
                enabled = formUiState.isEntryValid*/
            ) {
                Text("Fauna Búsqueda Libre", fontWeight = FontWeight.Bold)
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
                onClick = { navController.navigate("FormularioSeleccion") }/*onSaveClick,
                enabled = formUiState.isEntryValid*/
            ) {
                Text("Validación de Cobertura", fontWeight = FontWeight.Bold)
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
                onClick = {  }/*onSaveClick,
                enabled = formUiState.isEntryValid*/
            ) {
                Text("Parcela de Vegetación", fontWeight = FontWeight.Bold)
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
                onClick = { navController.navigate("CamarasTrampa") }/*onSaveClick,
                enabled = formUiState.isEntryValid*/
            ) {
                Text("Cámaras Trampa", fontWeight = FontWeight.Bold)
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
                onClick = { }/*onSaveClick,
                enabled = formUiState.isEntryValid*/
            ) {
                Text("Variables Climáticas", fontWeight = FontWeight.Bold)
            }
            }
    }
}

@Preview(showBackground = true)
@Composable
fun previewFormularioSeleccion() {
    FormularioSeleccion(navController = NavHostController(LocalContext.current))
}