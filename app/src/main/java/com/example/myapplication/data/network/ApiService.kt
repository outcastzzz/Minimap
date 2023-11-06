package com.example.myapplication.data.network

import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Map
import retrofit2.http.GET

interface ApiService {

    @GET("")
    suspend fun getAllBuildings(): BuildingsList

    @GET("")
    suspend fun getMapImage(buildingId: Int): Map

    @GET("")
    suspend fun getAllRooms(buildingId: Int): RoomList

}