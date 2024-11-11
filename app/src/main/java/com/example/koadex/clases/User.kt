package com.example.koadex.clases

data class User(
    val username: String,
    val totalForms: Int,
    val uploadedForms: Int,
    val locallyStoredForms: Int
) {

}