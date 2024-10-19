package com.example.koadex.data

import kotlinx.coroutines.flow.Flow

class OfflineFormsRepository(private val formGeneralDao : FormGeneralDao) : FormsRepository{
    override fun getAllFormsStream(): Flow<List<Formulario>> = formGeneralDao.getAllItems()

    override fun getFormStream(id: Int): Flow<Formulario?> = formGeneralDao.getItem(id)

    override suspend fun insertForm(formGeneral: Formulario) = formGeneralDao.insert(formGeneral)

    override suspend fun deleteForm(formGeneral: Formulario) = formGeneralDao.delete(formGeneral)

    override suspend fun updateForm(formGeneral: Formulario) = formGeneralDao.update(formGeneral)
}

