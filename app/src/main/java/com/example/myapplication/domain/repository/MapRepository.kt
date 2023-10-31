package com.example.myapplication.domain.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Room
import net.sourceforge.zbar.Image

interface MapRepository {

    fun getListOfRooms(): LiveData<List<Room>>

    fun getRoomById(roomId: String): Room

    fun loadData()

    fun getListOfBuildings(): LiveData<List<Building>>

    fun getMapImage(buildingId: Int): String
}