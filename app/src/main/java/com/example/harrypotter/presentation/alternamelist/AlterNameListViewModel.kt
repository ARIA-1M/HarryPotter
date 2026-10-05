package com.example.harrypotter.presentation.alternamelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.domain.model.AlternateName
import com.example.harrypotter.domain.repository.AlternateNameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlterNameListViewModel @Inject constructor(
    private val repository: AlternateNameRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AlterNameListUiState())
    val uiState: StateFlow<AlterNameListUiState> = _uiState.asStateFlow()


    fun loadAlterNames(characterId: String) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true, characterId = characterId) }
        val names = repository.getAlternateNames(characterId)
        _uiState.update { it.copy(isLoading = false, alterName = names) }
    }


    fun addAlternateName(title: String, description: String) = viewModelScope.launch {
        val newName = AlternateName(
            id = 0,
            title = title,
            description = description,
            characterId = _uiState.value.characterId
        )
        repository.addAlternateName(newName)
        // Обновляем список
        loadAlterNames(_uiState.value.characterId)
    }


    fun updateAlternateName(name: AlternateName) = viewModelScope.launch {
        repository.updateAlternateName(name)
        loadAlterNames(_uiState.value.characterId)
    }


    fun deleteAlternateName(name: AlternateName) = viewModelScope.launch {
        repository.deleteAlternateName(name)
        loadAlterNames(_uiState.value.characterId)
    }
}