package com.example.harrypotter.domain

import com.example.harrypotter.domain.repository.CharacterRepository
import com.example.harrypotter.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSortedCharacterUseCase(
    private val repository: CharacterRepository
) {
    suspend fun invoke(sortBy: SortOrder): List<Character>{
        val character = repository.getAllCharacters()
        return when (sortBy) {
            SortOrder.HOUSE -> character.sortedBy { it.name }
        }
    }
}

enum class SortOrder { HOUSE }