package com.example.harrypotter.presentation.alternameedit

import com.example.harrypotter.domain.model.AlternateName

data class AlterNameEditUIState(
    val name: AlternateName? = null,
    val isLoading: Boolean = false,
    val isSaved: Boolean = false
)