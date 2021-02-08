package com.example.breakingbadapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CharacterCacheEntity::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseTypeConverters::class)
abstract class CharacterDB : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        const val DATABASE_NAME: String = "Character_db"
    }
}
