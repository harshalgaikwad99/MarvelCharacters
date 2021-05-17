package com.globant.marvelcharacters.data.datasource.remote.apiservice

import com.globant.marvelcharacters.data.entity.CharacterListEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelCharacterApi {

    @GET("/v1/public/characters")
    suspend fun getCharacters(): Response<CharacterListEntity>

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterDetails(@Path(CHARACTER_ID) characterId: String): Response<CharacterListEntity>

    companion object {
        private const val CHARACTER_ID = "characterId"
    }
}