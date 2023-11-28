package com.example.myapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room")
data class RoomDbModel (
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val name: String,
    val maps: String,
    val floor: Int
)