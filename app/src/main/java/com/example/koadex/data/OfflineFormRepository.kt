
package com.example.koadex.data

import androidx.room.Query
import kotlinx.coroutines.flow.Flow

class OfflineFormRepository (private val formDao: FormDao) : FormRepository {

    /*Borrar después de actualizar el koadex con la nueva tabla de formularios general*/
    override fun getAllForms(): Flow<List<GeneralFormEntity>> = formDao.getAllForms()
    override suspend fun insertIntoListForms(listForms: List<GeneralFormEntity>) = formDao.insertIntoListForms(listForms)
    override fun getLastGeneralForm(): Flow<GeneralFormEntity> = formDao.getLastGeneralForm()
    override suspend fun deleteForm(form: GeneralFormEntity)  = formDao.deleteForm(form)

    // Usuario
    override suspend fun insertUser(user: UserEntity) = formDao.insertUser(user)
    override suspend fun updateUser(user: UserEntity) = formDao.updateUser(user)
    override suspend fun deleteUser(user: UserEntity) = formDao.deleteUser(user)
    override suspend fun deleteAllUsers() = formDao.deleteAllUsers()
    override suspend fun resetUserTable() = formDao.resetUserTable()
    override fun getAllUsers(): Flow<List<UserEntity>> = formDao.getAllUsers()
    override fun getUserById(id: Int): Flow<UserEntity?> = formDao.getUserById(id)
    override fun getUserByEmail(email: String): Flow<UserEntity?> = formDao.getUserByEmail(email)

    // Estado de Formularios
    override suspend fun insertFormState(form: FormStateEntity) = formDao.insertFormState(form)
    override fun getAllFormStates(): Flow<List<FormStateEntity>> = formDao.getAllFormStates()
    override fun getFormStateByFormId(id: Int): Flow<FormStateEntity?> = formDao.getFormStateByFormId(id)
    override suspend fun updateFormState(form: FormStateEntity) = formDao.updateFormState(form)
    override suspend fun deleteFormState(form: FormStateEntity) = formDao.deleteFormState(form)
    override suspend fun deleteAllFormStates() = formDao.deleteAllFormStates()
    override suspend fun resetFormStateTable() = formDao.resetFormStateTable()


    //Clima
    override fun getWeatherById(id: Int): Flow<WeatherEntity?> = formDao.getWeatherById(id)
    override suspend fun inserWeatherBegin(weathers: List<WeatherEntity>) = formDao.insertWeatherBegin(weathers)

    //Epoca
    override fun getSeasonById(id: Int): Flow<SeasonEntity?> = formDao.getSeasonById(id)
    override suspend fun insertSeasonBegin(seasons: List<SeasonEntity>) = formDao.insertSeasonBegin(seasons)

    //Zona tipo
    override suspend fun insertZonasTiposBegin(zonasTipos: List<ZoneTypeEntity>) = formDao.insertZonasTiposBegin(zonasTipos)
    override fun getZonaTipoId(id: Int): Flow<ZoneTypeEntity?> = formDao.getZonaTipoId(id)

    //Animal tipo
    override suspend fun insertAnimaltipoBegin(animalTipos: List<AnimalTypeEntity>) = formDao.insertAnimaltipoBegin(animalTipos)
    override fun getAnimalTipo(id: Int): Flow<AnimalTypeEntity?> = formDao.getAnimalTipo(id)

    //Observación tipo
    override suspend fun insertObservaciontipoBegin(observacion: List<ObservTypeEntity>) = formDao.insertObservaciontipoBegin(observacion)
    override fun getObservTipo(id: Int): Flow<ObservTypeEntity?> = formDao.getObservTipo(id)

    //Altura tipo
    override suspend fun insertAlturatipoBegin(altura: List<HeightTypeEntity>) = formDao.insertAlturatipoBegin(altura)
    override fun getAlturaTipo(id: Int): Flow<HeightTypeEntity?> = formDao.getAlturaTipo(id)

    //Coverage
    override suspend fun insertCoberturatipoBegin(cobetura: List<CoverageEntity>) = formDao.insertCoberturatipoBegin(cobetura)
    override fun getCoberturaTipo(id: Int): Flow<CoverageEntity?> = formDao.getCoberturaTipo(id)

    //Disturbio
    override suspend fun insertDisturbioatipoBegin(disturbio: List<DisturbanceEntity>) = formDao.insertDisturbioatipoBegin(disturbio)
    override fun getDisturbanceTipo(id: Int): Flow<DisturbanceEntity?> = formDao.getDisturbanceTipo(id)

    //Cuadrante superior
    override suspend fun insertCuadranteSBegin(cuadranteS: List<SuperQuadrantEntity>) = formDao.insertCuadranteSBegin(cuadranteS)
    override fun getCuadranteS(id: Int): Flow<SuperQuadrantEntity?> = formDao.getCuadranteS(id)

    //Cuadrante intermedio
    override suspend fun insertCuadranteIBegin(cuadranteI: List<MidQuadrantEntity>) = formDao.insertCuadranteIBegin(cuadranteI)
    override fun getCuadranteI(id: Int): Flow<MidQuadrantEntity?> = formDao.getCuadranteI(id)

    //Cuadrante sub
    override suspend fun insertCuadranteBBegin(cuadranteB: List<SubQuadrantEntity>) = formDao.insertCuadranteBBegin(cuadranteB)
    override fun getCuadranteB(id: Int): Flow<SubQuadrantEntity?> = formDao.getCuadranteB(id)


    //Habitat
    override suspend fun insertHabitatBegin(habitat: List<HabitatEntity>) = formDao.insertHabitatBegin(habitat)
    override fun getHabitat(id: Int): Flow<HabitatEntity?> = formDao.getHabitat(id)

    //General
    override fun getFormById(id: Int): Flow<GeneralFormEntity?> = formDao.getFormById(id)
    override suspend fun getLatestFormId(): Int = formDao.getLatestFormId()
    override suspend fun insertGeneralForm(form: GeneralFormEntity) = formDao.insertGeneralForm(form)
    override suspend fun updateGeneralForm(form: GeneralFormEntity) = formDao.updateGeneralForm(form)
    override suspend fun deleteGeneralForm(form: GeneralFormEntity) = formDao.deleteGeneralForm(form)

    //Especies
    override suspend fun insertSpecieForm(form: SpecieFormEntity) = formDao.insertSpecieForm(form)
    override suspend fun updateSpecieForm(form: SpecieFormEntity) = formDao.updateSpecieForm(form)
    override suspend fun deleteSpecieForm(form: SpecieFormEntity) = formDao.deleteSpecieForm(form)

    //Seguimiento

    override suspend fun insertFollowUpForm(form: FollowUpFormEntity) = formDao.insertFollowUpForm(form)
    override suspend fun updateFollowUpForm(form: FollowUpFormEntity) = formDao.updateFollowUpForm(form)
    override suspend fun deleteFollowUpForm(form: FollowUpFormEntity) = formDao.deleteFollowUpForm(form)

    //Cuadrante
    override suspend fun insertQuadrantForm(form: QuadrantFormEntity) = formDao.insertQuadrantForm(form)
    override suspend fun updateQuadrantForm(form: QuadrantFormEntity) = formDao.updateQuadrantForm(form)
    override suspend fun deleteQuadrantForm(form: QuadrantFormEntity) = formDao.deleteQuadrantForm(form)

    //Ruta
    override suspend fun insertRouteForm(form: RouteFormEntity) = formDao.insertRouteForm(form)
    override suspend fun updateRouteForm(form: RouteFormEntity) = formDao.updateRouteForm(form)
    override suspend fun deleteRouteForm(form: RouteFormEntity) = formDao.deleteRouteForm(form)

    //Variación climas
    override suspend fun insertWeatherForm(form: WeatherFormEntity) = formDao.insertWeatherForm(form)
    override suspend fun updateWeatherForm(form: WeatherFormEntity) = formDao.updateWeatherForm(form)
    override suspend fun deleteWeatherForm(form: WeatherFormEntity) = formDao.deleteWeatherForm(form)
}

