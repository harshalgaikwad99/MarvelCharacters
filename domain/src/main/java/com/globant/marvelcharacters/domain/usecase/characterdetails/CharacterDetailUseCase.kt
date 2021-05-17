package com.globant.marvelcharacters.domain.usecase.characterdetails

import com.globant.marvelcharacters.common.result.Result
import com.globant.marvelcharacters.domain.model.CharacterDetails

interface CharacterDetailUseCase {
    suspend fun getCharacterDetails(characterId: String): Result<CharacterDetails>
}