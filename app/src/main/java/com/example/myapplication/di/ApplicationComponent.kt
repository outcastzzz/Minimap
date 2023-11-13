package com.example.myapplication.di

import android.app.Application
import com.example.myapplication.presentation.MinimapApp
import com.example.myapplication.presentation.MapFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
    ]
)
interface ApplicationComponent  {

    fun inject(fragment: MapFragment)

    fun inject(application: MinimapApp)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}
