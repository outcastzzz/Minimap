package com.example.myapplication.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "building_table")
data class BuildingDto (
    @PrimaryKey
    @SerializedName("build")
    @Expose
    val address: String,
    @SerializedName("maps")
    @Expose
    val floors: List<String>
)