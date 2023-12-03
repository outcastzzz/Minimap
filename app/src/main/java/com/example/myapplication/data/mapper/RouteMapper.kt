package com.example.myapplication.data.mapper

import com.example.myapplication.data.network.model.RouteDto
import com.example.myapplication.domain.entities.Route
import javax.inject.Inject

class RouteMapper @Inject constructor() {

    fun mapRouteDtoToEntity(dto: RouteDto) = Route(
        floors = dto.floors,
        maps = dto.maps
    )

}