package com.example.koadex.ViewModels

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import java.io.InputStream

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
    fun ZonaButton(text: String, isSelected: Boolean, iconRes: Int, onClick: () -> Unit) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier
                .size(80.dp)
                .aspectRatio(1.2f)
                .clip(RoundedCornerShape(8.dp)) // Esquinas redondeadas
                .border(
                    BorderStroke(2.dp, Color(0xFF97B96E)),
                    shape = RoundedCornerShape(8.dp) // Forma del borde
                )
                .background(if (isSelected) Color(0xFF97B96E) else Color.White)
                .padding(2.dp)
                .clickable(onClick = onClick)
        )
    }

    @Composable
    fun TipoAnimalButton(text: String, isSelected: Boolean, iconRes: Int, onClick: () -> Unit) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp)) // Esquinas redondeadas
                .border(
                    BorderStroke(2.dp, Color(0xFF97B96E)),
                    shape = RoundedCornerShape(8.dp) // Forma del borde
                )
                .background(if (isSelected) Color(0xFF97B96E) else Color.White)
                .padding(4.dp)
                .clickable(onClick = onClick)
        )
    }

    @Composable
    fun TipoObservacionButton(text: String, isSelected: Boolean, iconRes: Int, onClick: () -> Unit) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp)) // Esquinas redondeadas
                .border(
                    BorderStroke(2.dp, Color(0xFF97B96E)),
                    shape = RoundedCornerShape(8.dp) // Forma del borde
                )
                .background(if (isSelected) Color(0xFF97B96E) else Color.White)
                .padding(4.dp)
                .clickable(onClick = onClick)
        )
    }

    fun convertUriToByteArray(context: Context, uri: Uri): ByteArray? {
        var byteArray: ByteArray? = null
        try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            byteArray = inputStream?.readBytes()
            inputStream?.close()
        } catch (e: Exception) {
            e.printStackTrace()  // Manejar el error si ocurre
        }
        return byteArray
    }
}