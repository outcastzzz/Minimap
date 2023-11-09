package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.network.ApiFactory
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Room
import com.example.myapplication.domain.repository.MapRepository
import com.example.myapplication.presentation.adapters.BuildingItemAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

class MapRepositoryImpl @Inject constructor(

): MapRepository {

    override fun getListOfRooms(buildingId: Int): LiveData<List<Room>> {
        TODO()
    }

    override fun getRoomById(roomId: String): Room {
        TODO()
    }

    override fun loadData() {
        TODO()
    }

    override fun getListOfBuildings(): List<Building> {
        TODO()
    }

    override fun getMapImage(buildingId: Int): String {
        TODO()
    }
}