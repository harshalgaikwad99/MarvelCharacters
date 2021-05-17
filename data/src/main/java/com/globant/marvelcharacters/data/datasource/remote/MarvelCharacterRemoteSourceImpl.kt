package com.globant.marvelcharacters.data.datasource.remote

import com.globant.marvelcharacters.common.result.Result
import com.globant.marvelcharacters.data.datasource.remote.apiservice.MarvelCharacterApi
import com.globant.marvelcharacters.data.datasource.remote.mapper.MarvelCharacterMapper
import com.globant.marvelcharacters.domain.model.Character
import com.globant.marvelcharacters.domain.model.CharacterDetails
import java.lang.Exception
import javax.inject.Inject

class MarvelCharacterRemoteSourceImpl @Inject constructor(
    private val marvelApi: MarvelCharacterApi,
    private val mapper: MarvelCharacterMapper
) : MarvelCharacterRemoteSource {

    override suspend fun getCharacters(): Result<List<Character>> = try {
        val apiResponse = marvelApi.getCharacters()
        if (apiResponse.isSuccessful) {
            Result.Success(mapper.toCharacterListModel(apiResponse.body()))
        } else {
            Result.Error(Exception(apiResponse.errorBody().toString()))
        }
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun getCharacterDetails(characterId: String): Result<CharacterDetails> =
        try {
            val apiResponse = marvelApi.getCharacterDetails(
                characterId = characterId
            )
            if (apiResponse.isSuccessful) {
                Result.Success(mapper.toCharacterDetailModel(apiResponse.body()))
            } else {
                Result.Error(Exception(apiResponse.errorBody().toString()))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
}