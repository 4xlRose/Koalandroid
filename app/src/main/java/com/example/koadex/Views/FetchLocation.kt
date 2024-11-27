package com.example.koadex.Views

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices

@Composable
fun FetchLocation(
    onLocationFetched: (String) -> Unit,
    activity: Activity
) {
    // FusedLocationProviderClient
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

    // Fetch location when Composable is first launched
    LaunchedEffect(Unit) {
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val coordinates = "Lat: $latitude, Long: $longitude"
                    onLocationFetched(coordinates)
                } else {
                    onLocationFetched("Location not available")
                }
            }
        } else {
            onLocationFetched("Permission required")
        }
    }
}