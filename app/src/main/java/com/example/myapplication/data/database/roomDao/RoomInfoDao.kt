package com.example.myapplication.data.database.roomDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomInfoDao {

//    @Query()
//    fun getRoomList(): LiveData<List<RoomInfoDbModel>>

//    @Query()
//    fun getInfoAboutRoom(id: Int): LiveData<RoomInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoomList(roomList: List<RoomInfoDbModel>)
}