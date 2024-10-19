package com.example.koadex.localdata
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FormEntity::class], version = 1, exportSchema = false)
abstract class FormDatabase : RoomDatabase() {
    abstract fun formDao(): FormDao

    companion object {
        @Volatile
        private var Instance: FormDatabase? = null

        fun getDatabase(context: Context): FormDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FormDatabase::class.java, "form_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}