package com.example.harrypotter.domain.model

import java.time.LocalDate

data class Character(
    val id: String,
    val name: String,
    val specie: String,
    val gender: String,
    val house: String,
    val dateOfBirth: LocalDate
)