package com.example.myapplication.domain.useCases

import androidx.lifecycle.LiveData
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.repository.MapRepository
import javax.inject.Inject

class GetListOfBuildingsUseCase @Inject constructor(
    private val repository: MapRepository
) {
    suspend fun getListOfBuildings() = repository.getListOfBuildings()


}