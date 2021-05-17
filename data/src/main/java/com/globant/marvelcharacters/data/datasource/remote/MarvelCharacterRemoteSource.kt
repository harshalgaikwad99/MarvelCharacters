package com.globant.marvelcharacters.data.datasource.remote

import com.globant.marvelcharacters.common.result.Result
import com.globant.marvelcharacters.domain.model.Character
import com.globant.marvelcharacters.domain.model.CharacterDetails

interface MarvelCharacterRemoteSource {
    suspend fun getCharacters(): Result<List<Character>>
    suspend fun getCharacterDetails(characterId:String): Result<CharacterDetails>
}