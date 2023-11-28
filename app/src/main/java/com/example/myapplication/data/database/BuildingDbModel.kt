package com.example.myapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "building")
data class BuildingDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val rooms: List<String>,
    val maps: List<String>,
    val address: String
)