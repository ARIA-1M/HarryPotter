package com.example.harrypotter.data.repository

import com.example.harrypotter.data.local.ImageStorage
import com.example.harrypotter.data.local.character.CharacterLocalDataSource
import com.example.harrypotter.data.remote.dto.CharacterDto
import com.example.harrypotter.data.remote.CharacterRemoteDataSource
import com.example.harrypotter.domain.model.Character
import com.example.harrypotter.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterLocalDataSource,
    private val imageStorage: ImageStorage
) : CharacterRepository {

    override suspend fun getAllCharacters(): List<Character> {
        val remote = remoteDataSource.fetchCharacters()
        return remote.map { dto ->
            // Если в БД есть своя картинка берём её, иначе URL из API
            val customPath = localDataSource.getImagePath(dto.id)
            dto.toDomain(imagePath = customPath ?: dto.imageUrl)
        }
    }


    override suspend fun updateImage(characterId: String, sourceUri: String): String {
        val savePath = imageStorage.saveImage(sourceUri, characterId)
        localDataSource.saveImagePath(characterId, savePath)
        return savePath
    }
}

fun CharacterDto.toDomain(imagePath: String) = Character(
    id = id,
    name = name,
    species = species,
    gender = gender,
    house = house,
    yearOfBirth = yearOfBirth ?: 0,
    customImagePath = imageUrl ?:null
)