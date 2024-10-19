package com.example.koadex.data

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room

@Database(entities = [Formulario::class], version = 1, exportSchema = false)
abstract class FormsDatabase : RoomDatabase() {
    abstract fun formGeneralDao() : FormGeneralDao

    companion object {
        @Volatile
        private var Instance: FormsDatabase? = null

        fun getDatabase(context: Context): FormsDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FormsDatabase::class.java,"form_database")
                    .build().also { Instance = it}

            }
        }
    }
}