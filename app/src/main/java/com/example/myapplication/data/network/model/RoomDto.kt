package com.example.myapplication.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "room_table")
data class RoomDto (
    @PrimaryKey
    @SerializedName("")
    @Expose
    val name: String,
    @SerializedName("")
    @Expose
    val maps: List<String>,
    @SerializedName("")
    @Expose
    val floor: Int
)