package com.example.harrypotter.data.di

import androidx.room3.Room
import com.example.harrypotter.data.local.altername.AlterNameDao
import com.example.harrypotter.data.local.Database
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
    fun provideDatabase(@ApplicationContext context: Context): Database{
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "harry_potter.db"

        ).build()
    }

    @Provides
    fun proviedAlterNameDao(database: Database): AlterNameDao = database.alterNameDao()
}