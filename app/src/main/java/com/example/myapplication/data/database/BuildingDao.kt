package com.example.myapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BuildingDao {

    @Query("SELECT * FROM building_table WHERE address=:address")
    suspend fun getBuilding(address: String): BuildingDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuilding(building: BuildingDbModel)

}