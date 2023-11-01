package com.example.myapplication.data.network.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MapDto (
    @SerializedName("IMGURL")
    @Expose
    val imgUrl: String,
    @SerializedName("ID")
    @Expose
    val id: String
)