package com.example.myapplication.data.database.buildDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.example.myapplication.domain.entities.Building

@Dao
interface BuildingInfoDao {

    fun getListOfBuildings(): LiveData<List<Building>>
}