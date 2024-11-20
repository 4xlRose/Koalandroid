package com.example.koadex.clases

import android.graphics.Bitmap
import com.example.koadex.R

data class User(
    var username: String,
    val totalForms: Int,
    val uploadedForms: Int,
    val locallyStoredForms: Int,
    // Otros campos relevantes del usuario
    val posts: Int = 0,
    val following: Int = 0,
    val followers: Int = 0,
    val isloggedIn: Boolean = true,
    // la foto de perfil
    val profilePicture: Int = R.drawable.profilepicture
    ) {

}