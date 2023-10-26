package com.example.myapplication.domain.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Point
import com.example.myapplication.domain.entities.Room

interface MapRepository {

    fun getListOfRooms(): LiveData<List<Room>>

    fun getRoomById(roomId: Int): Room

    fun getCurrentPoint(pointId: Int): Point

    fun changePoint(pointId: Int): Point

    fun loadData()

    fun getListOfBuildings(): LiveData<List<Building>>
}