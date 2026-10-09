package com.example.harrypotter.data.remote

import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val api: CharacterApi
) {
    suspend fun fetchCharacters(): List<CharacterDto> = api.getCharacters().result
}