package com.example.myapplication.domain.useCases

import androidx.lifecycle.LiveData
import com.example.myapplication.domain.repository.MapRepository
import net.sourceforge.zbar.Image
import javax.inject.Inject

class getMapImageUseCase @Inject constructor(
    private val repository: MapRepository
) {

    fun getMapImage(buildingId: Int): String {
        return repository.getMapImage(buildingId)
    }

}