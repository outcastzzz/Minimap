package com.example.myapplication.domain.useCases

import com.example.myapplication.domain.entities.Room
import com.example.myapplication.domain.repository.MapRepository
import javax.inject.Inject

class GetRoomByIdUseCase @Inject constructor (
    private val repository: MapRepository
) {

    fun getRoomById(roomId: Int): Room {
        return repository.getRoomById(roomId)
    }

}