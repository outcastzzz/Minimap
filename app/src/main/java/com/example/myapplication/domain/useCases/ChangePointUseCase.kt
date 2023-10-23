package com.example.myapplication.domain.useCases

import com.example.myapplication.domain.entities.Point
import com.example.myapplication.domain.repository.MapRepository
import javax.inject.Inject

class ChangePointUseCase @Inject constructor(
    private val repository: MapRepository
) {

    fun changePoint(pointId: Int): Point {
        return repository.changePoint(pointId)
    }

}