package com.example.koadex.clases

data class Configuracion(
    var notificacionesActivas: Boolean = true,
    var ultimaActualizacion: Long = System.currentTimeMillis()
) {
    companion object {
        private var instance: Configuracion? = null

        fun getInstance(): Configuracion {
            if (instance == null) {
                instance = Configuracion()
            }
            return instance!!
        }
    }
}