
package com.example.koadex.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFormRepository (private val formDao: FormDao) : FormRepository {

    /*Borrar después de actualizar el koadex con la nueva tabla de formularios general*/
    override fun getAllForms(): Flow<List<FormEntity>> = formDao.getAllForms()
    override fun getForm(id: Int): Flow<FormEntity?> = formDao.getForm(id)

    override suspend fun insertForm(formGeneral: FormEntity) = formDao.insert(formGeneral)

    override suspend fun deleteForm(formGeneral: FormEntity) = formDao.delete(formGeneral)

    override suspend fun updateForm(formGeneral: FormEntity) = formDao.update(formGeneral)
    /*Para el koadex*/
    override fun getFullDatabase(): Flow<List<GeneralFormEntity>>  = formDao.getFullDatabase()
    override suspend fun insertIntoListForms(listForms: List<GeneralFormEntity>) = formDao.insertIntoListForms(listForms)

    // Usuario
    override suspend fun insertUser(user: UserEntity) = formDao.insertUser(user)
    override suspend fun updateUser(user: UserEntity) = formDao.updateUser(user)
    override suspend fun deleteUser(user: UserEntity) = formDao.deleteUser(user)
    override fun getAllUsers(): Flow<List<UserEntity>> = formDao.getAllUsers()
    override fun getUserById(id: Int): Flow<UserEntity?> = formDao.getUserById(id)

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

