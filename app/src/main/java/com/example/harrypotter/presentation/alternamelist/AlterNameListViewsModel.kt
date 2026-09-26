package com.example.harrypotter.presentation.alternamelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.domain.repository.AlternateNameRepository
import com.example.harrypotter.domain.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlterNameListViewsModel @Inject constructor(
    private val repository: AlternateNameRepository
) : ViewModel(){
    private val _uiState = MutableStateFlow(AlterNameListUiState())
    val uiState: StateFlow<AlterNameListUiState> = _uiState.asStateFlow()

    init {

    }

    private fun loadAlterNames() = viewModelScope.launch {
//        _uiState.update { it.copy(isLoading = true) }
//        val alterName =
//        _uiState.update { it.copy(isLoading = false, alterName = alterName) }
    }

}