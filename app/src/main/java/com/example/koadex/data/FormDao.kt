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
    fun getAllForms(): Flow<List<FormEntity>>

    @Query("SELECT * from forms WHERE id = :id")
    fun getForm(id: Int): Flow<FormEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(form: FormEntity)

    @Update
    suspend fun update(form: FormEntity)

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