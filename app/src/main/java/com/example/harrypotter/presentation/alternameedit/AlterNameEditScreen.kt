package com.example.harrypotter.presentation.alternameedit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.harrypotter.ui.theme.Brown
import com.example.harrypotter.ui.theme.GryffindorDark
import com.example.harrypotter.ui.theme.Ink
import com.example.harrypotter.ui.theme.LightInk
import com.example.harrypotter.ui.theme.Parchment

@Composable
fun AlterNameEditScreen(
    nameId: Int?,
    characterId: String,
    onBack: () -> Unit,
    onSaved: () -> Unit,
    viewModel: AlterNameEditViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Загружаем имя, если редактирование
    LaunchedEffect(nameId, characterId) {
        if (nameId != null) {
            viewModel.loadName(nameId, characterId)
        }
    }

    // Локальные состояния полей
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    // Как только имя загрузилось — заполняем поля
    LaunchedEffect(uiState.name) {
        uiState.name?.let {
            title = it.title
            description = it.description
        }
    }

    // После сохранения — возвращаемся назад
    LaunchedEffect(uiState.isSaved) {
        if (uiState.isSaved) {
            onSaved()
        }
    }

    val isEditMode = nameId != null

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Parchment)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Назад",
                        tint = GryffindorDark
                    )
                }
                Text(
                    text = if (isEditMode) "Редактировать имя" else "Добавить имя",
                    style = MaterialTheme.typography.headlineSmall,
                    color = GryffindorDark,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(24.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Название") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Ink,
                    unfocusedTextColor = Ink,
                    focusedBorderColor = GryffindorDark,
                    unfocusedBorderColor = Brown,
                    focusedContainerColor = LightInk,
                    unfocusedContainerColor = LightInk
                )
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Описание") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Ink,
                    unfocusedTextColor = Ink,
                    focusedBorderColor = GryffindorDark,
                    unfocusedBorderColor = Brown,
                    focusedContainerColor = LightInk,
                    unfocusedContainerColor = LightInk
                )
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        viewModel.save(
                            nameId = nameId,
                            title = title,
                            description = description,
                            characterId = characterId
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GryffindorDark,
                    contentColor = LightInk
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Сохранить", fontWeight = FontWeight.Bold)
            }
        }
    }
}