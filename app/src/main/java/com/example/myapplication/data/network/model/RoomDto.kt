package com.example.myapplication.data.network.model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "room")
data class RoomDto (
    @SerializedName("")
    @Expose
    val id: String,
    @SerializedName("")
    @Expose
    val name: String,
    @SerializedName("")
    @Expose
    val maps: String,
    @SerializedName("")
    @Expose
    val floor: Int
)