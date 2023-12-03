package com.example.myapplication.data.mapper

import com.example.myapplication.data.database.BuildingDbModel
import com.example.myapplication.data.network.model.BuildingDto
import com.example.myapplication.domain.entities.Building
import javax.inject.Inject

class BuildingMapper @Inject constructor() {

    fun mapDtoToDbModel(dto: BuildingDto) = BuildingDbModel(
        address = dto.address,
        floors = dto.floors
    )

    fun mapDbModelToEntity(dbModel: BuildingDbModel) = Building(
        address = dbModel.address,
        floors = dbModel.floors
    )
}