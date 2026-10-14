package com.example.harrypotter.data.di

import android.content.Context
import androidx.room3.Room
import com.example.harrypotter.data.local.Database
import com.example.harrypotter.data.local.altername.AlterNameDao
import com.example.harrypotter.data.local.character.CharacterDao   // 👈 ДОБАВЬ
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "harry_potter.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAlterNameDao(database: Database): AlterNameDao = database.alterNameDao()

    @Provides
    fun provideCharacterDao(database: Database): CharacterDao = database.characterDao()   // 👈 ДОБАВИЛ
}