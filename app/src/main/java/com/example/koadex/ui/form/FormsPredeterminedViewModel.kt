package com.example.koadex.ui.form

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.koadex.data.AnimalTypeEntity
import com.example.koadex.data.CoverageEntity
import com.example.koadex.data.DisturbanceEntity
import com.example.koadex.data.FormRepository
import com.example.koadex.data.HabitatEntity
import com.example.koadex.data.HeightTypeEntity
import com.example.koadex.data.MidQuadrantEntity
import com.example.koadex.data.ObservTypeEntity
import com.example.koadex.data.SeasonEntity
import com.example.koadex.data.SubQuadrantEntity
import com.example.koadex.data.SuperQuadrantEntity
import com.example.koadex.data.WeatherEntity
import com.example.koadex.data.ZoneTypeEntity

class FormsPredeterminedViewModel(private val formRepository: FormRepository) : ViewModel() {

    //Guardar el clima

    suspend fun inicializarClimas() {

        var ListaClimas = listOf(
            WeatherEntity(1,"Soleado"),
            WeatherEntity(2,"Nublado"),
            WeatherEntity(3,"Lluvioso"))
        formRepository.inserWeatherBegin(ListaClimas)
    }
    suspend fun inicializarEpocas() {
        var ListaEpocas = listOf(
            SeasonEntity(1,"Verano"),
            SeasonEntity(2,"Primavera"),
            SeasonEntity(3,"Invierno"),
            SeasonEntity(4,"Otoño"))
        formRepository.insertSeasonBegin(ListaEpocas)
    }

    suspend fun inicializarZonaTipo() {
        var ListaZonaTipo = listOf(
            ZoneTypeEntity(1,"Bosque"),
            ZoneTypeEntity(2,"Arreglo Agroforestal"),
            ZoneTypeEntity(3,"Cultivos Transitorios"),
            ZoneTypeEntity(4,"Cultivos Permanentes"))
        formRepository.insertZonasTiposBegin(ListaZonaTipo)
    }

    suspend fun inicializarAnimalTipo() {
        var ListaAnimalTipo = listOf(
            AnimalTypeEntity(1,"Mamífero"),
            AnimalTypeEntity(2,"Ave"),
            AnimalTypeEntity(3,"Reptil"),
            AnimalTypeEntity(4,"Anfibio"),
            AnimalTypeEntity(5,"Insecto"))

        formRepository.insertAnimaltipoBegin(ListaAnimalTipo)
    }
    suspend fun inicializarObservTipo() {
        var ListaObserTipo = listOf(
            ObservTypeEntity(1,"La vió"),
            ObservTypeEntity(2,"Huella"),
            ObservTypeEntity(3,"Rastro"),
            ObservTypeEntity(4,"Cacería"),
            ObservTypeEntity(5,"Les dijeron"))

        formRepository.insertObservaciontipoBegin(ListaObserTipo)
    }
    suspend fun inicializaraltTipo() {
        var ListaAltura = listOf(
            HeightTypeEntity(1,"< 1 mt Baja>"),
            HeightTypeEntity(2,"1-3 mt Media"),
            HeightTypeEntity(3,"> 3 mt Alta")
        )

        formRepository.insertAlturatipoBegin(ListaAltura)
    }
    suspend fun inicializarCobertura() {
        var ListaCobertura = listOf(
            CoverageEntity(1,"BD"),
            CoverageEntity(2,"RA"),
            CoverageEntity(3,"RB"),
            CoverageEntity(4,"PA"),
            CoverageEntity(5,"PL"),
            CoverageEntity(6,"CP"),
            CoverageEntity(7,"CT"),
            CoverageEntity(8,"VH"),
            CoverageEntity(9,"TD"),
            CoverageEntity(10,"If"),

            )
        formRepository.insertCoberturatipoBegin(ListaCobertura)
    }
    suspend fun inicializarDisturbio() {
        var Lista = listOf(
            DisturbanceEntity(1,"Inundación"),
            DisturbanceEntity(2,"Quema"),
            DisturbanceEntity(3,"Tala"),
            DisturbanceEntity(4,"Erosión"),
            DisturbanceEntity(5,"Minería"),
            DisturbanceEntity(6,"Carretera"),
            DisturbanceEntity(7,"Plantas acuáticas"),
            DisturbanceEntity(8,"Otro")

        )
        formRepository.insertDisturbioatipoBegin(Lista)
    }


    suspend fun inicializarCuadranteS() {
        var Lista = listOf(
            SuperQuadrantEntity(1,"A"),
            SuperQuadrantEntity(2,"B"),
        )
        formRepository.insertCuadranteSBegin(Lista)
    }

    suspend fun inicializarCuadranteI() {
        var Lista = listOf(
            MidQuadrantEntity(1,"C"),
            MidQuadrantEntity(2,"D"),
            MidQuadrantEntity(3,"E"),
            MidQuadrantEntity(4,"F"),
            MidQuadrantEntity(5,"G"),
        )
        formRepository.insertCuadranteIBegin(Lista)
    }
    suspend fun inicializarCuadranteB() {
        var Lista = listOf(
            SubQuadrantEntity(1,"1"),
            SubQuadrantEntity(2,"2"),
            SubQuadrantEntity(3,"3"),
            SubQuadrantEntity(4,"4")
        )
        formRepository.insertCuadranteBBegin(Lista)
    }
    suspend fun inicializarHabitat() {
        var Lista = listOf(
            HabitatEntity(1,"Arbusto < 1 mt"),
            HabitatEntity(2,"Arbolito 1-3 mt"),
            HabitatEntity(3,"Árbol > 3 mt")
        )
        formRepository.insertHabitatBegin(Lista)
    }

    suspend fun inicializarTablasPredeterminadas() {
        inicializarClimas()
        inicializarEpocas()
        inicializarZonaTipo()
        inicializarAnimalTipo()
        inicializarObservTipo()
        inicializaraltTipo()
        inicializarCobertura()
        inicializarDisturbio()
        inicializarCuadranteS()
        inicializarCuadranteI()
        inicializarCuadranteB()
        inicializarHabitat()

    }

}


