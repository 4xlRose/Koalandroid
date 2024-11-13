
package com.example.koadex.data

import kotlinx.coroutines.flow.Flow

interface FormRepository {

    fun getAllForms(): Flow<List<GeneralFormEntity>>
    fun getForm(id: Int): Flow<GeneralFormEntity?>

    suspend fun insertForm(formGeneral: GeneralFormEntity)
    suspend fun updateForm(formGeneral: GeneralFormEntity)
    suspend fun deleteForm(formGeneral: GeneralFormEntity)

    fun getAllUsers(): Flow<List<UserEntity>>
}


