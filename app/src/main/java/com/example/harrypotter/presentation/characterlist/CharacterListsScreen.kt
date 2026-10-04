package com.example.harrypotter.presentation.characterlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.harrypotter.domain.model.Character
import com.example.harrypotter.domain.usecase.SortOrder

@Composable
fun CharacterListScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CharacterListContent(
        uiState = uiState,
        onSortChange = viewModel::onSortOrderChanged,
        modifier = modifier
    )
}

@Composable
fun CharacterListContent(
    uiState: CharacterListUiState,
    onSortChange: (SortOrder) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {

        // ===== Сортировка =====
        Text("Сортировать по:", style = MaterialTheme.typography.titleSmall)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            SortOrder.entries.forEach { order ->
                FilterChip(
                    selected = uiState.sortOrder == order,
                    onClick = { onSortChange(order) },
                    label = { Text(order.name) }
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // ===== Список персонажей =====
        when {
            uiState.isLoading -> Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }

            uiState.characters.isEmpty() -> Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { Text("Персонажи не найдены") }

            else -> LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.characters, key = { it.id }) { character ->
                    CharacterCard(character = character)
                }
            }
        }
    }
}

@Composable
fun CharacterCard(character: Character) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${character.house} · ${character.yearOfBirth}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "${character.species} · ${character.gender}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}