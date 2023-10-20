package com.example.myapplication.domain.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.domain.entities.Room

interface MapRepository {

    fun getListOfRooms(): LiveData<List<Room>>

    fun getRoomById(): Room

    fun changePoint()

    fun loadData()

}