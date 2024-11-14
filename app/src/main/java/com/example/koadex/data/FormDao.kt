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
    // TO DO
    // añadir un form para un usuario y cambiar su estado entre subido y guardado
    //

    // Métodos de Formulario General
    @Query("SELECT * FROM general_form ORDER BY id DESC")
    fun getAllForms(): Flow<List<GeneralFormEntity>>

    @Query("SELECT * from general_form WHERE id = :id")
    fun getForm(id: Int): Flow<GeneralFormEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGeneralForm(form: GeneralFormEntity)

    @Update
    suspend fun updateGeneralForm(form: GeneralFormEntity)

    @Delete
    suspend fun deleteGeneralForm(form: GeneralFormEntity)



    // Métodos de Usuario
    @Insert
    fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    fun getUser(email: String, password: String): UserEntity?

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: Int): UserEntity?

    @Update
    fun updateUser(user: UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user WHERE name = :name")
    fun getUserByName(name: String): UserEntity?

    @Query("SELECT * FROM user WHERE name LIKE :name")
    fun getUsersByName(name: String): List<UserEntity>

    @Query("SELECT * FROM user WHERE email = :email")
    fun getUserByEmail(email: String): UserEntity?

    @Query("SELECT * FROM user WHERE email LIKE :email")
    fun getUsersByEmail(email: String): List<UserEntity>



    // Métodos de Formulario de Especie
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSpecieForm(form: SpecieFormEntity)

    @Update
    suspend fun updateSpecieForm(form: SpecieFormEntity)

    @Delete
    suspend fun deleteSpecieForm(form: SpecieFormEntity)



    // Métodos de Formulario de Seguimiento
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFollowUpForm(form: FollowUpFormEntity)

    @Update
    suspend fun updateFollowUpForm(form: FollowUpFormEntity)

    @Delete
    suspend fun deleteFollowUpForm(form: FollowUpFormEntity)
}
