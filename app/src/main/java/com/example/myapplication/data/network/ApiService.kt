package com.example.myapplication.data.network

import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Map
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("Gb9HGF")
    suspend fun getAllBuildings(): retrofit2.Response<List<Building>>

    @GET("")
    suspend fun getMapImage(buildingId: Int): Map

    @GET("")
    suspend fun getAllRooms(buildingId: Int): RoomList

}