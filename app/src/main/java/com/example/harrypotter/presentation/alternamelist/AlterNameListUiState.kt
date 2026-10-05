package com.example.harrypotter.presentation.alternamelist

import com.example.harrypotter.domain.model.AlternateName

data class AlterNameListUiState (
    val alterName: List<AlternateName> = emptyList(),
    val isLoading: Boolean = false,
    val characterId: String = ""
)