package com.example.myapplication.domain.entities

data class Room(
    val name: String,
    val maps: List<String>,
    val floor: Int
)