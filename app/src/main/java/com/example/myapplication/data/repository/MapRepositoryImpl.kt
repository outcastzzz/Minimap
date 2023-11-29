package com.example.myapplication.data.repository

import com.example.myapplication.data.database.BuildingDao
import com.example.myapplication.data.database.RoomDao
import com.example.myapplication.data.mapper.BuildingMapper
import com.example.myapplication.data.mapper.RoomMapper
import com.example.myapplication.data.network.ApiService
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Room
import com.example.myapplication.domain.repository.MapRepository
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val roomDao: RoomDao,
    private val buildingDao: BuildingDao,
    private val rMapper: RoomMapper,
    private val bMapper: BuildingMapper
): MapRepository {

    override suspend fun getListOfRooms(buildingName: String): List<String> {
        return apiService.getListOfRooms(buildingName)
    }

    override suspend fun getRoomByName(roomName: String): Room {
        val roomInfo = apiService.getRoomByName(roomName)
        val dbModel = rMapper.mapRoomDtoToDbModel(roomInfo)
        roomDao.insertRoom(dbModel)
        val entity = roomDao.getRoom()
        return rMapper.mapRoomDbModelToEntity(entity)
    }

    override suspend fun getListOfBuildings(): List<String> {
        return apiService.getListOfBuilding()
    }

    override suspend fun getBuildingByName(buildingName: String): Building {
        val buildingInfo = apiService.getBuildingByName(buildingName)
        val buildingDbModel = bMapper.mapDtoToDbModel(buildingInfo)
        buildingDao.insertBuilding(buildingDbModel)
        val entity = buildingDao.getBuilding()
        return bMapper.mapDbModelToEntity(entity)
    }
}