package com.example.myapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BuildingDao {

    @Query("SELECT * FROM building_table")
    suspend fun getBuilding(): BuildingDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuilding(building: BuildingDbModel)

}