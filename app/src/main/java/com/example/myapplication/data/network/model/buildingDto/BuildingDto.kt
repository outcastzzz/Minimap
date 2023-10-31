package com.example.myapplication.data.network.model.buildingDto

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "building_list")
data class BuildingDto (
    @SerializedName("ID")
    @Expose
    val id: String?,
    @SerializedName("NAME")
    @Expose
    val name: String?,
    @SerializedName("TITLE")
    @Expose
    val title: String?,
    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String?
)