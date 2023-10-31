package com.example.myapplication.data.database.buildDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.domain.entities.Building


@Dao
interface BuildingInfoDao {

    @Query("SELECT * FROM building_list ORDER BY id DESC")
    fun getListOfBuildings(): LiveData<List<Building>>

    @Query("SELECT * FROM building_list WHERE name == name LIMIT 1")
    fun getInfoAboutRoom(id: Int): LiveData<BuildingInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuildingList(buildList: List<BuildingInfoDbModel>)
}