
package com.example.harrypotter.presentation.alternamelist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AlterNameListScreen(
    characterId: String,
    onBack: () -> Unit,
    onAddNameClick: () -> Unit,
    onEditNameClick: (Int) -> Unit,
    viewModel: AlterNameListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(characterId) {
        viewModel.loadAlterNames(characterId)
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Здесь будет список альтернативных имён (${uiState.alterName.size})")
    }
}