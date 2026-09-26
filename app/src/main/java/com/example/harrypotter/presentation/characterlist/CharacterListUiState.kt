package com.example.harrypotter.presentation.characterlist

import com.example.harrypotter.domain.SortOrder

data class CharacterListUiState (
    val characters: List<Charsets> = emptyList(),
    val sortOrder: SortOrder = SortOrder.HOUSE
)

