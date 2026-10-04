package com.example.harrypotter.data.di

import com.example.harrypotter.data.repository.AlternateNameRepositoryImpl
import com.example.harrypotter.data.repository.CharacterRepositoryImpl
import com.example.harrypotter.domain.repository.AlternateNameRepository
import com.example.harrypotter.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAlterNameRepository(
        impl: AlternateNameRepositoryImpl
    ): AlternateNameRepository

    @Singleton
    @Binds
    abstract fun bindCharacterRepository(
        impl: CharacterRepositoryImpl
    ): CharacterRepository

}