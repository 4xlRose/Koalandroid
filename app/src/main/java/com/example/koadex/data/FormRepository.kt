
package com.example.koadex.data

import kotlinx.coroutines.flow.Flow

interface FormRepository {

    fun getAllForms(): Flow<List<GeneralFormEntity>>
    fun getForm(id: Int): Flow<GeneralFormEntity?>

    suspend fun insertGeneralForm(form: GeneralFormEntity)
    suspend fun updateGeneralForm(form: GeneralFormEntity)
    suspend fun deleteGeneralForm(form: GeneralFormEntity)

    suspend fun insertSpecieForm(form: SpecieFormEntity)
    suspend fun updateSpecieForm(form: SpecieFormEntity)
    suspend fun deleteSpecieForm(form: SpecieFormEntity)

    suspend fun insertFollowUpForm(form: FollowUpFormEntity)
    suspend fun updateFollowUpForm(form: FollowUpFormEntity)
    suspend fun deleteFollowUpForm(form: FollowUpFormEntity)

    fun getAllUsers(): Flow<List<UserEntity>>
}


