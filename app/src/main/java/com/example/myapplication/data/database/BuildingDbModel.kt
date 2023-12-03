package com.example.myapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "building_table")
data class BuildingDbModel(
    @PrimaryKey
    val address: String,
    val floors: List<String>,
)