package com.example.harrypotter.presentation.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.domain.GetSortedCharacterUseCase
import com.example.harrypotter.domain.SortOrder
import com.example.harrypotter.domain.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getSortedCharacter: GetSortedCharacterUseCase,
    private val repository: CharacterRepository
) : ViewModel(){
    private val _uiState = MutableStateFlow(CharacterListUiState())
    val uiState: StateFlow<CharacterListUiState> = _uiState.asStateFlow()

    init {
        loadCharacters()
    }

    fun onSortOrderChanged(order: SortOrder){
        _uiState.update { it.copy(sortOrder = order) }
        loadCharacters()

    }

    private fun loadCharacters() = viewModelScope.launch {
//        _uiState.update { it.copy(isLoading =true) }
//        val characters = getSortedCharacter(_uiState.value.sortOrder)
//        _uiState.update { it.copy(isLoading = false, characters =characters) }
    }
}