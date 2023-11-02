package com.example.myapplication.data.network

import com.example.myapplication.data.network.model.BuildingDto
import com.example.myapplication.data.network.model.MapDto
import com.example.myapplication.data.network.model.RoomDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("build")
    suspend fun getBuildingInfo(): BuildingDto

    @GET("roomInfo")
    suspend fun getRoomInfo(): RoomDto

    @GET("mapInfo")
    suspend fun getMapInfo(): MapDto

}