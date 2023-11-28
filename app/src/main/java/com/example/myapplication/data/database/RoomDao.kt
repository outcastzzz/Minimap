package com.example.myapplication.data.database

import androidx.lifecycle.LiveData

interface RoomDao {

    suspend fun getRoom(): LiveData<RoomDbModel>

    suspend fun insertRoom(room: RoomDbModel)

}