package com.example.harrypotter.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val id: String,
    val name: String,
    val species: String,
    val gender: String,
    val house: String,
    val yearOfBirth: Int? = null,
    @SerialName("image")
    val imagePath: String = ""
)