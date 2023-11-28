package com.example.myapplication.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.example.myapplication.domain.entities.Building

@Dao
interface BuildingDao {

    suspend fun getBuilding(buildingName: String): LiveData<BuildingDbModel>

    suspend fun insertBuilding(building: BuildingDbModel)

}