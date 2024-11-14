package com.example.koadex.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

class DateValidator {
    @RequiresApi(Build.VERSION_CODES.O)
    fun isValidDate(input: String): Boolean {
        // Verificar que solo se tengan numeros y el '/'
        if (!input.replace("/", "").all { it.isDigit() }) {
            return false
        }

        // Verificar el formato (dd/mm/yy)
        if (!input.matches(Regex("""^\d{2}/\d{2}/\d{2}$"""))) {
            return false
        }

        try {
            val parts = input.split("/")
            val day = parts[0].toInt()
            val month = parts[1].toInt()
            val year = parts[2].toInt()

            // Validar rangos mes y dias
            if (month < 1 || month > 12) return false
            if (day < 1 || day > 31) return false

            // Convertir año de dos digitos a cuatro digitos
            val fullYear = 2000 + year

            // Validar la fecha completa
            val date = LocalDate.of(fullYear, month, day)

            // Verificar que la fecha convertida coincide con los valores originales
            return date.dayOfMonth == day && date.monthValue == month
        } catch (e: Exception) {
            return false
        }
    }

    fun formatDate(input: String): String {
        if (input.length != 6) return input
        return "${input.substring(0,2)}/${input.substring(2,4)}/${input.substring(4,6)}"
    }
}