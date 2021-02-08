package com.example.breakingbadapp.retorfit

import retrofit2.http.GET

interface CharacterApi {
    @GET("characters")
    suspend fun getCharacters(): ArrayList<CharacterNetworkEntity>
}