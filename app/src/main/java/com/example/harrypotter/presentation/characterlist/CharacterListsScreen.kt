package com.example.harrypotter.presentation.characterlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.harrypotter.domain.model.Character

@Composable
fun CharacterListScreen(viewModel: CharacterListViewModel = hiltViewModel()){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CharacterListContent(

    )
}

@Composable
fun CharacterListContent() {
    TODO("Not yet implemented")
}