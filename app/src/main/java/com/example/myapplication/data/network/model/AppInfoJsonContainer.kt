package com.example.myapplication.data.network.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AppInfoJsonContainer (
    @SerializedName("RAW")
    @Expose
    val json: JsonObject? = null
)