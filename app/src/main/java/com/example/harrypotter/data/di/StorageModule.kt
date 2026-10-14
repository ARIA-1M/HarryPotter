package com.example.harrypotter.data.di

import com.example.harrypotter.data.local.AndroidImageStorage
import com.example.harrypotter.data.local.ImageStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    @Singleton
    abstract fun bindImageStorage(
        impl: AndroidImageStorage
    ): ImageStorage
}