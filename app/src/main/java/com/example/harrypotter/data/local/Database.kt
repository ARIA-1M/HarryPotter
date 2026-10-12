package com.example.harrypotter.data.local

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.example.harrypotter.data.local.altername.AlterNameDao
import com.example.harrypotter.data.local.altername.AlterNameEntity
import com.example.harrypotter.data.local.character.CharacterDao

@Database(entities = [AlterNameEntity::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun alterNameDao(): AlterNameDao
    abstract fun characterDao(): CharacterDao
}