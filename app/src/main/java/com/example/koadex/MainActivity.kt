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
import com.example.koadex.Views.Botton_inicio_sesion
import com.example.koadex.Views.Fondo_vista
import com.example.koadex.Views.Intro_title
import com.example.koadex.navigate.Navigation
import com.example.koadex.ui.theme.KoadexTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import androidx.compose.material3.MaterialTheme
import com.auth0.android.Auth0

class MainActivity : ComponentActivity() {
    private lateinit var account: Auth0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        account = Auth0.getInstance(
            "HQjd9ogRdlwoP9eq1S2msaTmM925icR0",
            "dev-qk6i0qseg2txot3r.us.auth0.com"
        )
        enableEdgeToEdge()
        setContent {
            KoadexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    KoadexApp(
                        modifier = Modifier.padding(innerPadding),
                        account = account
                    )

                }
            }
        }
    }


}
