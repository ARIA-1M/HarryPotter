package com.example.harrypotter.domain

import com.example.harrypotter.domain.repository.CharacterRepository
import com.example.harrypotter.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSortedCharacterUseCase(
    private val repository: CharacterRepository
) {
    suspend fun invoke(sortBy: SortOrder): Flow<List<Character>> {
        return repository.getAllCharacters().map { characters ->
            when (sortBy) {
                SortOrder.HOUSE -> characters.sortedBy { it.house }
            }
        }
    }
}

enum class SortOrder { HOUSE }