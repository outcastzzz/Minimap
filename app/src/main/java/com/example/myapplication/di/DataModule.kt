package com.example.myapplication.di

import com.example.myapplication.data.network.ApiFactory
import com.example.myapplication.data.network.ApiService
import com.example.myapplication.data.repository.MapRepositoryImpl
import com.example.myapplication.domain.repository.MapRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindMapRepository(impl: MapRepositoryImpl): MapRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.buildingApi
        }

    }
}