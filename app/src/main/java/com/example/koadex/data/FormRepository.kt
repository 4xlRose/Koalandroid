package com.example.koadex.data

import kotlinx.coroutines.flow.Flow

interface FormRepository {
    fun getLastFormsStream(): Flow<FormEntity>
    suspend fun insertForm(form: FormEntity)
    suspend fun updateForm(form: FormEntity)
    suspend fun deleteForm(form: FormEntity)
}


