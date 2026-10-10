package com.example.harrypotter.data.repository

import com.example.harrypotter.data.remote.CharacterDto
import com.example.harrypotter.data.remote.CharacterRemoteDataSource
import com.example.harrypotter.domain.model.Character
import com.example.harrypotter.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource
) : CharacterRepository {

    override suspend fun getAllCharacters(): List<Character> {
        return remoteDataSource.fetchCharacters().map { it.toDomain() }
    }
}

fun CharacterDto.toDomain() = Character(
    id = id,
    name = name,
    species = species,
    gender = gender,
    house = house,
    yearOfBirth = yearOfBirth ?: 0
)