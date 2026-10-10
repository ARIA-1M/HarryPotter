package com.example.harrypotter.domain.repository

import com.example.harrypotter.domain.model.Character


interface CharacterRepository{
    //список персонажей
    suspend fun getAllCharacters(): List<Character>
}