package com.example.koadex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.koadex.navigate.ControladorNavegador
import com.example.koadex.ui.theme.KoadexTheme


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun Navigation(modifier: Modifier = Modifier) {
    ControladorNavegador()
}

