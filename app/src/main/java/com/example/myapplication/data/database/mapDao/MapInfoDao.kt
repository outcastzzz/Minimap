package com.example.myapplication.data.database.mapDao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface MapInfoDao {

    @Query("SELECT * FROM imageUrl")
    fun getMapImage(buildingId: Int): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: String)
}