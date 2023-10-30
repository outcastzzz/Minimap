package com.example.myapplication.data.database.buildDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.domain.entities.Building


@Dao
interface BuildingInfoDao {

//    @Query()
    fun getListOfBuildings(): LiveData<List<Building>>

//    @Query()
    fun getInfoAboutRoom(id: Int): LiveData<BuildingInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuildingList(buildList: List<BuildingInfoDbModel>)
}