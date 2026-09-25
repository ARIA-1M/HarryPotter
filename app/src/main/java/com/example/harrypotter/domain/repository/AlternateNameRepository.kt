package com.example.harrypotter.domain.repository

import com.example.harrypotter.domain.model.AlternateName
import kotlinx.coroutines.flow.Flow

interface AlternateNameRepository{
    // добавление
    suspend fun addAlternateName(name: AlternateName)
    //чтение
    suspend fun getAlternateNames(characterId: String): List<AlternateName>
    //обновление
    suspend fun updateAlternateName(name: AlternateName)
    //удаление
    suspend fun deleteAlternateName(name: AlternateName)
}