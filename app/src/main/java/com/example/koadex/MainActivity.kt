package com.example.koadex
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
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
import androidx.core.content.ContextCompat
import com.auth0.android.Auth0
import com.example.koadex.ui.form.FormEntryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var account: Auth0
    private val formViewModel: FormEntryViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        account = Auth0.getInstance(
           "tplWesPliZAjIckkDPfCQETXZ9IDkJ8T",
            "dev-qk6i0qseg2txot3r.us.auth0.com"
        )

        enableEdgeToEdge()
        setContent {
            KoadexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    KoadexApp(
                        activity = this,
                        modifier = Modifier.padding(innerPadding),
                        account = account
                    )
                }
            }
        }

    }
    fun arePermissionsGranted(): Boolean {
        return CAMERA_PERMISSION.all { perssion ->
            ContextCompat.checkSelfPermission(
                applicationContext,
                perssion
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        val CAMERA_PERMISSION = arrayOf(
            Manifest.permission.CAMERA
        )
    }
    fun arePermissionsGranted(): Boolean {
        return CAMERA_PERMISSION.all { perssion ->
            ContextCompat.checkSelfPermission(
                applicationContext,
                perssion
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        val CAMERA_PERMISSION = arrayOf(
            Manifest.permission.CAMERA
        )
    }
}
