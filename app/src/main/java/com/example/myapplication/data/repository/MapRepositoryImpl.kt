package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.network.ApiService
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Room
import com.example.myapplication.domain.repository.MapRepository
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val apiService: ApiService

): MapRepository {

    override suspend fun getListOfRooms(building: Building): LiveData<List<String>> {

    }

    override suspend fun getRoomByName(roomName: String): LiveData<Room> {

    }

    override suspend fun getListOfBuildings(): LiveData<List<String>> {
        return apiService.getListOfBuilding()
    }

    override suspend fun getBuildingByName(buildingName: String): LiveData<Building> {
        val response = apiService.getBuildingByName(buildingName)
    }
}