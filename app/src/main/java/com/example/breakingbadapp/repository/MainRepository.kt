package com.example.breakingbadapp.repository

import com.example.breakingbadapp.models.CharacterData
import com.example.breakingbadapp.retorfit.CharacterApi
import com.example.breakingbadapp.retorfit.NetworkMapper
import com.example.breakingbadapp.room.CacheMapper
import com.example.breakingbadapp.room.CharacterDao
import com.example.breakingbadapp.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository
@Inject
constructor(
    private val api: CharacterApi,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper,
    private val characterDao: CharacterDao
) {
    suspend fun getCharacters(): Flow<DataState<ArrayList<CharacterData>>> = flow {
        emit(DataState.Loading)
        try {
            val networkCharacters = api.getCharacters()
            val characters = networkMapper.mapFromEntityList(networkCharacters)
            for (character in characters) {
                characterDao.insert(cacheMapper.mapToEntity(character))
            }
            val cacheCharacters = characterDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cacheCharacters)))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }
}
