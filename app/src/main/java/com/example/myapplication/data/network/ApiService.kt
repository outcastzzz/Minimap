package com.example.myapplication.data.network

import androidx.lifecycle.LiveData
import com.example.myapplication.data.network.model.BuildingDto
import com.example.myapplication.data.network.model.RoomDto
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Room
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("")
    suspend fun getListOfBuilding(): List<String>

    @GET("{buildingName}")
    suspend fun getBuildingByName(@Path("buildingName") buildingName: String): BuildingDto

    @GET("")
    suspend fun getListOfRooms(@Path("buildingName") buildingName: String): List<String>

    @GET("")
    suspend fun getRoomByName(@Path("roomName") roomName: String): RoomDto

}