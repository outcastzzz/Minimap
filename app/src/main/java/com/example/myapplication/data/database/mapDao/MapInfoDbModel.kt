package com.example.myapplication.data.database.mapDao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imageUrl")
data class MapInfoDbModel(
    @PrimaryKey
    val id: String = "",
    val imgUrl: String
)