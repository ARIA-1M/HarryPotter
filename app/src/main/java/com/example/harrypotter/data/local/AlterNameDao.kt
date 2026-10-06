package com.example.harrypotter.data.local

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update

@Dao
interface AlterNameDao {

    @Insert
    suspend fun insert(name: AlterNameEntity)

    @Query("SELECT * FROM alter_names WHERE characterId = :characterId")
    suspend fun getByCharacter(characterId: String): List<AlterNameEntity>

    @Query("SELECT * FROM alter_names WHERE id = :id")
    suspend fun getById(id: Int): AlterNameEntity?

    @Query("SELECT * FROM alter_names")
    suspend fun getAll(): List<AlterNameEntity>

    @Update
    suspend fun update(name: AlterNameEntity)

    @Delete
    suspend fun delete(name: AlterNameEntity)


}