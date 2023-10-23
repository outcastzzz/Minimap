package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.domain.entities.Point
import com.example.myapplication.domain.entities.Room
import com.example.myapplication.domain.repository.MapRepository

class MapRepositoryImpl(

): MapRepository {

    override fun getListOfRooms(): LiveData<List<Room>> {
        TODO()
    }

    override fun getRoomById(roomId: Int): Room {
        TODO()
    }

    override fun changePoint(pointId: Int): Point {
        TODO()
    }

    override fun getCurrentPoint(pointId: Int): Point {
        TODO()
    }

    override fun loadData() {
        TODO()
    }

}