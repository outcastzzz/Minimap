package com.example.myapplication.data.mapper

import com.example.myapplication.data.database.BuildingDbModel
import com.example.myapplication.data.network.model.BuildingDto
import com.example.myapplication.domain.entities.Building
import javax.inject.Inject

class BuildingMapper @Inject constructor() {

    fun mapDtoToDbModel(dto: BuildingDto) = BuildingDbModel(
        id = dto.id,
        rooms = dto.rooms,
        maps = dto.maps,
        address = dto.address
    )

    fun mapDbModelToEntity(dbModel: BuildingDbModel) = Building(
        id = dbModel.id,
        rooms = dbModel.rooms,
        maps = dbModel.maps,
        address = dbModel.address
    )
}