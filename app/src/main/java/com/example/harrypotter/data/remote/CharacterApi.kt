package com.example.harrypotter.data.remote

import com.example.harrypotter.data.remote.dto.CharacterDto
import retrofit2.http.GET

interface CharacterApi {
    @GET("api/characters")
    suspend fun getCharacters(): List<CharacterDto>
}