package com.example.myapplication.data.network.model.roomDto

import android.icu.text.CaseMap.Title
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "room_list")
data class RoomDto(
    @SerializedName("ID")
    @Expose
    val id: String?,
    @SerializedName("NAME")
    @Expose
    val name: String?,
    @SerializedName("TITLE")
    @Expose
    val title: String?,
)