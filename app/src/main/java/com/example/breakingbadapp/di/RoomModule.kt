package com.example.breakingbadapp.di

import android.content.Context
import androidx.room.Room
import com.example.breakingbadapp.room.CharacterDB
import com.example.breakingbadapp.room.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideCharacterDB(@ApplicationContext context: Context): CharacterDB {
        return Room
            .databaseBuilder(
                context,
                CharacterDB::class.java,
                CharacterDB.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(characterDataBase: CharacterDB): CharacterDao {
        return characterDataBase.characterDao()
    }
}