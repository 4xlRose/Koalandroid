
package com.example.koadex.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface FormRepository {
    /*Borrar después de actualizar koadex con la nueva tabla formulario general*/

    fun getAllForms(): Flow<List<GeneralFormEntity>>
    fun getForm(id: Int): Flow<GeneralFormEntity?>
    suspend fun insertIntoListForms(listForms: List<GeneralFormEntity>)

    suspend fun insertUser(user: UserEntity)
    suspend fun updateUser(user: UserEntity)
    suspend fun deleteUser(user: UserEntity)
    suspend fun deleteAllUsers()
    suspend fun resetUserTable()
    fun getAllUsers(): Flow<List<UserEntity>>
    fun getUserById(id: Int): Flow<UserEntity?>
    fun getUserByEmail(email: String): Flow<UserEntity?>

    //Clima
    fun getWeatherById(id: Int): Flow<WeatherEntity?>
    suspend fun inserWeatherBegin(weathers: List<WeatherEntity>)


    //Epoca
    fun getSeasonById(id: Int): Flow<SeasonEntity?>
    suspend fun insertSeasonBegin(seasons: List<SeasonEntity>)

    //Zona tipo
    suspend fun insertZonasTiposBegin(zonasTipos: List<ZoneTypeEntity>)
    fun getZonaTipoId(id: Int): Flow<ZoneTypeEntity?>


    //Animal tipo
    suspend fun insertAnimaltipoBegin(animalTipos: List<AnimalTypeEntity>)
    fun getAnimalTipo(id: Int): Flow<AnimalTypeEntity?>

    //Observación tipo
    suspend fun insertObservaciontipoBegin(observacion: List<ObservTypeEntity>)
    fun getObservTipo(id: Int): Flow<ObservTypeEntity?>

    //Altura tipo
    suspend fun insertAlturatipoBegin(altura: List<HeightTypeEntity>)
    fun getAlturaTipo(id: Int): Flow<HeightTypeEntity?>

    //Coverage
    suspend fun insertCoberturatipoBegin(cobetura: List<CoverageEntity>)
    fun getCoberturaTipo(id: Int): Flow<CoverageEntity?>

    //Disturbio
    suspend fun insertDisturbioatipoBegin(disturbio: List<DisturbanceEntity>)
    fun getDisturbanceTipo(id: Int): Flow<DisturbanceEntity?>

    //Cuadrante superior
    suspend fun insertCuadranteSBegin(cuadranteS: List<SuperQuadrantEntity>)
    fun getCuadranteS(id: Int): Flow<SuperQuadrantEntity?>
    //Cuadrante intermedio
    suspend fun insertCuadranteIBegin(cuadranteI: List<MidQuadrantEntity>)
    fun getCuadranteI(id: Int): Flow<MidQuadrantEntity?>

    //Cuadrante sub
    suspend fun insertCuadranteBBegin(cuadranteB: List<SubQuadrantEntity>)
    fun getCuadranteB(id: Int): Flow<SubQuadrantEntity?>

    //Habitat
    suspend fun insertHabitatBegin(habitat: List<HabitatEntity>)
    fun getHabitat(id: Int): Flow<HabitatEntity?>

    fun getFormById(id: Int): Flow<GeneralFormEntity?>
    suspend fun insertGeneralForm(form: GeneralFormEntity)
    suspend fun updateGeneralForm(form: GeneralFormEntity)
    suspend fun deleteGeneralForm(form: GeneralFormEntity)

    suspend fun insertSpecieForm(form: SpecieFormEntity)
    suspend fun updateSpecieForm(form: SpecieFormEntity)
    suspend fun deleteSpecieForm(form: SpecieFormEntity)

    suspend fun insertFollowUpForm(form: FollowUpFormEntity)
    suspend fun updateFollowUpForm(form: FollowUpFormEntity)
    suspend fun deleteFollowUpForm(form: FollowUpFormEntity)

    suspend fun insertQuadrantForm(form: QuadrantFormEntity)
    suspend fun updateQuadrantForm(form: QuadrantFormEntity)
    suspend fun deleteQuadrantForm(form: QuadrantFormEntity)

    suspend fun insertRouteForm(form: RouteFormEntity)
    suspend fun updateRouteForm(form: RouteFormEntity)
    suspend fun deleteRouteForm(form: RouteFormEntity)

    suspend fun insertWeatherForm(form: WeatherFormEntity)
    suspend fun updateWeatherForm(form: WeatherFormEntity)
    suspend fun deleteWeatherForm(form: WeatherFormEntity)
}