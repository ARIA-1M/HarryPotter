package com.example.harrypotter.data.di

import androidx.room3.Room
import com.example.harrypotter.data.local.AlterNameDao
import com.example.harrypotter.data.local.AlterNameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule{
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AlterNameDatabase{
        return Room.databaseBuilder(
            context,
            AlterNameDatabase::class.java,
            "altername.db"

        ).build()
    }

    @Provides
    fun proviedAlterNameDao(database: AlterNameDatabase): AlterNameDao = database.alterNameDao()
}