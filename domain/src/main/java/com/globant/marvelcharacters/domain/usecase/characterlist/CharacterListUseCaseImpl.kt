package com.globant.marvelcharacters.domain.usecase.characterlist

import com.globant.marvelcharacters.common.result.Result
import com.globant.marvelcharacters.domain.model.Character
import com.globant.marvelcharacters.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterListUseCaseImpl @Inject constructor(private val characterRepository: CharacterRepository) :
    CharacterListUseCase {
    override suspend fun getCharacterList(): Result<List<Character>> =
        characterRepository.getCharacters()
}