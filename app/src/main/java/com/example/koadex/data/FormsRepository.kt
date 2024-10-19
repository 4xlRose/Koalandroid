package com.example.koadex.data

import kotlinx.coroutines.flow.Flow


interface FormsRepository {
    fun getAllFormsStream(): Flow<List<Formulario>>
    fun getFormStream(id: Int): Flow<Formulario?>
    suspend fun insertForm(formGeneral: Formulario)
    suspend fun deleteForm(formGeneral: Formulario)
    suspend fun updateForm(formGeneral: Formulario)
}