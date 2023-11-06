package com.example.myapplication.presentation.mapScreen

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.domain.repository.MapRepository
import com.example.myapplication.domain.useCases.GetMapImageUseCase
import java.lang.reflect.Field
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val getMapImageUseCase: GetMapImageUseCase,
    private val buildingId: Int,
    private val application: Application
): AndroidViewModel(application) {

    val mapImage = getMapImageUseCase.getMapImage(buildingId)

    private fun setImage() {
        val id = application.applicationContext.resources.getIdentifier(mapImage, "drawable", application.packageName)
        val draw: Drawable? = ContextCompat.getDrawable(application.applicationContext, id)
    }

}