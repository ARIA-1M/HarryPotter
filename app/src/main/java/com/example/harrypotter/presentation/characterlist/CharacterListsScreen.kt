package com.example.harrypotter.presentation.characterlist

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.harrypotter.domain.model.Character
import com.example.harrypotter.domain.usecase.SortOrder
import com.example.harrypotter.ui.theme.Brown
import com.example.harrypotter.ui.theme.GryffindorDark
import com.example.harrypotter.ui.theme.Ink
import com.example.harrypotter.ui.theme.LightInk
import com.example.harrypotter.ui.theme.Parchment

@Composable
fun CharacterListScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CharacterListContent(
        uiState = uiState,
        onSortChange = viewModel::onSortOrderChanged,
        onHouseSelected = viewModel::onHouseSelected,
        modifier = modifier
    )
}

@Composable
fun CharacterListContent(
    uiState: CharacterListUiState,
    onSortChange: (SortOrder) -> Unit,
    onHouseSelected: (String?) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Parchment)
            .padding(16.dp)
    ) {

        // ===== Заголовок =====
        Text(
            text = "Персонажи Хогвартса",
            style = MaterialTheme.typography.headlineMedium,
            color = GryffindorDark,
            fontWeight = FontWeight.Bold
        )


        Spacer(Modifier.height(16.dp))

        // ===== Фильтр по факультету =====
        Text(
            text = "Фильтр по факультету:",
            style = MaterialTheme.typography.titleSmall,
            color = Ink
        )
        Spacer(Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // Кнопка "Все"
            item {
                FilterChip(
                    selected = uiState.selectedHouse == null,
                    onClick = { onHouseSelected(null) },
                    label = {
                        Text(
                            "Все",
                            color = if (uiState.selectedHouse == null) LightInk else Ink
                        )
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = GryffindorDark,
                        containerColor = LightInk
                    )
                )
            }
            // Факультеты
            items(uiState.houses) { house ->
                FilterChip(
                    selected = uiState.selectedHouse == house,
                    onClick = { onHouseSelected(house) },
                    label = {
                        Text(
                            house,
                            color = if (uiState.selectedHouse == house) LightInk else Ink
                        )
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = GryffindorDark,
                        containerColor = LightInk
                    )
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // ===== Список персонажей =====
        when {
            uiState.isLoading -> Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = GryffindorDark)
            }

            uiState.characters.isEmpty() -> Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Персонажи не найдены",
                    color = Brown,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            else -> LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
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
            .clip(RoundedCornerShape(16.dp))
            .clickable { /* позже откроем детали */ },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightInk
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = GryffindorDark,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${character.house} · ${character.yearOfBirth}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Ink
                )
                Text(
                    text = "${character.species} · ${character.gender}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Brown
                )
            }
        }
    }
}