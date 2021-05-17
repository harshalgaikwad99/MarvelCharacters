package com.globant.marvelcharacters.data.repository

import com.globant.marvelcharacters.common.result.Result
import com.globant.marvelcharacters.data.datasource.remote.MarvelCharacterRemoteSource
import com.globant.marvelcharacters.domain.model.Character
import com.globant.marvelcharacters.domain.model.CharacterDetails
import com.globant.marvelcharacters.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val remoteDataSource: MarvelCharacterRemoteSource) :
    CharacterRepository {

    override suspend fun getCharacters(): Result<List<Character>> = remoteDataSource.getCharacters()

    override suspend fun getCharacterDetails(characterId: String): Result<CharacterDetails> =
        remoteDataSource.getCharacterDetails(characterId)
}