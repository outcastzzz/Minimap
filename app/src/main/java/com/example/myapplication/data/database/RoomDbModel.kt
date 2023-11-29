package com.example.myapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_table")
data class RoomDbModel (
    @PrimaryKey
    val name: String,
    val maps: List<String>,
    val floor: Int
)