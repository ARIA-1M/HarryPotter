package com.example.harrypotter.data.repository

import com.example.harrypotter.domain.model.Character
import com.example.harrypotter.domain.repository.CharacterRepository
import jakarta.inject.Inject


class CharacterRepositoryImpl @Inject constructor(): CharacterRepository {
    private val characters = mutableListOf(
        Character(
            id = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
            name = "Harry Potter",
            species = "human",
            gender = "male",
            house = "Gryffindor",
            yearOfBirth = 1980
        ),
        Character(
            id = "4c7e6819-a91a-45b2-a454-f931e4a7cce3",
            name = "Hermione Granger",
            species = "human",
            gender = "female",
            house = "Gryffindor",
            yearOfBirth = 1979
        ),
        Character(
            id = "c3b1f9a5-b87b-48bf-b00d-95b093ea6390",
            name = "Ron Weasley",
            species = "human",
            gender = "male",
            house = "Gryffindor",
            yearOfBirth = 1980
        ),
    )


    override suspend fun getAllCharacters(): List<Character>{
        return characters.toList()
    }

    override suspend fun getCharactersByHouse(house: String): List<Character> {
        return characters.filter { it.house == house }
    }

}