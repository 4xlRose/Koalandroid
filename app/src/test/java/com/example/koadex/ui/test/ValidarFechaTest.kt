package com.example.koadex.ui.test

import org.junit.Test
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.*
import java.time.LocalDate

class ValidarFechaTest {
    // Clase para validar las fechas y su formato
    class DateValidator {
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

    private val dateValidator = DateValidator()

    @Test
    fun test_valid_dates() {
        assertTrue(dateValidator.isValidDate("01/01/24"))
        assertTrue(dateValidator.isValidDate("31/12/23"))
        assertTrue(dateValidator.isValidDate("15/06/24"))
    }

    @Test
    fun test_invalid_months() {
        assertFalse(dateValidator.isValidDate("01/13/24")) // Mes 13 inválido
        assertFalse(dateValidator.isValidDate("01/00/24")) // Mes 0 inválido
    }

    @Test
    fun test_invalid_days() {
        assertFalse(dateValidator.isValidDate("32/01/24")) // Día 32 inválido
        assertFalse(dateValidator.isValidDate("31/04/24")) // 31 de abril no existe
        assertFalse(dateValidator.isValidDate("29/02/23")) // 29 de febrero en año no bisiesto
    }

    @Test
    fun test_invalid_format() {
        assertFalse(dateValidator.isValidDate("1/1/24"))  // Falta un cero
        assertFalse(dateValidator.isValidDate("aa/bb/cc")) // Letras inválidas
        assertFalse(dateValidator.isValidDate("01-01-24")) // Formato incorrecto
    }

    @Test
    fun test_date_formatting() {
        assertEquals("01/02/24", dateValidator.formatDate("010224"))
        assertEquals("31/12/23", dateValidator.formatDate("311223"))
        assertEquals("invalid", dateValidator.formatDate("invalid")) // Devuelve el mismo input si no es válido
    }
}