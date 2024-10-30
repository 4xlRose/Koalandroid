package com.example.koadex.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forms")
data class FormEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val date: String = "",
    val place: String = "",
    val hour: String = "",
    val weather: String = "",
    val season: String = ""
)
