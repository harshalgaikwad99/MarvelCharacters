package com.globant.marvelcharacters.domain.usecase.characterlist

import com.globant.marvelcharacters.common.result.Result
import com.globant.marvelcharacters.domain.model.Character

interface CharacterListUseCase {
    suspend fun getCharacterList(): Result<List<Character>>
}