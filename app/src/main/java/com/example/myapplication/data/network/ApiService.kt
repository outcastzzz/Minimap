package com.example.myapplication.data.network

import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Map
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("7ai788")
    suspend fun getAllBuildings(): Response<Building>
    @GET("-UEfMw")
    suspend fun getMapImage(): Response<Map>

}