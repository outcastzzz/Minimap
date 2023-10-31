package com.example.myapplication.data.network.model.mapDto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "imageUrl")
data class MapDto (
    @SerializedName("IMGURL")
    @Expose
    val imgUrl: String?,
    @SerializedName("ID")
    @Expose
    val id: String
)