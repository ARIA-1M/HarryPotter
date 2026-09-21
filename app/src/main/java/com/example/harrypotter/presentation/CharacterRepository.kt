package com.example.harrypotter.presentation

import com.example.harrypotter.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository{
    //список персонажей
    suspend fun getAllCharacter(): Flow<List<Character>>
    // фильтр персонажей по факультету
    suspend fun getCharacterByHouse(house: String): Flow<List<Character>>
}