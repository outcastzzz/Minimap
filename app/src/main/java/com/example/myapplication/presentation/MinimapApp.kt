package com.example.myapplication.presentation

import android.app.Application
import com.example.myapplication.di.DaggerApplicationComponent

class MinimapApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }


}