package com.example.harrypotter.domain.model


data class Character(
    val id: String,
    val name: String,
    val specie: String,
    val gender: String,
    val house: String,
    val yearOfBirth: Int
)