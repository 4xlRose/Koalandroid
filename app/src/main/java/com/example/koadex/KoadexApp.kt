package com.example.koadex

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.auth0.android.Auth0
import com.example.koadex.navigate.Navigation

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun KoadexApp(activity: MainActivity, modifier: Modifier = Modifier, account: Auth0) {
    Navigation(activity,account)
}