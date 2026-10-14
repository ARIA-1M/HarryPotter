package com.example.harrypotter.data.local

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.example.harrypotter.data.local.altername.AlterNameDao
import com.example.harrypotter.data.local.altername.AlterNameEntity
import com.example.harrypotter.data.local.character.CharacterDao
import com.example.harrypotter.data.local.character.CharacterEntity

@Database(
    entities = [
        AlterNameEntity::class,
        CharacterEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun alterNameDao(): AlterNameDao
    abstract fun characterDao(): CharacterDao
}