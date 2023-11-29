package com.example.myapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.useCases.GetBuildingByNameUseCase
import com.example.myapplication.domain.useCases.GetListOfBuildingsUseCase
import com.example.myapplication.domain.useCases.GetListOfRoomsUseCase
import com.example.myapplication.domain.useCases.GetRoomByNameUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getListOfRoomsUseCase: GetListOfRoomsUseCase,
    private val getRoomByNameUseCase: GetRoomByNameUseCase,
    private val getListOfBuildingUseCase: GetListOfBuildingsUseCase,
    private val getBuildingByNameUseCase: GetBuildingByNameUseCase
): ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _buildings = MutableLiveData<List<String>>()
    val buildings: LiveData<List<String>> = _buildings

    suspend fun getBuildingByName(
        buildingName: String
    ) = getBuildingByNameUseCase.getBuildingByName(buildingName)

    suspend fun getListOfBuildings() {
        viewModelScope.launch {
            try {
                val buildings = getListOfBuildingUseCase.getListOfBuildings()
                _buildings.value = buildings
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }
    suspend fun getListOfRooms(
        buildingName: String
    ) = getListOfRoomsUseCase.getListOfRooms(buildingName)

    suspend fun getRoomByName(roomName: String) = getRoomByNameUseCase.getRoomByName(roomName)

}