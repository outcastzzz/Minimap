package com.example.myapplication.data.database.roomDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomInfoDao {

    @Query("SELECT * FROM room_list ORDER BY id DESC")
    fun getRoomList(): LiveData<List<RoomInfoDbModel>>

    @Query("SELECT * FROM room_list WHERE name == name LIMIT 1")
    fun getInfoAboutRoom(roomId: Int): LiveData<RoomInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoomList(roomList: List<RoomInfoDbModel>)
}