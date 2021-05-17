package com.globant.marvelcharacters.data.datasource.remote.mapper

import com.globant.marvelcharacters.data.entity.CharacterListEntity
import com.globant.marvelcharacters.domain.model.Character
import com.globant.marvelcharacters.domain.model.CharacterDetails

interface MarvelCharacterMapper {
    fun toCharacterListModel(characterListResponse: CharacterListEntity?): List<Character>
    fun toCharacterDetailModel(characterDetailsResponse: CharacterListEntity?): CharacterDetails
}