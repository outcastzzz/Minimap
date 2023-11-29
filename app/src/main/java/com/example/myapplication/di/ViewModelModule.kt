package com.example.myapplication.di

import androidx.lifecycle.ViewModel
import com.example.myapplication.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMapViewModel(viewModel: MainViewModel): ViewModel

}