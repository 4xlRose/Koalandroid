
package com.example.koadex.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFormsRepository(private val formDao : FormDao) : FormRepository {

    override fun getAllForms(): Flow<List<FormEntity>> = formDao.getAllForms()
    override fun getForm(id: Int): Flow<FormEntity?> = formDao.getForm(id)

    override suspend fun insertForm(formGeneral: FormEntity) = formDao.insert(formGeneral)

    override suspend fun deleteForm(formGeneral: FormEntity) = formDao.delete(formGeneral)

    override suspend fun updateForm(formGeneral: FormEntity) = formDao.update(formGeneral)
}
/*
class OfflineFormRepository @Inject constructor() : FormRepository {

    @Inject
    lateinit var formDao: FormDao

    override suspend fun insertUser(user: UserEntity) {
        return formDao.insertUser(user)
    }
    override suspend fun updateUser(user: UserEntity) {
        return formDao.updateUser(user)
    }
    override suspend fun deleteUser(user: UserEntity) {
        return formDao.deleteUser(user)
    }
    override fun getAllUsers(): Flow<List<UserEntity>> {
        return formDao.getAllUsers()
    }
    override fun getUserById(id: Int): Flow<UserEntity?> {
        return formDao.getUserById(id)
    }
    override fun getUserByName(name: String): Flow<UserEntity?> {
        return formDao.getUserByName(name)
    }
    override fun getAllAccountsByName(name: String): Flow<List<UserEntity>> {
        return formDao.getAllAccountsByName(name)
    }
    override fun getUserByEmail(email: String): Flow<UserEntity?> {
        return formDao.getUserByEmail(email)
    }
    override fun getAllAccountsByEmail(email: String): Flow<List<UserEntity>> {
        return formDao.getAllAccountsByEmail(email)
    }


    override fun getFullDatabase(): Flow<ListFormsEntity> {
        return formDao.getFullDatabase()
    }
    override fun getAllUploadedForms(): Flow<ListFormsEntity> {
        return formDao.getAllUploadedForms()
    }
    override fun getAllLocalForms(): Flow<ListFormsEntity> {
        return formDao.getAllLocalForms()
    }
    override suspend fun insertIntoListForms(listForms: ListFormsEntity) {
        return formDao.insertIntoListForms(listForms)
    }


    override fun getFormById(id: Int): Flow<GeneralFormEntity?> {
        return formDao.getFormById(id)
    }
    override suspend fun insertGeneralForm(form: GeneralFormEntity) {
        return formDao.insertGeneralForm(form)
    }
    override suspend fun deleteGeneralForm(form: GeneralFormEntity) {
        return formDao.deleteGeneralForm(form)
    }
    override suspend fun updateGeneralForm(form: GeneralFormEntity) {
        return formDao.updateGeneralForm(form)
    }


    override suspend fun insertSpecieForm(form: SpecieFormEntity) {
        return formDao.insertSpecieForm(form)
    }
    override suspend fun deleteSpecieForm(form: SpecieFormEntity) {
        return formDao.deleteSpecieForm(form)
    }
    override suspend fun updateSpecieForm(form: SpecieFormEntity) {
        return formDao.updateSpecieForm(form)
    }


    override suspend fun insertFollowUpForm(form: FollowUpFormEntity) {
        return formDao.insertFollowUpForm(form)
    }
    override suspend fun deleteFollowUpForm(form: FollowUpFormEntity) {
        return formDao.deleteFollowUpForm(form)
    }
    override suspend fun updateFollowUpForm(form: FollowUpFormEntity) {
        return formDao.updateFollowUpForm(form)
    }


    override suspend fun insertQuadrantForm(form: QuadrantFormEntity) {
        return formDao.insertQuadrantForm(form)
    }
    override suspend fun deleteQuadrantForm(form: QuadrantFormEntity) {
        return formDao.deleteQuadrantForm(form)
    }
    override suspend fun updateQuadrantForm(form: QuadrantFormEntity) {
        return formDao.updateQuadrantForm(form)
    }


    override suspend fun insertRouteForm(form: RouteFormEntity) {
        return formDao.insertRouteForm(form)
    }
    override suspend fun deleteRouteForm(form: RouteFormEntity) {
        return formDao.deleteRouteForm(form)
    }
    override suspend fun updateRouteForm(form: RouteFormEntity) {
        return formDao.updateRouteForm(form)
    }


    override suspend fun insertWeatherForm(form: WeatherFormEntity) {
        return formDao.insertWeatherForm(form)
    }
    override suspend fun deleteWeatherForm(form: WeatherFormEntity) {
        return formDao.deleteWeatherForm(form)
    }
    override suspend fun updateWeatherForm(form: WeatherFormEntity) {
        return formDao.updateWeatherForm(form)
    }
}

*/