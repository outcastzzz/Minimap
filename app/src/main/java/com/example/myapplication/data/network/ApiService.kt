package com.example.myapplication.data.network

import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Map
import retrofit2.http.GET

interface ApiService {

    @GET("3uea1Q")
    suspend fun getAllBuildings(): retrofit2.Response<Building>
    @GET("")
    suspend fun getMapImage(buildingId: Int): Map

    @GET("")
    suspend fun getAllRooms(buildingId: Int): RoomList

}