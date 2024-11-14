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
}
/*
@Dao
interface FormDao {
    // TO DO
    // añadir un form para un usuario y cambiar su estado entre subido y guardado
    //

    // Métodos de Lista de Formularios
    @Query("SELECT * FROM list_forms")
    fun getFullDatabase(): Flow<ListFormsEntity>

    @Query("SELECT * FROM list_forms WHERE state = 1")
    fun getAllUploadedForms(): Flow<ListFormsEntity>

    @Query("SELECT * FROM list_forms WHERE state = 0")
    fun getAllLocalForms(): Flow<ListFormsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIntoListForms(listForms: ListFormsEntity)


    // Métodos de Formulario General
    @Query("SELECT * from general_form WHERE id = :id")
    fun getFormById(id: Int): Flow<GeneralFormEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGeneralForm(form: GeneralFormEntity)

    @Delete
    suspend fun deleteGeneralForm(form: GeneralFormEntity)

    @Update
    suspend fun updateGeneralForm(form: GeneralFormEntity)



    // Métodos de Usuario
    @Insert
    fun insertUser(user: UserEntity)

    @Update
    fun updateUser(user: UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: Int): Flow<UserEntity?>

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user WHERE name = :name")
    fun getUserByName(name: String): Flow<UserEntity?>

    @Query("SELECT * FROM user WHERE name LIKE :name")
    fun getAllAccountsByName(name: String): Flow<List<UserEntity>>

    @Query("SELECT * FROM user WHERE email = :email")
    fun getUserByEmail(email: String): Flow<UserEntity?>

    @Query("SELECT * FROM user WHERE email LIKE :email")
    fun getAllAccountsByEmail(email: String): Flow<List<UserEntity>>



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
*/