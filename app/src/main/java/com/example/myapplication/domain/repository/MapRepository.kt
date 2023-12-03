package com.example.myapplication.domain.repository

import com.example.myapplication.data.network.model.RouteDto
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Route

interface MapRepository {

    suspend fun getListOfRooms(buildingName: String): List<String>

    suspend fun getRoute(
        buildingName: String,
        roomNameStart: String,
        roomNameEnd: String
    ): Route

    suspend fun getListOfBuildings(): List<String>

    suspend fun getBuildingByName(buildingName: String): Building

}