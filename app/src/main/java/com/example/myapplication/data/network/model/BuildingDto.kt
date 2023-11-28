package com.example.myapplication.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "building")
data class BuildingDto (
    @SerializedName("")
    @Expose
    @PrimaryKey
    val id: Int,
    @SerializedName("")
    @Expose
    val rooms: List<String>,
    @SerializedName("")
    @Expose
    val maps: List<String>,
    @SerializedName("")
    @Expose
    val address: String
)