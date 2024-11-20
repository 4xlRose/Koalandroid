
package com.example.koadex.data

import kotlinx.coroutines.flow.Flow

interface FormRepository {

    fun getAllForms(): Flow<List<FormEntity>>

    fun getForm(id: Int): Flow<FormEntity?>

    suspend fun insertForm(form: FormEntity)
    suspend fun updateForm(form: FormEntity)
    suspend fun deleteForm(form: FormEntity)
}
