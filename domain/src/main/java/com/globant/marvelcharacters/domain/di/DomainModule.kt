package com.globant.marvelcharacters.domain.di

import com.globant.marvelcharacters.domain.repository.CharacterRepository
import com.globant.marvelcharacters.domain.usecase.characterdetails.CharacterDetailUseCase
import com.globant.marvelcharacters.domain.usecase.characterdetails.CharacterDetailUseCaseImpl
import com.globant.marvelcharacters.domain.usecase.characterlist.CharacterListUseCase
import com.globant.marvelcharacters.domain.usecase.characterlist.CharacterListUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun providesCharacterListUseCase(characterRepository: CharacterRepository): CharacterListUseCase =
        CharacterListUseCaseImpl(characterRepository)

    @Provides
    fun providesCharacterDetailUseCase(characterRepository: CharacterRepository): CharacterDetailUseCase =
        CharacterDetailUseCaseImpl(characterRepository)

}