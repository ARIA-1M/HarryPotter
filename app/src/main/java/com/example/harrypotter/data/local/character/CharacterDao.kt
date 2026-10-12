package com.example.harrypotter.data.local.character

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query

@Dao
interface CharacterDao {

    // Сохранить путь (если уже есть — перезаписать)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(image: CharacterEntity)

    // Получить путь по ID персонажа
    @Query("SELECT customImagePath FROM character_images WHERE characterId = :id")
    suspend fun getImagePath(id: String): String?

    @Query("DELETE FROM character_images WHERE characterId = :id")
    suspend fun delete(id: String)
}