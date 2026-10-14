package com.example.harrypotter.presentation.characterlist

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.harrypotter.R
import com.example.harrypotter.domain.model.Character
import com.example.harrypotter.ui.theme.Brown
import com.example.harrypotter.ui.theme.GryffindorDark
import com.example.harrypotter.ui.theme.Ink
import com.example.harrypotter.ui.theme.LightInk
import com.example.harrypotter.ui.theme.Parchment

@Composable
fun CharacterListScreen(
    onCharacterClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CharacterListContent(
        uiState = uiState,
        onHouseSelected = viewModel::onHouseSelected,
        onCharacterClick = onCharacterClick,
        onImageSelected = viewModel::onImageSelected,
        modifier = modifier
    )
}

@Composable
fun CharacterListContent(
    uiState: CharacterListUiState,
    onHouseSelected: (String?) -> Unit,
    onCharacterClick: (String) -> Unit,
    onImageSelected: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Parchment)
            .padding(16.dp)
    ) {
        Text(
            text = "Персонажи Хогвартса",
            style = MaterialTheme.typography.headlineMedium,
            color = GryffindorDark,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Фильтр по факультету:",
            style = MaterialTheme.typography.titleSmall,
            color = Ink
        )
        Spacer(Modifier.height(8.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
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

        when {
            uiState.isLoading -> Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator(color = GryffindorDark,  modifier = Modifier.testTag("loading_indicator") ) }

            uiState.characters.isEmpty() -> Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Персонажи не найдены", color = Brown, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.testTag("empty_message"))
            }

            else -> LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(uiState.characters, key = { it.id }) { character ->
                    CharacterCard(
                        character = character,
                        onClick = { onCharacterClick(character.id) },
                        onImageSelected = { uri -> onImageSelected(character.id, uri.toString()) }
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterCard(
    character: Character,
    onClick: () -> Unit,
    onImageSelected: (Uri) -> Unit
) {
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { onImageSelected(it) }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .testTag("character_card_${character.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = LightInk),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!character.imagePath.isNullOrBlank()) {
                AsyncImage(
                    model = character.imagePath,
                    contentDescription = character.name,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_no_cover),
                    contentDescription = "Нет фото",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = GryffindorDark,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.testTag("character_name_${character.id}")
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

            IconButton(onClick = { pickImageLauncher.launch("image/*") }, modifier = Modifier.testTag("image_button_${character.id}") ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Сменить фото",
                    tint = GryffindorDark
                )
            }

            Button(
                onClick = onClick,
                modifier = Modifier.testTag("details_button_${character.id}"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GryffindorDark,
                    contentColor = LightInk
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Подробнее")
            }
        }
    }
}