package com.example.myapplication.domain.useCases

import com.example.myapplication.domain.repository.MapRepository
import javax.inject.Inject

class GetMapImageUseCase @Inject constructor(
    private val repository: MapRepository
) {

    fun getMapImage(buildingId: Int): String {
        return repository.getMapImage(buildingId)
    }

}