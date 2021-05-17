package com.globant.marvelcharacters.domain.usecase.characterdetails

import com.globant.marvelcharacters.common.result.Result
import com.globant.marvelcharacters.domain.model.CharacterDetails
import com.globant.marvelcharacters.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterDetailUseCaseImpl @Inject constructor(private val characterRepository: CharacterRepository) :
    CharacterDetailUseCase {

    override suspend fun getCharacterDetails(characterId: String): Result<CharacterDetails> =
        characterRepository.getCharacterDetails(characterId)
}