package com.example.myapplication.domain.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Room

interface MapRepository {

    suspend fun getListOfRooms(building: Building): LiveData<List<String>>

    suspend fun getRoomByName(roomName: String): LiveData<Room>

    suspend fun getListOfBuildings(): LiveData<List<String>>

    suspend fun getBuildingByName(buildingName: String): LiveData<Building>
}