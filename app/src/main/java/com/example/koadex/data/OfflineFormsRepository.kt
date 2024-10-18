package com.example.koadex.data

import kotlinx.coroutines.flow.Flow

class OfflineFormsRepository(private val formGeneralDao : FormGeneralDao) : FormsRepository{
    override fun getAllItemsStream(): Flow<List<Formulario>> = formGeneralDao.getAllItems()

    override fun getItemStream(id: Int): Flow<Formulario?> = formGeneralDao.getItem(id)

    override suspend fun insertForm(form: Formulario) = formGeneralDao.insert(form)

    override suspend fun deleteForm(form: Formulario) = formGeneralDao.delete(form)

    override suspend fun updateForm(form: Formulario) = formGeneralDao.update(form)
}

