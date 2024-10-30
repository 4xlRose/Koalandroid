package com.example.koadex

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.koadex.navigate.Navigation

@Composable
fun KoadexApp(modifier: Modifier = Modifier) {
    Navigation()
}