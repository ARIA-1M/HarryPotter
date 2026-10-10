package com.example.harrypotter.data.remote.dto

import com.example.harrypotter.data.remote.CharacterDto
import retrofit2.http.GET

interface CharacterApi {
    @GET("api/characters")
    suspend fun getCharacters(): List<CharacterDto>
}