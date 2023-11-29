package com.example.myapplication.domain.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Room

interface MapRepository {

    suspend fun getListOfRooms(buildingName: String): List<String>

    suspend fun getRoomByName(roomName: String): Room

    suspend fun getListOfBuildings(): List<String>

    suspend fun getBuildingByName(buildingName: String): Building
}