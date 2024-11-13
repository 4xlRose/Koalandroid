package com.example.koadex.data

import android.health.connect.datatypes.units.Temperature
import android.icu.text.DateFormat
import android.media.Image
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.DatePicker
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo
import java.sql.Date
import java.sql.Time
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName = "list_forms",
    foreignKeys = [
        ForeignKey(
            entity = GeneralFormEntity::class,
            parentColumns = ["id"],
            childColumns = ["idGeneralForm"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class ListFormsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Date = Date(20241111),
    val state: Boolean = false,
    val idGeneralForm: Int? // Foreign Key
)


@Entity(tableName = "general_form",
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
    ])
data class GeneralFormEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Date = Date(20241111),
    val hour: Time = Time(451),
    val serialCode: String = "",
    val idUser: Int, // Foreign Key
    val idWeather: Int, // Foreign Key
    val idSeason: Int, // Foreign Key
    val idSpecieForm: Int, // Foreign Key
    val idFollowUpForm: Int, // Foreign Key
    val idQuadrantForm: Int, // Foreign Key
    val idRouteForm: Int, // Foreign Key
    val idWeatherForm: Int // Foreign Key
)

@Entity(tableName = "user",
    foreignKeys = [
        ForeignKey(
            entity = ZoneEntity::class,
            parentColumns = ["id"],
            childColumns = ["idZone"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val startDate: Date = Date(20241111),
    val idZone: Int, // Foreign Key
)

@Entity(tableName = "weather")
data class WeatherEntity(
    val id: Int = 0,
    val weather: String = "",
)

@Entity(tableName = "season")
data class SeasonEntity(
    val id: Int = 0,
    val season: String = "",
)

@Entity(tableName = "zone")
data class ZoneEntity(
    val id: Int = 0,
    val zone: String = "",
)


@Entity(tableName = "specie_form",
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
    ])
data class SpecieFormEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idGeneralForm: Int, // Foreign Key
    val transect: String = "",
    val idZoneType: Int, // Foreign Key
    val idAnimalType: Int, // Foreign Key
    val animalName: String = "",
    val scientificName: String = "",
    val quantity: Int = 0,
    val idObservType: Int, // Foreign Key
    val idHeightType: Int, // Foreign Key
    val evidences: ImageVector,
    val observations: String
)

@Entity(tableName = "zone_type")
data class ZoneTypeEntity(
    val id: Int = 0,
    val zoneType: String = "",
)

@Entity(tableName = "animal_type")
data class AnimalTypeEntity(
    val id: Int = 0,
    val animalType: String = "",
)

@Entity(tableName = "observation_type")
data class ObservTypeEntity(
    val id: Int = 0,
    val observType: String = "",
)

@Entity(tableName = "height_type")
data class HeightTypeEntity(
    val id: Int = 0,
    val heightType: String = "",
)


@Entity(tableName = "follow_up_form",
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
    ])
data class FollowUpFormEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idGeneralForm: Int, // Foreign Key
    val followUp: Boolean = false,
    val change: Boolean = false,
    val idCoverage: Int, // Foreign Key
    val cropType: String = "",
    val idDisturbance: Int, // Foreign Key
    val evidences: ImageVector
)

@Entity(tableName = "coverage")
data class CoverageEntity(
    val id: Int = 0,
    val coverage: String = "" //char(2)
)

@Entity(tableName = "disturbance")
data class DisturbanceEntity(
    val id: Int = 0,
    val disturbance: String = ""
)


@Entity(tableName = "quadrant_form",
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
    ])
data class QuadrantFormEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idGeneralForm: Int, // Foreign Key
    val idSuperQuadrant: Int, // Foreign Key
    val idMidQuadrant: Int, // Foreign Key
    val idSubQuadrant: Int, // Foreign Key
    val specieName: String = "",
    val scientificName: String = "",
    val idHabitat: Int, // Foreign Key
    val circumference: Int = 0, // decimal(2)
    val biomonitorMtSize: Int = 0, // decimal(2)
    val distanceMt: Int = 0, // decimal(2)
    val observations: String = "",
    val heightMt: Int = 0, // decimal(2)
    val evidences: ImageVector
)

@Entity(tableName = "superior_quadrant")
data class SuperQuadrantEntity(
    val id: Int = 0,
    val quadrant: Char = ' '
)

@Entity(tableName = "intermediate_quadrant")
data class MidQuadrantEntity(
    val id: Int = 0,
    val quadrant: Char = ' '
)

@Entity(tableName = "sub_quadrant")
data class SubQuadrantEntity(
    val id: Int = 0,
    val subQuadrant: Char = ' '
)

@Entity(tableName = "habitat")
data class HabitatEntity(
    val id: Int = 0,
    val habitatType: String = ""
)


@Entity(tableName = "route_form",
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
    ])
data class RouteFormEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idGeneralForm: Int, // Foreign Key
    val idZoneType: Int, // Foreign Key
    val idCamera: Int, // Foreign Key
    val guayaPlate: Int = 0,
    val routeWidth: Int = 0, // decimal(2)
    val targetDistance: Int = 0, // decimal(2)
    val lensHeight: Int = 0, // decimal(2)
    val idCheckList: Int, // Foreign Key
    val evidences: ImageVector,
    val observations: String = ""
)

@Entity(tableName = "camera")
data class CameraEntity(
    val id: Int = 0,
    val name: String = "",
    val plate: Int = 0,
    val instalationDate: Date = Date(20241111)
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
    val id: Int = 0,
    val idCheck: Int, // Foreign Key
)

@Entity(tableName = "check")
data class CheckEntity(
    val id: Int = 0,
    val name: String = "",
    val state: Boolean = false
)


@Entity(tableName = "weather_form",
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
    ])
data class WeatherFormEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idGeneralForm: Int, // Foreign Key
    val idZoneType: Int, // Foreign Key
    val rainfall: Int = 0, // decimal(2)
    val maxTemperature: Int = 0, // decimal(2)
    val maxHumidity: Int = 0, // decimal(2)
    val minTemperature: Int = 0, // decimal(2)
    val streamMtLevel: Int = 0 // decimal(2)
)