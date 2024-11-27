package com.example.koadex.ui.form

import androidx.lifecycle.ViewModel
import com.example.koadex.R
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
import com.example.koadex.data.UserEntity
import com.example.koadex.data.WeatherEntity
import com.example.koadex.data.ZoneTypeEntity
import com.example.koadex.navigate.getCurrentDate
import kotlinx.coroutines.flow.first

class FormsPredeterminedViewModel(private val formRepository: FormRepository) : ViewModel() {
    //Guardar el clima
    private suspend fun inicializarClimas() {
        val listaClimas = listOf(
            WeatherEntity(1,"Soleado"),
            WeatherEntity(2,"Nublado"),
            WeatherEntity(3,"Lluvioso"))
        formRepository.inserWeatherBegin(listaClimas)
    }
    private suspend fun inicializarEpocas() {
        val listaEpocas = listOf(
            SeasonEntity(1,"Verano"),
            //SeasonEntity(2,"Primavera"),
            SeasonEntity(3,"Invierno"),
            //SeasonEntity(4,"Otoño")
            )
        formRepository.insertSeasonBegin(listaEpocas)
    }

    private suspend fun inicializarZonaTipo() {
        val listaZonaTipo = listOf(
            ZoneTypeEntity(1,"Bosque"),
            ZoneTypeEntity(2,"Arreglo Agroforestal"),
            ZoneTypeEntity(3,"Cultivos Transitorios"),
            ZoneTypeEntity(4,"Cultivos Permanentes"))
        formRepository.insertZonasTiposBegin(listaZonaTipo)
    }

    private suspend fun inicializarAnimalTipo() {
        val listaAnimalTipo = listOf(
            AnimalTypeEntity(1,"Mamífero"),
            AnimalTypeEntity(2,"Ave"),
            AnimalTypeEntity(3,"Reptil"),
            AnimalTypeEntity(4,"Anfibio"),
            AnimalTypeEntity(5,"Insecto"))

        formRepository.insertAnimaltipoBegin(listaAnimalTipo)
    }
    private suspend fun inicializarObservTipo() {
        val listaObserTipo = listOf(
            ObservTypeEntity(1,"La vió"),
            ObservTypeEntity(2,"Huella"),
            ObservTypeEntity(3,"Rastro"),
            ObservTypeEntity(4,"Cacería"),
            ObservTypeEntity(5,"Les dijeron"))

        formRepository.insertObservaciontipoBegin(listaObserTipo)
    }
    private suspend fun inicializaraltTipo() {
        val listaAltura = listOf(
            HeightTypeEntity(1,"< 1 mt Baja>"),
            HeightTypeEntity(2,"1-3 mt Media"),
            HeightTypeEntity(3,"> 3 mt Alta")
        )

        formRepository.insertAlturatipoBegin(listaAltura)
    }
    private suspend fun inicializarCobertura() {
        val listaCobertura = listOf(
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
        formRepository.insertCoberturatipoBegin(listaCobertura)
    }
    private suspend fun inicializarDisturbio() {
        val lista = listOf(
            DisturbanceEntity(1,"Inundación"),
            DisturbanceEntity(2,"Quema"),
            DisturbanceEntity(3,"Tala"),
            DisturbanceEntity(4,"Erosión"),
            DisturbanceEntity(5,"Minería"),
            DisturbanceEntity(6,"Carretera"),
            DisturbanceEntity(7,"Plantas acuáticas"),
            DisturbanceEntity(8,"Otro")

        )
        formRepository.insertDisturbioatipoBegin(lista)
    }


    private suspend fun inicializarCuadranteS() {
        val lista = listOf(
            SuperQuadrantEntity(1,"A"),
            SuperQuadrantEntity(2,"B"),
        )
        formRepository.insertCuadranteSBegin(lista)
    }

    private suspend fun inicializarCuadranteI() {
        val lista = listOf(
            MidQuadrantEntity(1,"C"),
            MidQuadrantEntity(2,"D"),
            MidQuadrantEntity(3,"E"),
            MidQuadrantEntity(4,"F"),
            MidQuadrantEntity(5,"G"),
        )
        formRepository.insertCuadranteIBegin(lista)
    }
    private suspend fun inicializarCuadranteB() {
        val lista = listOf(
            SubQuadrantEntity(1,"1"),
            SubQuadrantEntity(2,"2"),
            SubQuadrantEntity(3,"3"),
            SubQuadrantEntity(4,"4")
        )
        formRepository.insertCuadranteBBegin(lista)
    }
    private suspend fun inicializarHabitat() {
        val lista = listOf(
            HabitatEntity(1,"Arbusto < 1 mt"),
            HabitatEntity(2,"Arbolito 1-3 mt"),
            HabitatEntity(3,"Árbol > 3 mt")
        )
        formRepository.insertHabitatBegin(lista)
    }

    private suspend fun inicializarUsuariosPredeterminados() {
        val allUsers = formRepository.getAllUsers().first()
        if (allUsers.isEmpty()) {
            formRepository.insertUser(koalandroidUser)
            formRepository.insertUser(siUser)
            formRepository.insertUser(pepeUser)

        }
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
        inicializarUsuariosPredeterminados()
    }
}


private val koalandroidUser = UserEntity(
    name = "Koalandroid",
    email = "koalandroid@tec.mx",
    phone = "+00 012 345 6789",
    password = "KoalAndroid*2025",
    startDate = getCurrentDate(),
    uploadedForms = 0,
    locallyStoredForms = 0,
    posts = 0,
    following = 0,
    followers = 0,
    isloggedIn = false,
    idZone = 0,
    profilePicture = R.drawable.profilepicture // Recurso de imagen predeterminado
)
private val siUser = UserEntity(
    name = "si",
    email = "si@tec.mx",
    phone = "+00 012 345 6789",
    password = "123qwe@@",
    startDate = getCurrentDate(),
    uploadedForms = 0,
    locallyStoredForms = 0,
    posts = 0,
    following = 0,
    followers = 0,
    isloggedIn = false,
    idZone = 0,
    profilePicture = R.drawable.profilepicture // Recurso de imagen predeterminado
)
private val pepeUser = UserEntity(
    name = "pepe",
    email = "pepe@gmail.com",
    phone = "+00 012 345 6789",
    password = "123qwe@@",
    startDate = getCurrentDate(),
    uploadedForms = 0,
    locallyStoredForms = 0,
    posts = 0,
    following = 0,
    followers = 0,
    isloggedIn = false,
    idZone = 0,
    profilePicture = R.drawable.profilepicture // Recurso de imagen predeterminado
)
