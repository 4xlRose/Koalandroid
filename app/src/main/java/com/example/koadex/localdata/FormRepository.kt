package com.example.koadex.localdata

import kotlinx.coroutines.flow.Flow

interface FormRepository {
    fun getLastFormStream(): Flow<FormEntity?>
    suspend fun insertForm(form: FormEntity)
    suspend fun updateForm(form: FormEntity)
}

class OfflineFormRepository(private val formDao: FormDao) : FormRepository {
    override fun getLastFormStream(): Flow<FormEntity?> = formDao.getLastForm()
    override suspend fun insertForm(form: FormEntity) = formDao.insert(form)
    override suspend fun updateForm(form: FormEntity) = formDao.update(form)
}
