package com.example.harrypotter.domain.usecase

import com.example.harrypotter.domain.model.Character
import com.example.harrypotter.domain.repository.CharacterRepository
import javax.inject.Inject

class GetSortedCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(house: String? = null): List<Character> {
        val characters = repository.getAllCharacters()
        return if (house == null) {
            characters
        } else {
            characters.filter { it.house == house }
        }
    }
}

enum class SortOrder { HOUSE }