package com.example.myapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "building_table")
data class BuildingDbModel(
    val rooms: List<String>,
    val mainMap: String,
    @PrimaryKey
    val address: String
)