
package com.example.koadex.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFormRepository @Inject constructor() : FormRepository {

    @Inject
    lateinit var formDao: FormDao

    override fun getAllForms(): Flow<List<GeneralFormEntity>> {
        return formDao.getAllForms()
    }
    override fun getForm(id: Int): Flow<GeneralFormEntity?> {
        return formDao.getForm(id)
    }
    override fun getAllUsers(): Flow<List<UserEntity>> {
        return formDao.getAllUsers()
    }

    override suspend fun insertForm(formGeneral: GeneralFormEntity) {
        return formDao.insert(formGeneral)
    }
    override suspend fun deleteForm(formGeneral: GeneralFormEntity) {
        return formDao.delete(formGeneral)
    }
    override suspend fun updateForm(formGeneral: GeneralFormEntity) {
        return formDao.update(formGeneral)
    }
    override suspend fun saveForm(formGeneral: GeneralFormEntity) {
        return formDao.saveForm(formGeneral)
    }
}

