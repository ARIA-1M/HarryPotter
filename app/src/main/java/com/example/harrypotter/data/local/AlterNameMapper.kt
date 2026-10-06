package com.example.harrypotter.data.local

import com.example.harrypotter.domain.model.AlternateName

fun AlterNameEntity.toDomain() = AlternateName(
    id = id,
    title = title,
    description = description,
    characterId = characterId
)

fun AlternateName.toEntity() = AlterNameEntity(
    id = id,
    title = title,
    description = description,
    characterId = characterId
)