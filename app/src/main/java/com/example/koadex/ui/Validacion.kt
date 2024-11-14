package com.example.koadex.ui

// Función para validar que la fecha esté en el formato dd/mm/aa
fun isDateValid (date: String): Boolean {
    val regex = Regex("^\\d{2}/\\d{2}/\\d{2}\$")
    return regex.matches(date)
}