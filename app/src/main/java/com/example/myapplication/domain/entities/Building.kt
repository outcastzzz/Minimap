package com.example.myapplication.domain.entities

data class Building(
    val id: Int,
    val rooms: List<String>,
    val maps: List<String>,
    val address: String
)