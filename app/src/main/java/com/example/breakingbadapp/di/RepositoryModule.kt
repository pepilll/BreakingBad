package com.example.breakingbadapp.di

import com.example.breakingbadapp.repository.MainRepository
import com.example.breakingbadapp.retorfit.CharacterApi
import com.example.breakingbadapp.retorfit.NetworkMapper
import com.example.breakingbadapp.room.CacheMapper
import com.example.breakingbadapp.room.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepositoryModule(
        api: CharacterApi,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper,
        characterDao: CharacterDao
    ): MainRepository {
        return MainRepository(
            api,
            cacheMapper,
            networkMapper,
            characterDao
        )
    }
}