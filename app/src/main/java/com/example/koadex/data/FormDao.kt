package com.example.koadex.data

import android.provider.Settings
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.koadex.clases.User
import kotlinx.coroutines.flow.Flow

@Dao
interface FormDao {

    /*Borrar después de actualizar el koadex con la nueva tabla de formularios general*/

    @Query("SELECT * FROM forms ORDER BY id DESC")
    fun getAllForms(): Flow<List<FormEntity>>

    @Query("SELECT * from forms WHERE id = :id")
    fun getForm(id: Int): Flow<FormEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(form: FormEntity)

    @Update
    suspend fun update(form: FormEntity)

    @Delete
    suspend fun delete(form: FormEntity)
    // Métodos de Lista de Formularios
    @Query("SELECT * FROM general_form")
    fun getFullDatabase(): Flow<List<GeneralFormEntity>>

    @Query("SELECT * FROM general_form order by id desc limit 1")
    fun getLastGeneralForm(): Flow<GeneralFormEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIntoListForms(listForms: List<GeneralFormEntity>)


    // Métodos de Formulario General
    @Query("SELECT * from general_form WHERE id = :id")
    fun getFormById(id: Int): Flow<GeneralFormEntity>

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

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: Int): Flow<UserEntity?>


    // Clima
    @Query("SELECT * FROM weather WHERE id = :id")
    fun getWeatherById(id: Int): Flow<WeatherEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWeatherBegin(weathers: List<WeatherEntity>)


    // Época
    @Query("SELECT * FROM season WHERE id = :id")
    fun getSeasonById(id: Int): Flow<SeasonEntity?>

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
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSpecieForm(form: SpecieFormEntity)

    @Delete
    suspend fun deleteSpecieForm(form: SpecieFormEntity)

    @Update
    suspend fun updateSpecieForm(form: SpecieFormEntity)



    // Métodos de Formulario de Seguimiento
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFollowUpForm(form: FollowUpFormEntity)

    @Delete
    suspend fun deleteFollowUpForm(form: FollowUpFormEntity)

    @Update
    suspend fun updateFollowUpForm(form: FollowUpFormEntity)



    // Métodos de Formulario Cuadrantes
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuadrantForm(form: QuadrantFormEntity)

    @Delete
    suspend fun deleteQuadrantForm(form: QuadrantFormEntity)

    @Update
    suspend fun updateQuadrantForm(form: QuadrantFormEntity)



    // Métodos de Formulario Ruta
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRouteForm(form: RouteFormEntity)

    @Delete
    suspend fun deleteRouteForm(form: RouteFormEntity)

    @Update
    suspend fun updateRouteForm(form: RouteFormEntity)



    // Métodos de Formulario Clima
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWeatherForm(form: WeatherFormEntity)

    @Delete
    suspend fun deleteWeatherForm(form: WeatherFormEntity)

    @Update
    suspend fun updateWeatherForm(form: WeatherFormEntity)
}
