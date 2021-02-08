package com.example.breakingbadapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterEntity: CharacterCacheEntity): Long

    @Query("SELECT * FROM characters")
    suspend fun get(): List<CharacterCacheEntity>
}