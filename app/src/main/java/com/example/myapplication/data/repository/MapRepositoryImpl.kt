package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.domain.entities.Room
import com.example.myapplication.domain.repository.MapRepository

class MapRepositoryImpl(

): MapRepository {

    override fun getListOfRooms(): LiveData<List<Room>> {
        TODO()
    }

    override fun getRoomById(): Room {
        TODO()
    }

    override fun changePoint() {
        TODO()
    }

    override fun loadData() {
        TODO()
    }

}