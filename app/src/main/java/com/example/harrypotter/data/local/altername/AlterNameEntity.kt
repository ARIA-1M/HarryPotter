package com.example.harrypotter.data.local.altername

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "alter_names")
data class AlterNameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val characterId: String
)