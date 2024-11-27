package com.example.koadex.data

import android.graphics.Bitmap
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
    var type: String = "",
)

@Entity(tableName = "season")
data class SeasonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var type: String = "",
)

@Entity(tableName = "zone")
data class ZoneEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var type: String = "",
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
    var evidences: String,
    var observations: String
)

@Entity(tableName = "zone_type")
data class ZoneTypeEntity(
    @PrimaryKey
    val id: Int = 0,
    var typeName: String = "",
)

@Entity(tableName = "animal_type")
data class AnimalTypeEntity(
    @PrimaryKey
    val id: Int = 0,
    var typeName: String = "",
)

@Entity(tableName = "observation_type")
data class ObservTypeEntity(
    @PrimaryKey
    val id: Int = 0,
    var typeName: String = "",
)

@Entity(tableName = "height_type")
data class HeightTypeEntity(
    @PrimaryKey
    val id: Int = 0,
    var typeName: String = "",
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
    var evidences: String,
    var observations: String = "" // <- AGREGO MARIA
)

@Entity(tableName = "coverage")
data class CoverageEntity(
    @PrimaryKey
    val id: Int = 0,
    var type: String = "" //char(2)
)

@Entity(tableName = "disturbance")
data class DisturbanceEntity(
    @PrimaryKey
    val id: Int = 0,
    var type: String = ""
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
    var idHabitat: Int, // Foreign Key
    var circumference: Int = 0, // decimal(2)
    var biomonitorMtSize: Int = 0, // decimal(2)
    var distanceMt: Int = 0, // decimal(2)
    var observations: String = "",
    var heightMt: Int = 0, // decimal(2)
    var evidences: String
)

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
    var quadrant: String = ""
)

@Entity(tableName = "habitat")
data class HabitatEntity(
    @PrimaryKey
    val id: Int = 0,
    var type: String = ""
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
    var idCamera: Int, // Foreign Key
    var guayaPlate: Int = 0,
    var routeWidth: Int = 0, // decimal(2)
    var targetDistance: Int = 0, // decimal(2)
    var lensHeight: Int = 0, // decimal(2)
    var idCheckList: Int, // Foreign Key
    var evidences: String,
    var observations: String = ""
)

@Entity(tableName = "camera")
data class CameraEntity(
    @PrimaryKey
    val id: Int = 0,
    var name: String = "",
    var plate: Int = 0,
    var instalationDate: String = ""
)

@Entity(tableName = "check_list",
    foreignKeys = [
        ForeignKey(
            entity = CheckEntity::class,
            parentColumns = ["id"],
            childColumns = ["idCheck"],
            onDelete = ForeignKey.CASCADE
        )
    ])
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
    var evidences: String = "", // <- AGREGO MARIA
    var observations: String = "" // <- AGREGO MARIA
)
