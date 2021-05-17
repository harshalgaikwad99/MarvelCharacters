package com.globant.marvelcharacters.di

import com.globant.marvelcharacters.characterdetail.viewModel.CharacterDetailViewModel
import com.globant.marvelcharacters.characterlist.viewmodel.CharacterListViewModel
import com.globant.marvelcharacters.domain.usecase.characterdetails.CharacterDetailUseCase
import com.globant.marvelcharacters.domain.usecase.characterlist.CharacterListUseCase
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun providesCharacterListViewModel(characterListUseCase: CharacterListUseCase): CharacterListViewModel =
        CharacterListViewModel(characterListUseCase)

    @Provides
    fun provideCharacterDetailViewModel(characterDetailUseCase: CharacterDetailUseCase): CharacterDetailViewModel =
        CharacterDetailViewModel(characterDetailUseCase)

}