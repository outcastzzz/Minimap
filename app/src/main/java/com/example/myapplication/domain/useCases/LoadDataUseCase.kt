package com.example.myapplication.domain.useCases

import com.example.myapplication.domain.repository.MapRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: MapRepository
) {

    fun loadData(repository: MapRepository) {

        repository.loadData()

    }

}