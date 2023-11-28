package com.example.myapplication.domain.useCases

import com.example.myapplication.domain.repository.MapRepository
import javax.inject.Inject

class GetBuildingByNameUseCase @Inject constructor(
    private val repository: MapRepository
) {

    suspend fun getBuildingByName(buildingName: String) = repository.getBuildingByName(buildingName)

}