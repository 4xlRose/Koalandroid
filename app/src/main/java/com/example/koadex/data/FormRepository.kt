
package com.example.koadex.data

import kotlinx.coroutines.flow.Flow

interface FormRepository {
    fun getFullDatabase(): Flow<ListFormsEntity>
    fun getAllUploadedForms(): Flow<ListFormsEntity>
    fun getAllLocalForms(): Flow<ListFormsEntity>
    suspend fun insertIntoListForms(listForms: ListFormsEntity)

    suspend fun insertUser(user: UserEntity)
    suspend fun updateUser(user: UserEntity)
    suspend fun deleteUser(user: UserEntity)
    fun getAllUsers(): Flow<List<UserEntity>>
    fun getUserById(id: Int): Flow<UserEntity?>
    fun getUserByName(name: String): Flow<UserEntity?>
    fun getAllAccountsByName(name: String): Flow<List<UserEntity>>
    fun getUserByEmail(email: String): Flow<UserEntity?>
    fun getAllAccountsByEmail(email: String): Flow<List<UserEntity>>

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


