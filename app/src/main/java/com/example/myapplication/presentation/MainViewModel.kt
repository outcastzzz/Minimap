package com.example.myapplication.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.network.model.RouteDto
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Route
import com.example.myapplication.domain.useCases.GetBuildingByNameUseCase
import com.example.myapplication.domain.useCases.GetListOfBuildingsUseCase
import com.example.myapplication.domain.useCases.GetListOfRoomsUseCase
import com.example.myapplication.domain.useCases.GetRouteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getListOfRoomsUseCase: GetListOfRoomsUseCase,
    private val getRouteUseCase: GetRouteUseCase,
    private val getListOfBuildingUseCase: GetListOfBuildingsUseCase,
    private val getBuildingByNameUseCase: GetBuildingByNameUseCase
): ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _buildings = MutableLiveData<List<String>>()
    val buildings: LiveData<List<String>> = _buildings

    private val _rooms = MutableLiveData<List<String>>()
    val rooms: LiveData<List<String>> = _rooms

    private val _route = MutableLiveData<Route>()
    val route: LiveData<Route> = _route

    private val _building = MutableLiveData<Building>()
    val building: LiveData<Building> = _building

    fun getBuildingByName(buildingName: String) {
        Log.d("BuildNameTag", "done")
        viewModelScope.launch {
            try {
                val building = getBuildingByNameUseCase.getBuildingByName(buildingName)
                _building.value = building
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun getListOfBuildings() {
        viewModelScope.launch {
            try {
                val buildings = getListOfBuildingUseCase.getListOfBuildings()
                _buildings.value = buildings
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }
    fun getListOfRooms(buildingName: String) {
        viewModelScope.launch {
            try {
                val rooms = getListOfRoomsUseCase.getListOfRooms(buildingName)
                _rooms.value = rooms
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun getRoute(
        buildingName: String,
        roomNameStart: String,
        roomNameEnd: String
    ) {
        viewModelScope.launch {
            try {
                val route = getRouteUseCase.getRoute(buildingName, roomNameStart, roomNameEnd)
                _route.value = route
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }

    }

}