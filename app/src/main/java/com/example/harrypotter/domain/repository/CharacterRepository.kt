package com.example.harrypotter.domain.repository

import com.example.harrypotter.domain.model.Character


interface CharacterRepository{
    //список персонажей
    suspend fun getAllCharacters(): List<Character>
    // фильтр персонажей по факультету
    suspend fun getCharactersByHouse(house: String): List<Character>
}