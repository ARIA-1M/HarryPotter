package com.example.harrypotter.presentation.characterlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.input.pointer.HistoricalChange
import androidx.compose.ui.platform.testTag
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.harrypotter.domain.SortOrder
import com.example.harrypotter.domain.model.Character

@Composable
fun CharacterListScreen(viewModel: CharacterListViewModel = hiltViewModel()){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CharacterListContent(
        uiState = uiState,
        onSortChange = viewModel::onSortOrderChanged

    )
}

@Composable
fun CharacterListContent(
    uiState: CharacterListUiState,
    onSortChange: (SortOrder) -> Unit
) {
    Column {
        Row {
            SortOrder.entries.forEach { order ->
                TextButton(onClick = {onSortChange(order)}) {
                    Text(order.name)
                }
            }
        }
//        if(uiState.isLoading){
//            CircularProgressIndicator(modifier = Modifier.testTag("loading"))
//        }
//        else{
//            LazyColumn {
//                items(uiState.characters, key = {it.id}){
//                    character ->
//                    CharacterRow(
//                        character = character,
//
//                    )
//                }
//            }
//        }
    }
}

@Composable
fun CharacterRow(character: Character) {
    Row(modifier = Modifier.testTag("character_row${character.id}")) {
        Text(character.name, modifier = Modifier.weight(1f))
        Text("${character.yearOfBirth} год")
    }
}

