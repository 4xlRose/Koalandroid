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
    suspend fun insert(formGeneral: Formulario)
    @Update
    suspend fun update(formGeneral: Formulario)
    @Delete
    suspend fun delete(formGeneral: Formulario)
    @Query("SELECT * from formGeneral WHERE id = :id")
    fun getItem(id: Int): Flow<Formulario>
    @Query("SELECT * from formGeneral ORDER BY name ASC")
    fun getAllItems(): Flow<List<Formulario>>
}