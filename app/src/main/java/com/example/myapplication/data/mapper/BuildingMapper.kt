package com.example.myapplication.data.mapper

import com.example.myapplication.data.database.BuildingDbModel
import com.example.myapplication.data.network.model.BuildingDto
import com.example.myapplication.domain.entities.Building
import javax.inject.Inject

class BuildingMapper @Inject constructor() {

    fun mapDtoToDbModel(dto: BuildingDto) = BuildingDbModel(
        rooms = dto.rooms,
        mainMap = dto.mainMap,
        address = dto.address
    )

    fun mapDbModelToEntity(dbModel: BuildingDbModel) = Building(
        rooms = dbModel.rooms,
        mainMap = dbModel.mainMap,
        address = dbModel.address
    )
}