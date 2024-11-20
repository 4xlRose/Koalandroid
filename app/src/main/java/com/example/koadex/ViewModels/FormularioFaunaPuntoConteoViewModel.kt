package com.example.koadex.ViewModels

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class FormularioFaunaPuntoConteoViewModel: ViewModel() {
    @Composable
    public fun AlturaButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
        Text(
            text = text,
            modifier = Modifier
                .background(if (isSelected) Color(0xFF97B96E) else Color.Transparent)
                .padding(8.dp)
                .clickable(onClick = onClick),
            color = if (isSelected) Color.Black else Color.Gray
        )
    }

    @Composable
    public fun ZonaButton(text: String, isSelected: Boolean, iconRes: Int, onClick: () -> Unit) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier
                .size(80.dp)
                .aspectRatio(1.2f)
                .background(if (isSelected) Color(0xFF97B96E) else Color.Transparent)
                .padding(2.dp)
                .clickable(onClick = onClick)
        )
    }

    @Composable
    public fun TipoAnimalButton(text: String, isSelected: Boolean, iconRes: Int, onClick: () -> Unit) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier
                .size(75.dp)
                .background(if (isSelected) Color(0xFF97B96E) else Color.Transparent)
                .padding(4.dp)
                .clickable(onClick = onClick)
        )
    }

    @Composable
    public fun TipoObservacionButton(text: String, isSelected: Boolean, iconRes: Int, onClick: () -> Unit) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier
                .size(64.dp)
                .background(if (isSelected) Color(0xFF97B96E) else Color.Transparent)
                .padding(4.dp)
                .clickable(onClick = onClick)
        )
    }
}