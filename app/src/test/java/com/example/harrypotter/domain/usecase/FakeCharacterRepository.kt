package com.example.harrypotter.domain.usecase

import com.example.harrypotter.domain.model.Character
import com.example.harrypotter.domain.repository.CharacterRepository
import java.io.IOException

class FakeCharacterRepository : CharacterRepository {

    private var characters = listOf<Character>()

    var shouldThrow = false

    var lastImageUpdate: Pair<String, String>? = null

    fun setCharacters(list: List<Character>) { characters = list }

    override suspend fun getAllCharacters(): List<Character> {
        if (shouldThrow) throw IOException("fake failure")
        return characters
    }

    override suspend fun updateImage(characterId: String, sourceUri: String): String {
        lastImageUpdate = characterId to sourceUri
        return "file://covers/$characterId.jpg"
    }
}