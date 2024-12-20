package com.example.koadex.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FormDao {
    // Métodos de Lista de Formularios
    @Query("SELECT * FROM general_form")
    fun getAllForms(): Flow<List<GeneralFormEntity>>

    @Query("SELECT * FROM general_form WHERE id = :id")
    fun getForm(id: Int): Flow<GeneralFormEntity?>

    @Query("SELECT * FROM general_form order by id desc limit 1")
    fun getLastGeneralForm(): Flow<GeneralFormEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIntoListForms(listForms: List<GeneralFormEntity>)

    @Delete()
    suspend fun deleteForm(form: GeneralFormEntity)


    // Métodos de Formulario General
    @Query("SELECT * from general_form WHERE id = :id")
    fun getFormById(id: Int): Flow<GeneralFormEntity>

    @Query("SELECT MAX(id) FROM general_form")
    suspend fun getLatestFormId(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGeneralForm(form: GeneralFormEntity)

    @Delete
    suspend fun deleteGeneralForm(form: GeneralFormEntity)

    @Update
    suspend fun updateGeneralForm(form: GeneralFormEntity)


    // Usuario
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("DELETE FROM user") // Use with resetUserTable()
    suspend fun deleteAllUsers()

    @Query("UPDATE `sqlite_sequence` SET `seq` = 0 WHERE `name` = 'user'") // Resets Auto Increment if there is 1 user left
    suspend fun resetUserTable()

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: Int): Flow<UserEntity?>

    @Query("SELECT * FROM user WHERE email = :email")
    fun getUserByEmail(email: String): Flow<UserEntity?>



    // Estado de Formularios
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFormState(form: FormStateEntity)

    @Query("SELECT * FROM form_state")
    fun getAllFormStates(): Flow<List<FormStateEntity>>

    @Query("SELECT * FROM form_state WHERE idGeneralForm = :id")
    fun getFormStateByFormId(id: Int): Flow<FormStateEntity?>

    @Update
    suspend fun updateFormState(form: FormStateEntity)

    @Delete
    suspend fun deleteFormState(form: FormStateEntity)

    @Query("DELETE FROM form_state")
    suspend fun deleteAllFormStates()

    @Query("UPDATE `sqlite_sequence` SET `seq` = 0 WHERE `name` = 'form_state'")
    suspend fun resetFormStateTable()


    // Clima
    @Query("SELECT * FROM weather WHERE id = :id")
    fun getWeatherById(id: Int): Flow<WeatherEntity?>

    @Query("SELECT * FROM weather WHERE type = :name")
    fun getWeatherByName(name: String): Flow<WeatherEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWeatherBegin(weathers: List<WeatherEntity>)


    // Época
    @Query("SELECT * FROM season WHERE id = :id")
    fun getSeasonById(id: Int): Flow<SeasonEntity?>

    @Query("SELECT * FROM season WHERE type = :name")
    fun getSeasonByName(name: String): Flow<SeasonEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSeasonBegin(seasons: List<SeasonEntity>)


    //Zona tipo
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertZonasTiposBegin(zonasTipos: List<ZoneTypeEntity>)

    @Query("Select * from zone_type where id = :id")
    fun getZonaTipoId(id: Int): Flow<ZoneTypeEntity?>


    //Animal tipo
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAnimaltipoBegin(animalTipos: List<AnimalTypeEntity>)

    @Query("Select * from animal_type where id = :id")
    fun getAnimalTipo(id: Int): Flow<AnimalTypeEntity?>

    //Observación tipo
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertObservaciontipoBegin(observacion: List<ObservTypeEntity>)

    @Query("Select * from observation_type where id = :id")
    fun getObservTipo(id: Int): Flow<ObservTypeEntity?>


    //Altura tipo
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAlturatipoBegin(altura: List<HeightTypeEntity>)

    @Query("Select * from height_type where id = :id")
    fun getAlturaTipo(id: Int): Flow<HeightTypeEntity?>


    //Coverage
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCoberturatipoBegin(cobetura: List<CoverageEntity>)

    @Query("Select * from coverage where id = :id")
    fun getCoberturaTipo(id: Int): Flow<CoverageEntity?>


    //Disturbio
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDisturbioatipoBegin(disturbio: List<DisturbanceEntity>)
    @Query("Select * from disturbance where id = :id")
    fun getDisturbanceTipo(id: Int): Flow<DisturbanceEntity?>

    //Cuadrante superior
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCuadranteSBegin(cuadranteS: List<SuperQuadrantEntity>)
    @Query("Select * from superior_quadrant where id = :id")
    fun getCuadranteS(id: Int): Flow<SuperQuadrantEntity?>


    //Cuadrante intermedio
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCuadranteIBegin(cuadranteI: List<MidQuadrantEntity>)

    @Query("Select * from intermediate_quadrant where id = :id")
    fun getCuadranteI(id: Int): Flow<MidQuadrantEntity?>


    //Cuadrante sub
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCuadranteBBegin(cuadranteB: List<SubQuadrantEntity>)

    @Query("Select * from sub_quadrant where id = :id")
    fun getCuadranteB(id: Int): Flow<SubQuadrantEntity?>


    //Habitat
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHabitatBegin(habitat: List<HabitatEntity>)

    @Query("Select * from habitat where id = :id")
    fun getHabitat(id: Int): Flow<HabitatEntity?>


    // Métodos de Formulario de Especie

    @Query("SELECT * from specie_form WHERE id = :id")
    fun getSpecieFormById(id: Int): Flow<SpecieFormEntity?>

    @Query("SELECT MAX(id) FROM specie_form")
    suspend fun getLatestSpecieFormId(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSpecieForm(form: SpecieFormEntity)

    @Delete
    suspend fun deleteSpecieForm(form: SpecieFormEntity)

    @Update
    suspend fun updateSpecieForm(form: SpecieFormEntity)



    // Métodos de Formulario de Seguimiento

    @Query("SELECT * from follow_up_form WHERE id = :id")
    fun getFollowUpFormById(id: Int): Flow<FollowUpFormEntity?>

    @Query("SELECT MAX(id) FROM follow_up_form")
    suspend fun getLatestFollowUpFormId(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFollowUpForm(form: FollowUpFormEntity)

    @Delete
    suspend fun deleteFollowUpForm(form: FollowUpFormEntity)

    @Update
    suspend fun updateFollowUpForm(form: FollowUpFormEntity)



    // Métodos de Formulario Cuadrantes

    @Query("SELECT * from quadrant_form WHERE id = :id")
    fun getQuadrantFormById(id: Int): Flow<QuadrantFormEntity?>

    @Query("SELECT MAX(id) FROM quadrant_form")
    suspend fun getLatestQuadrantFormId(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuadrantForm(form: QuadrantFormEntity)

    @Delete
    suspend fun deleteQuadrantForm(form: QuadrantFormEntity)

    @Update
    suspend fun updateQuadrantForm(form: QuadrantFormEntity)



    // Métodos de Formulario Ruta

    @Query("SELECT * from route_form WHERE id = :id")
    fun getRouteFormById(id: Int): Flow<RouteFormEntity?>

    @Query("SELECT MAX(id) FROM route_form")
    suspend fun getLatestRouteFormId(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRouteForm(form: RouteFormEntity)

    @Delete
    suspend fun deleteRouteForm(form: RouteFormEntity)

    @Update
    suspend fun updateRouteForm(form: RouteFormEntity)



    // Métodos de Formulario Clima

    @Query("SELECT * from weather_form WHERE id = :id")
    fun getWeatherFormById(id: Int): Flow<WeatherFormEntity?>

    @Query("SELECT MAX(id) FROM weather_form")
    suspend fun getLatestWeatherFormId(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWeatherForm(form: WeatherFormEntity)

    @Delete
    suspend fun deleteWeatherForm(form: WeatherFormEntity)

    @Update
    suspend fun updateWeatherForm(form: WeatherFormEntity)

    //Método de Formulario Busqueda libre
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBusquedaLibre(form: BusquedaLibreEntity)

    @Delete
    suspend fun deleteBusquedaLibre(form: BusquedaLibreEntity)

    @Update
    suspend fun updateBusquedaLibre(form: BusquedaLibreEntity)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPuntoConteo(form: PuntoConteoEntity)

    @Update
    suspend fun updatePuntoConteo(form: PuntoConteoEntity)

    @Delete
    suspend fun deletePuntoConteo(form: PuntoConteoEntity)

}
