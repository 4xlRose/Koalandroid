package com.example.koadex.data

import kotlinx.coroutines.flow.Flow

class OfflineFormsRepository(private val formDao : FormDao) : FormRepository {

    override fun getLastFormsStream(): Flow<FormEntity> = formDao.getLastForm()



    /*override fun getFormStream(id: Int): Flow<Formulario?> = formGeneralDao.getItem(id)*/

    override suspend fun insertForm(formGeneral: FormEntity) = formDao.insert(formGeneral)

    /*override suspend fun deleteForm(formGeneral: Formulario) = formGeneralDao.delete(formGeneral)*/

    override suspend fun updateForm(formGeneral: FormEntity) = formDao.update(formGeneral)
}

