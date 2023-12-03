package com.example.myapplication.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RouteDto (
    @SerializedName("floor")
    @Expose
    val floors: List<Int>,
    @SerializedName("maps")
    @Expose
    val maps: List<String>
)