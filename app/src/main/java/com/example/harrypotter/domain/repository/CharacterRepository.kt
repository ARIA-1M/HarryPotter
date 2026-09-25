package com.example.harrypotter.domain.repository

import com.example.harrypotter.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository{
    //список персонажей
    suspend fun getAllCharacters(): List<Character>
    // фильтр персонажей по факультету
    suspend fun getCharacterByHouse(house: String): List<Character>
}