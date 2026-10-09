package com.example.harrypotter.data.local

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import androidx.room3.Update

@Dao
interface AlterNameDao {

    @Insert
    suspend fun insert(name: AlterNameEntity)

    @Query("SELECT * FROM alter_names WHERE characterId = :characterId")
    suspend fun getByCharacter(characterId: String): List<AlterNameEntity>

    @Query("SELECT COUNT(*) FROM alter_names")
    suspend fun count(): Int

    @Query("SELECT * FROM alter_names WHERE id = :id")
    suspend fun getById(id: Int): AlterNameEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<AlterNameEntity>)

    @Query("SELECT * FROM alter_names")
    suspend fun getAll(): List<AlterNameEntity>

    @Update
    suspend fun update(name: AlterNameEntity)

    @Delete
    suspend fun delete(name: AlterNameEntity)


}