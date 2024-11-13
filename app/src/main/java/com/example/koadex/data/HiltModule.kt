package com.example.koadex.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.text.Normalizer.Form
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): FormDatabase {
        return Room.databaseBuilder(
            appContext,
            FormDatabase::class.java,
            "form_database"
        ).build()
    }

    @Provides
    fun provideMyDao(appDatabase: FormDatabase): FormDao {
        return appDatabase.formDao()
    }
}