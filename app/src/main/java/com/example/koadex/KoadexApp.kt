package com.example.koadex

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.auth0.android.Auth0
import com.example.koadex.navigate.Navigation

@Composable
fun KoadexApp(modifier: Modifier = Modifier, account: Auth0, navController: NavHostController = rememberNavController()) {
    Navigation(account)
}