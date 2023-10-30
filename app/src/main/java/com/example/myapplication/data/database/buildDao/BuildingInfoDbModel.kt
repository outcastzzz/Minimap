package com.example.myapplication.data.database.buildDao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "building_list")
data class BuildingInfoDbModel (
    @PrimaryKey
    val id: String? = "",
    val name: String?,
    val title: String?,
)