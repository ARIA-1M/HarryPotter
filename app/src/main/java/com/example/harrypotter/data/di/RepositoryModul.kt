package com.example.harrypotter.data.di

import com.example.harrypotter.data.repository.AlterNameRepositoryImpl
import com.example.harrypotter.data.repository.CharacterRepositoryImpl
import com.example.harrypotter.domain.repository.AlternateNameRepository
import com.example.harrypotter.domain.repository.CharacterRepository
import com.google.android.datatransport.runtime.dagger.Binds
import com.google.android.datatransport.runtime.dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class) // один репозиторий на всё приложение
abstract class RepositoryModule {
    @Binds
    abstract fun bindAlterNameRepository(
        impl: AlterNameRepositoryImpl
    ): AlternateNameRepository

    @Binds
    abstract fun bindCharacterRepository(
        impl: CharacterRepositoryImpl
    ): CharacterRepository

}