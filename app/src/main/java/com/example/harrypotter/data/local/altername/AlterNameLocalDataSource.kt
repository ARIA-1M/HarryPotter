package com.example.harrypotter.data.local.altername

import com.example.harrypotter.domain.model.AlternateName
import javax.inject.Inject

// DataSource для альтернативных имён — работает с локальной БД (Room).
class AlterNameLocalDataSource @Inject constructor(
    private val dao: AlterNameDao
) {
    suspend fun getAlterNames(characterId: String): List<AlternateName> {
        return dao.getByCharacter(characterId).map { it.toDomain() }
    }

    suspend fun getAlterNameById(id: Int): AlternateName? {
        return dao.getById(id)?.toDomain()
    }

    suspend fun hasAlterNames(characterId: String): Boolean {
        return dao.getByCharacter(characterId).isNotEmpty()
    }

    suspend fun addAlterName(name: AlternateName) {
        dao.insert(name.toEntity())
    }

    suspend fun updateAlterName(name: AlternateName) {
        dao.update(name.toEntity())
    }

    suspend fun deleteAlterName(name: AlternateName) {
        dao.delete(name.toEntity())
    }
}