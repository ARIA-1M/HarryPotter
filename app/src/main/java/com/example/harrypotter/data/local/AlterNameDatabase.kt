package com.example.harrypotter.data.local

import androidx.room3.Database
import androidx.room3.RoomDatabase

@Database(entities = [AlterNameEntity::class], version = 1)
abstract class AlterNameDatabase: RoomDatabase() {
    abstract fun alterNameDao(): AlterNameDao
}