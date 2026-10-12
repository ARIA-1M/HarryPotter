package com.example.harrypotter.data.local.character

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey
    val characterId: String,
    val customImagePath: String
)