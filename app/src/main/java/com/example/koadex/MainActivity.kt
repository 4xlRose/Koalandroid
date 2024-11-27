package com.example.koadex
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.koadex.ui.theme.KoadexTheme
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.auth0.android.Auth0


class MainActivity : ComponentActivity() {
    private lateinit var account: Auth0

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        account = Auth0.getInstance(
            "tplWesPliZAjIckkDPfCQETXZ9IDkJ8T",
            "dev-qk6i0qseg2txot3r.us.auth0.com"
        )

        //Solicitar permisos
        if (!arePermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this, CAMERA_PERMISSION, 100
            )
        }
        requestLocationPermission()


        enableEdgeToEdge()
        setContent {
            KoadexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    KoadexApp(
                        this,
                        modifier = Modifier.padding(innerPadding),
                        account,

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

    private fun requestLocationPermission() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, permissions, 100)
        }
    }
}



