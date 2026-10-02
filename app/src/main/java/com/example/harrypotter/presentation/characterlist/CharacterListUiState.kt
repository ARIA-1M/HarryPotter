package com.example.harrypotter.presentation.characterlist

import com.example.harrypotter.domain.model.Character
import com.example.harrypotter.domain.usecase.SortOrder

data class CharacterListUiState (
    val characters: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val sortOrder: SortOrder = SortOrder.HOUSE
)

