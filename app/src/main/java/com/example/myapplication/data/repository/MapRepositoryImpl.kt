package com.example.myapplication.data.repository

import com.example.myapplication.data.database.BuildingDao
import com.example.myapplication.data.mapper.BuildingMapper
import com.example.myapplication.data.mapper.RoomMapper
import com.example.myapplication.data.network.ApiService
import com.example.myapplication.data.network.model.RouteDto
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Route
import com.example.myapplication.domain.repository.MapRepository
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val buildingDao: BuildingDao,
    private val rMapper: RoomMapper,
    private val bMapper: BuildingMapper
): MapRepository {

    override suspend fun getListOfRooms(buildingName: String): List<String> {
        return apiService.getListOfRooms(buildingName).rooms
    }

    override suspend fun getRoute(
        buildingName: String,
        roomNameStart: String,
        roomNameEnd: String
    ): Route {
        val response = apiService.getRouteByName(buildingName, roomNameStart, roomNameEnd)
        return rMapper.mapRouteDtoToEntity(response)
    }

    override suspend fun getListOfBuildings(): List<String> {
        return apiService.getListOfBuilding().buildings
    }

    override suspend fun getBuildingByName(buildingName: String): Building {
        val buildingInfo = apiService.getBuildingByName(buildingName = buildingName)
        val buildingDbModel = bMapper.mapDtoToDbModel(buildingInfo)
        buildingDao.insertBuilding(buildingDbModel)
        val entity = buildingDao.getBuilding()
        return bMapper.mapDbModelToEntity(entity)
    }

}