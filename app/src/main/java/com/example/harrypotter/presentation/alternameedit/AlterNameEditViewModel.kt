package com.example.harrypotter.presentation.alternameedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.domain.model.AlternateName
import com.example.harrypotter.domain.repository.AlternateNameRepository
import com.example.harrypotter.presentation.alternamelist.AlterNameListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlterNameEditViewModel @Inject constructor(
    private val repository: AlternateNameRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AlterNameEditUIState())
    val uiState: StateFlow<AlterNameEditUIState> = _uiState.asStateFlow()

    fun loadName(nameId: Int, characterId: String) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        val names =repository.getAlternateNames(characterId)
        val name = names.find { it.id == nameId }
        _uiState.update { it.copy(isLoading = false, name = name)}
    }

    fun save(nameId: Int?, title: String, description: String, characterId: String) = viewModelScope.launch {
        val name = AlternateName(
            id = nameId ?: 0,
            title = title,
            description = description,
            characterId = characterId
        )
        if(nameId == null){
            repository.addAlternateName(name)
        } else{
            repository.updateAlternateName(name)
        }
        _uiState.update { it.copy(isSaved = true) }
    }

}

