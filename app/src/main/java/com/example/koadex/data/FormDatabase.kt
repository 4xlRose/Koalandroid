package com.example.koadex.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =
[
    GeneralFormEntity::class, UserEntity::class,FormStateEntity::class,
    WeatherEntity::class, SeasonEntity::class, ZoneEntity::class,
    SpecieFormEntity::class, ZoneTypeEntity::class, AnimalTypeEntity::class,
    ObservTypeEntity::class, HeightTypeEntity::class, FollowUpFormEntity::class,
    CoverageEntity::class, DisturbanceEntity::class, QuadrantFormEntity::class,
    SuperQuadrantEntity::class, MidQuadrantEntity::class, SubQuadrantEntity::class,
    HabitatEntity::class, RouteFormEntity::class, CameraEntity::class,
    CheckListEntity::class, CheckEntity::class, WeatherFormEntity::class
],

    version = 11,
    exportSchema = false
)
abstract class FormDatabase : RoomDatabase() {
    abstract fun formDao(): FormDao

    companion object {
        @Volatile
        private var Instance: FormDatabase? = null

        fun getDatabase(context: Context): FormDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FormDatabase::class.java, "Koadex_DB")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}