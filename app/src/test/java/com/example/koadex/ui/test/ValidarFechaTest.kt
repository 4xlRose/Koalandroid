package com.example.koadex.ui.test

import com.example.koadex.ui.isDateValid
import org.junit.Test
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

class ValidarFechaTest {

    // Funcion de prueba para fechas validas
    @Test
    fun valid_date_formats_accepted() {
        assertTrue(isDateValid("12/12/22"))
        assertTrue(isDateValid("01/01/23"))
    }

    // Funcion de prueba para fechas no validas
    @Test
    fun invalid_date_formats_rejected() {
        assertFalse(isDateValid("12-12-22")) // Guiones en lugar de barras
        assertFalse(isDateValid("12/12/2022")) // Año de cuatro digitos
        assertFalse(isDateValid("1/1/22")) // Día y mes de un solo digito
        assertFalse(isDateValid("31/04/22")) // Fecha invalida (abril tiene 30 días)
        assertFalse(isDateValid("12/13/22")) // Mes inva lido
    }
}