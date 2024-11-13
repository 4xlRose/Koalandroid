
package com.example.koadex.data

import kotlinx.coroutines.flow.Flow

class OfflineFormsRepository(private val formDao : FormDao) : FormRepository {

    override fun getAllForms(): Flow<List<GeneralFormEntity>> = formDao.getAllForms()
    override fun getForm(id: Int): Flow<GeneralFormEntity?> = formDao.getForm(id)
    override fun getAllUsers(): Flow<List<UserEntity>> = formDao.getAllUsers()

    override suspend fun insertForm(formGeneral: GeneralFormEntity) = formDao.insert(formGeneral)

    override suspend fun deleteForm(formGeneral: GeneralFormEntity) = formDao.delete(formGeneral)

    override suspend fun updateForm(formGeneral: GeneralFormEntity) = formDao.update(formGeneral)
}

