package com.example.myapplication.presentation.mapScreen

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.domain.useCases.GetMapImageUseCase
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val getMapImageUseCase: GetMapImageUseCase,
    private val application: Application
): AndroidViewModel(application) {

//    private val buildingId: Int = 0
//
//    private val mapImage = getMapImageUseCase.getMapImage(buildingId)
//
//    private fun setImage() {
//        val id = application.applicationContext.resources.getIdentifier(mapImage, "drawable", application.packageName)
//        val draw: Drawable? = ContextCompat.getDrawable(application.applicationContext, id)
//    }

}