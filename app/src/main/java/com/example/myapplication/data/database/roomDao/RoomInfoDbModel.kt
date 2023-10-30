package com.example.myapplication.data.database.roomDao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_list")
data class RoomInfoDbModel (
    @PrimaryKey
    val id: Int? = 0,
    val name: String,
    val title: String?,
)