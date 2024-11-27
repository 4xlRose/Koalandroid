package com.example.koadex.data

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.koadex.R

@Entity(tableName = "general_form"/*,
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["idUser"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = WeatherEntity::class,
            parentColumns = ["id"],
            childColumns = ["idWeather"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SeasonEntity::class,
            parentColumns = ["id"],
            childColumns = ["idSeason"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SpecieFormEntity::class,
            parentColumns = ["id"],
            childColumns = ["idSpecieForm"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FollowUpFormEntity::class,
            parentColumns = ["id"],
            childColumns = ["idFollowUpForm"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = QuadrantFormEntity::class,
            parentColumns = ["id"],
            childColumns = ["idQuadrantForm"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = RouteFormEntity::class,
            parentColumns = ["id"],
            childColumns = ["idRouteForm"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = WeatherFormEntity::class,
            parentColumns = ["id"],
            childColumns = ["idWeatherForm"],
            onDelete = ForeignKey.CASCADE
        ),
    ]*/)
data class GeneralFormEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var date: String = "",
    var hour: String = "",
    var idUser: Int, // Foreign Key
    var idWeather: Int, // Foreign Key
    var idSeason: Int, // Foreign Key
    var place: String = "",
    var idSpecieForm: Int, // Foreign Key
    var idFollowUpForm: Int, // Foreign Key
    var idQuadrantForm: Int, // Foreign Key
    var idRouteForm: Int, // Foreign Key
    var idWeatherForm: Int // Foreign Key
)

@Entity(tableName = "user"/*,
    foreignKeys = [
        ForeignKey(
            entity = ZoneEntity::class,
            parentColumns = ["id"],
            childColumns = ["idZone"],
            onDelete = ForeignKey.CASCADE
        )
    ]*/)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String = "", //Se agrega
    var email: String = "", //Se agrega
    var phone: String = "", //Se agrega
    var password: String = "", //Se agrega
    var startDate: String = "", //Se agrega
    var idZone: Int, // Foreign Key //1
    var uploadedForms: Int, // predeterminado 0
    var locallyStoredForms: Int, // predeterminado 0
    var posts: Int = 0, // 0
    var following: Int = 0, // 0
    var followers: Int = 0, // 0
    var isloggedIn: Boolean = false, //
    // la foto de perfil
    var profilePicture: Int = R.drawable.profilepicture
)

@Entity(tableName = "form_state"/*,
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["idUser"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GeneralFormEntity::class,
            parentColumns = ["id"],
            childColumns = ["idGeneralForm"],
            onDelete = ForeignKey.CASCADE
        )
    ]*/)
data class FormStateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var idUser: Int, // Foreign Key
    var idGeneralForm: Int, // Foreign Key
    var isUploaded: Boolean
)

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var weather: String = "",
)

@Entity(tableName = "season")
data class SeasonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var season: String = "",
)

@Entity(tableName = "zone")
data class ZoneEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var zone: String = "",
)


@Entity(tableName = "specie_form"/*,
    foreignKeys = [
        ForeignKey(
            entity = GeneralFormEntity::class,
            parentColumns = ["id"],
            childColumns = ["idGeneralForm"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ZoneTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["idZoneType"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AnimalTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["idAnimalType"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ObservTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["idObservType"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = HeightTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["idHeightType"],
            onDelete = ForeignKey.CASCADE
        )
    ]*/)
data class SpecieFormEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var transect: String = "",
    var idZoneType: Int, // Foreign Key
    var idAnimalType: Int, // Foreign Key
    var animalName: String = "",
    var scientificName: String = "",
    var quantity: Int = 0,
    var idObservType: Int, // Foreign Key
    var idHeightType: Int, // Foreign Key
    var evidences: ByteArray = byteArrayOf(),
    var observations: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SpecieFormEntity

        if (id != other.id) return false
        if (transect != other.transect) return false
        if (idZoneType != other.idZoneType) return false
        if (idAnimalType != other.idAnimalType) return false
        if (animalName != other.animalName) return false
        if (scientificName != other.scientificName) return false
        if (quantity != other.quantity) return false
        if (idObservType != other.idObservType) return false
        if (idHeightType != other.idHeightType) return false
        if (!evidences.contentEquals(other.evidences)) return false
        if (observations != other.observations) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + transect.hashCode()
        result = 31 * result + idZoneType
        result = 31 * result + idAnimalType
        result = 31 * result + animalName.hashCode()
        result = 31 * result + scientificName.hashCode()
        result = 31 * result + quantity
        result = 31 * result + idObservType
        result = 31 * result + idHeightType
        result = 31 * result + evidences.contentHashCode()
        result = 31 * result + observations.hashCode()
        return result
    }
}

@Entity(tableName = "zone_type")
data class ZoneTypeEntity(
    @PrimaryKey
    val id: Int = 0,
    var zoneType: String = "",
)

@Entity(tableName = "animal_type")
data class AnimalTypeEntity(
    @PrimaryKey
    val id: Int = 0,
    var animalType: String = "",
)

@Entity(tableName = "observation_type")
data class ObservTypeEntity(
    @PrimaryKey
    val id: Int = 0,
    var observType: String = "",
)

@Entity(tableName = "height_type")
data class HeightTypeEntity(
    @PrimaryKey
    val id: Int = 0,
    var heightType: String = "",
)


@Entity(tableName = "follow_up_form"/*,
    foreignKeys = [
        ForeignKey(
            entity = GeneralFormEntity::class,
            parentColumns = ["id"],
            childColumns = ["idGeneralForm"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CoverageEntity::class,
            parentColumns = ["id"],
            childColumns = ["idCoverage"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DisturbanceEntity::class,
            parentColumns = ["id"],
            childColumns = ["idDisturbance"],
            onDelete = ForeignKey.CASCADE
        )
    ]*/)
data class FollowUpFormEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var followUp: Boolean = false,
    var change: Boolean = false,
    var idCoverage: Int, // Foreign Key
    var cropType: String = "",
    var idDisturbance: Int, // Foreign Key
    var evidences: ByteArray = byteArrayOf(),
    var observations: String = "" // <- AGREGO MARIA
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FollowUpFormEntity

        if (id != other.id) return false
        if (followUp != other.followUp) return false
        if (change != other.change) return false
        if (idCoverage != other.idCoverage) return false
        if (cropType != other.cropType) return false
        if (idDisturbance != other.idDisturbance) return false
        if (!evidences.contentEquals(other.evidences)) return false
        if (observations != other.observations) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + followUp.hashCode()
        result = 31 * result + change.hashCode()
        result = 31 * result + idCoverage
        result = 31 * result + cropType.hashCode()
        result = 31 * result + idDisturbance
        result = 31 * result + evidences.contentHashCode()
        result = 31 * result + observations.hashCode()
        return result
    }
}

@Entity(tableName = "coverage")
data class CoverageEntity(
    @PrimaryKey
    val id: Int = 0,
    var coverage: String = "" //char(2)
)

@Entity(tableName = "disturbance")
data class DisturbanceEntity(
    @PrimaryKey
    val id: Int = 0,
    var disturbance: String = ""
)


@Entity(tableName = "quadrant_form"/*,
    foreignKeys = [
        ForeignKey(
            entity = GeneralFormEntity::class,
            parentColumns = ["id"],
            childColumns = ["idGeneralForm"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SuperQuadrantEntity::class,
            parentColumns = ["id"],
            childColumns = ["idSuperQuadrant"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MidQuadrantEntity::class,
            parentColumns = ["id"],
            childColumns = ["idMidQuadrant"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SubQuadrantEntity::class,
            parentColumns = ["id"],
            childColumns = ["idSubQuadrant"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = HabitatEntity::class,
            parentColumns = ["id"],
            childColumns = ["idHabitat"],
            onDelete = ForeignKey.CASCADE
        )
    ]*/)
data class QuadrantFormEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var idSuperQuadrant: Int, // Foreign Key
    var idMidQuadrant: Int, // Foreign Key
    var idSubQuadrant: Int, // Foreign Key
    var specieName: String = "",
    var scientificName: String = "",
    var placa  : String = "",
    var idHabitat: Int, // Foreign Key
    var circumference: Int = 0, // decimal(2)
    var biomonitorMtSize: Int = 0, // decimal(2)
    var distanceMt: Int = 0, // decimal(2)
    var observations: String = "",
    var heightMt: Int = 0, // decimal(2)
    var evidences: ByteArray = byteArrayOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as QuadrantFormEntity

        if (id != other.id) return false
        if (idSuperQuadrant != other.idSuperQuadrant) return false
        if (idMidQuadrant != other.idMidQuadrant) return false
        if (idSubQuadrant != other.idSubQuadrant) return false
        if (specieName != other.specieName) return false
        if (scientificName != other.scientificName) return false
        if (placa != other.placa) return false
        if (idHabitat != other.idHabitat) return false
        if (circumference != other.circumference) return false
        if (biomonitorMtSize != other.biomonitorMtSize) return false
        if (distanceMt != other.distanceMt) return false
        if (observations != other.observations) return false
        if (heightMt != other.heightMt) return false
        if (!evidences.contentEquals(other.evidences)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + idSuperQuadrant
        result = 31 * result + idMidQuadrant
        result = 31 * result + idSubQuadrant
        result = 31 * result + specieName.hashCode()
        result = 31 * result + scientificName.hashCode()
        result = 31 * result + placa.hashCode()
        result = 31 * result + idHabitat
        result = 31 * result + circumference
        result = 31 * result + biomonitorMtSize
        result = 31 * result + distanceMt
        result = 31 * result + observations.hashCode()
        result = 31 * result + heightMt
        result = 31 * result + evidences.contentHashCode()
        return result
    }
}

@Entity(tableName = "superior_quadrant")
data class SuperQuadrantEntity(
    @PrimaryKey
    val id: Int = 0,
    var quadrant: String = ""
)

@Entity(tableName = "intermediate_quadrant")
data class MidQuadrantEntity(
    @PrimaryKey
    val id: Int = 0,
    var quadrant: String = ""
)

@Entity(tableName = "sub_quadrant")
data class SubQuadrantEntity(
    @PrimaryKey
    val id: Int = 0,
    var subQuadrant: String = ""
)

@Entity(tableName = "habitat")
data class HabitatEntity(
    @PrimaryKey
    val id: Int = 0,
    var habitatType: String = ""
)


@Entity(tableName = "route_form"/*,
    foreignKeys = [
        ForeignKey(
            entity = GeneralFormEntity::class,
            parentColumns = ["id"],
            childColumns = ["idGeneralForm"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ZoneTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["idZoneType"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CameraEntity::class,
            parentColumns = ["id"],
            childColumns = ["idCamera"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CheckListEntity::class,
            parentColumns = ["id"],
            childColumns = ["idCheckList"],
            onDelete = ForeignKey.CASCADE
        )
    ]*/)
data class RouteFormEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var idZoneType: Int, // Foreign Key
    var nameCamara: String = "",
    var placaCamara: String = "",
    var guayaPlate: Int = 0,
    var fecha: Int = 0,
    var instalada: Int = 0,
    var memoria: Int = 0,
    var pruebaGateo: Int = 0,
    var programa: Int = 0,
    var prendida: Int = 0,
    var letreroCamara: Int = 0,
    var routeWidth: Int = 0, // decimal(2)
    var targetDistance: Int = 0, // decimal(2)
    var lensHeight: Int = 0, // decimal(2)
    var idCheckList: Int, // Foreign Key
    var evidences: ByteArray = byteArrayOf(),
    var observations: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RouteFormEntity

        if (id != other.id) return false
        if (idZoneType != other.idZoneType) return false
        if (nameCamara != other.nameCamara) return false
        if (placaCamara != other.placaCamara) return false
        if (guayaPlate != other.guayaPlate) return false
        if (fecha != other.fecha) return false
        if (instalada != other.instalada) return false
        if (memoria != other.memoria) return false
        if (pruebaGateo != other.pruebaGateo) return false
        if (programa != other.programa) return false
        if (prendida != other.prendida) return false
        if (letreroCamara != other.letreroCamara) return false
        if (routeWidth != other.routeWidth) return false
        if (targetDistance != other.targetDistance) return false
        if (lensHeight != other.lensHeight) return false
        if (idCheckList != other.idCheckList) return false
        if (!evidences.contentEquals(other.evidences)) return false
        if (observations != other.observations) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + idZoneType
        result = 31 * result + nameCamara.hashCode()
        result = 31 * result + placaCamara.hashCode()
        result = 31 * result + guayaPlate
        result = 31 * result + fecha.hashCode()
        result = 31 * result + instalada.hashCode()
        result = 31 * result + memoria.hashCode()
        result = 31 * result + pruebaGateo.hashCode()
        result = 31 * result + programa.hashCode()
        result = 31 * result + prendida.hashCode()
        result = 31 * result + letreroCamara.hashCode()
        result = 31 * result + routeWidth
        result = 31 * result + targetDistance
        result = 31 * result + lensHeight
        result = 31 * result + idCheckList
        result = 31 * result + evidences.contentHashCode()
        result = 31 * result + observations.hashCode()
        return result
    }
}

@Entity(tableName = "camera")
data class CameraEntity(
    @PrimaryKey
    val id: Int = 0,
    var name: String = "",
    var plate: Int = 0,
    var instalationDate: String = ""
)

@Entity(tableName = "check_list"/*,
    foreignKeys = [
        ForeignKey(
            entity = CheckEntity::class,
            parentColumns = ["id"],
            childColumns = ["idCheck"],
            onDelete = ForeignKey.CASCADE
        )
    ]*/)
data class CheckListEntity(
    @PrimaryKey
    val id: Int = 0,
    var idCheck: Int, // Foreign Key
)

@Entity(tableName = "checks")
data class CheckEntity(
    @PrimaryKey
    val id: Int = 0,
    var name: String = "",
    var state: Boolean = false
)


@Entity(tableName = "weather_form"/*,
    foreignKeys = [
        ForeignKey(
            entity = GeneralFormEntity::class,
            parentColumns = ["id"],
            childColumns = ["idGeneralForm"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ZoneTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["idZoneType"],
            onDelete = ForeignKey.CASCADE
        )
    ]*/)
data class WeatherFormEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var idZoneType: Int, // Foreign Key
    var rainfall: Double = 0.0, // decimal(2)
    var maxTemperature: Double = 0.0, // decimal(2)
    var maxHumidity: Double = 0.0, // decimal(2)
    var minTemperature: Double = 0.0, // decimal(2)
    var minHumidity: Double = 0.0, // decimal(2)
    var streamMtLevel: Double = 0.0, // decimal(2)
    var evidences: ByteArray = byteArrayOf(),
    var observations: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WeatherFormEntity

        if (id != other.id) return false
        if (idZoneType != other.idZoneType) return false
        if (rainfall != other.rainfall) return false
        if (maxTemperature != other.maxTemperature) return false
        if (maxHumidity != other.maxHumidity) return false
        if (minTemperature != other.minTemperature) return false
        if (minHumidity != other.minHumidity) return false
        if (streamMtLevel != other.streamMtLevel) return false
        if (!evidences.contentEquals(other.evidences)) return false
        if (observations != other.observations) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + idZoneType
        result = 31 * result + rainfall.hashCode()
        result = 31 * result + maxTemperature.hashCode()
        result = 31 * result + maxHumidity.hashCode()
        result = 31 * result + minTemperature.hashCode()
        result = 31 * result + minHumidity.hashCode()
        result = 31 * result + streamMtLevel.hashCode()
        result = 31 * result + evidences.contentHashCode()
        result = 31 * result + observations.hashCode()
        return result
    }
}
@Entity(tableName = "punto_conteo")
data class PuntoConteoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var idZoneType: Int, // Foreign Key
    var idAnimalType: Int, // Foreign Key
    var animalName: String = "",
    var scientificName: String = "",
    var quantity: Int = 0,
    var idObservType: Int, // Foreign Key
    var idHeightType: Int, // Foreign Key
    var evidences: ByteArray = byteArrayOf(),
    var observations: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PuntoConteoEntity

        if (id != other.id) return false
        if (idZoneType != other.idZoneType) return false
        if (idAnimalType != other.idAnimalType) return false
        if (animalName != other.animalName) return false
        if (scientificName != other.scientificName) return false
        if (quantity != other.quantity) return false
        if (idObservType != other.idObservType) return false
        if (idHeightType != other.idHeightType) return false
        if (!evidences.contentEquals(other.evidences)) return false
        if (observations != other.observations) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + idZoneType
        result = 31 * result + idAnimalType
        result = 31 * result + animalName.hashCode()
        result = 31 * result + scientificName.hashCode()
        result = 31 * result + quantity
        result = 31 * result + idObservType
        result = 31 * result + idHeightType
        result = 31 * result + evidences.contentHashCode()
        result = 31 * result + observations.hashCode()
        return result
    }
}
@Entity(tableName = "busqueda_libre")
data class BusquedaLibreEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var idZoneType: Int, // Foreign Key
    var idAnimalType: Int, // Foreign Key
    var animalName: String = "",
    var scientificName: String = "",
    var quantity: Int = 0,
    var idObservType: Int, // Foreign Key
    var idHeightType: Int, // Foreign Key
    var evidences: ByteArray = byteArrayOf(),
    var observations: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BusquedaLibreEntity

        if (id != other.id) return false
        if (idZoneType != other.idZoneType) return false
        if (idAnimalType != other.idAnimalType) return false
        if (animalName != other.animalName) return false
        if (scientificName != other.scientificName) return false
        if (quantity != other.quantity) return false
        if (idObservType != other.idObservType) return false
        if (idHeightType != other.idHeightType) return false
        if (!evidences.contentEquals(other.evidences)) return false
        if (observations != other.observations) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + idZoneType
        result = 31 * result + idAnimalType
        result = 31 * result + animalName.hashCode()
        result = 31 * result + scientificName.hashCode()
        result = 31 * result + quantity
        result = 31 * result + idObservType
        result = 31 * result + idHeightType
        result = 31 * result + evidences.contentHashCode()
        result = 31 * result + observations.hashCode()
        return result
    }
}