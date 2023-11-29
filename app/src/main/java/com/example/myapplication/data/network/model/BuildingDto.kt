package com.example.myapplication.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "building_table")
data class BuildingDto (
    @SerializedName("")
    @Expose
    val rooms: List<String>,
    @SerializedName("")
    @Expose
    val mainMap: String,
    @PrimaryKey
    @SerializedName("")
    @Expose
    val address: String
)