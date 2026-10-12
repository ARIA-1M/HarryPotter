package com.example.harrypotter.data.local.character

import javax.inject.Inject


class CharacterLocalDataSource  @Inject constructor(
    private val dao: CharacterDao
) {
    suspend fun getImagePath(characterId: String): String? =
        dao.getImagePath(characterId)

    suspend fun saveImagePath(characterId: String, path: String) =
        dao.save(CharacterEntity(characterId, path))
}