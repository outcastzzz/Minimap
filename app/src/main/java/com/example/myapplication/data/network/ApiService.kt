package com.example.myapplication.data.network

import androidx.lifecycle.LiveData
import com.example.myapplication.domain.entities.Building
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("")
    suspend fun getListOfBuilding(): LiveData<List<String>>

    @GET("{buildingName}")
    suspend fun getBuildingByName(@Path("buildingName") buildingName: String): LiveData<Building>

}