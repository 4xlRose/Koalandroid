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
