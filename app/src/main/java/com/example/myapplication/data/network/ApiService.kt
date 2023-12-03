package com.example.myapplication.data.network

import com.example.myapplication.data.network.model.BuildingDto
import com.example.myapplication.data.network.model.BuildingsName
import com.example.myapplication.data.network.model.RouteDto
import com.example.myapplication.data.network.model.RoomsName
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {

    @GET("getBuildings")
    suspend fun getListOfBuilding(): BuildingsName

    @GET("connectWithMobile/{buildingName}")
    suspend fun getBuildingByName(
        @Header("ngrok-skip-browser-warning") ngrokSkipBrowserWarning: String = "skit",
        @Path("buildingName") buildingName: String
    ): BuildingDto

    @GET("getListOfRooms/{buildingName}")
    suspend fun getListOfRooms(@Path("buildingName") buildingName: String): RoomsName

    @GET("generateImages/{buildingName}/{roomNameStart}/{roomNameEnd}")
    suspend fun getRouteByName(
        @Path("buildingName") buildingName: String,
        @Path("roomNameStart") roomNameStart: String,
        @Path("roomNameEnd") roomNameEnd: String
    ): RouteDto
}
