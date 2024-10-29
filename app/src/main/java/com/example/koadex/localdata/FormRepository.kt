package com.example.koadex.localdata

import kotlinx.coroutines.flow.Flow

interface FormRepository {
    fun getLastFormsStream(): Flow<List<FormEntity>>
    suspend fun insertForm(form: FormEntity)
    suspend fun updateForm(form: FormEntity)
}


