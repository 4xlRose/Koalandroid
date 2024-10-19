package com.example.koadex.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FormGeneralDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(form: Formulario)
    @Update
    suspend fun update(form: Formulario)
    @Delete
    suspend fun delete(form: Formulario)
    @Query("SELECT * from form WHERE id = :id")
    fun getItem(id: Int): Flow<Formulario>
    @Query("SELECT * from form ORDER BY name ASC")
    fun getAllItems(): Flow<List<Formulario>>
}