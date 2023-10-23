package com.example.myapplication.domain.useCases

import androidx.lifecycle.LiveData
import com.example.myapplication.domain.entities.Room
import com.example.myapplication.domain.repository.MapRepository
import javax.inject.Inject

class GetListOfRoomsUseCase @Inject constructor(
    private val repository: MapRepository
) {

    fun getListOfRoom(): LiveData<List<Room>> {
        return repository.getListOfRooms()
    }

}