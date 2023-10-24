package com.example.myapplication.domain.useCases

import com.example.myapplication.domain.entities.Point
import com.example.myapplication.domain.repository.MapRepository
import javax.inject.Inject

class GetCurrentPointUseCase @Inject constructor(
    private val repository: MapRepository
) {

    fun getCurrentPoint(pointId: Int): Point {
        return repository.getCurrentPoint(pointId)
    }

}