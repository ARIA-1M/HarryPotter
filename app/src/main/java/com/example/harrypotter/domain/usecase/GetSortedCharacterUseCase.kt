package com.example.harrypotter.domain.usecase

import com.example.harrypotter.domain.repository.CharacterRepository
import com.example.harrypotter.domain.model.Character
import javax.inject.Inject


class GetSortedCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(sortBy: SortOrder): List<Character>{
        val character = repository.getAllCharacters()
        return when (sortBy) {
            SortOrder.HOUSE -> character.sortedBy { it.name }
        }
    }
}

enum class SortOrder { HOUSE }