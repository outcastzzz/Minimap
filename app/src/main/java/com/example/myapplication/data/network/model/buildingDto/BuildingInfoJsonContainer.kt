package com.example.myapplication.data.network.model.buildingDto

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BuildingInfoJsonContainer (
    @SerializedName("RAW")
    @Expose
    val json: JsonObject? = null
)