package com.globant.marvelcharacters.data.datasource.remote.mapper

import com.globant.marvelcharacters.data.entity.CharacterListEntity
import com.globant.marvelcharacters.domain.model.Character
import com.globant.marvelcharacters.domain.model.CharacterDetails

class MarvelCharacterMapperImpl : MarvelCharacterMapper {

    override fun toCharacterListModel(characterListResponse: CharacterListEntity?): List<Character> =
        characterListResponse?.data?.results?.map {
            Character(
                id = it.id ?: 0,
                name = it.name.orEmpty(),
                thumbnailUrl = "${it.thumbnail?.path}.${it.thumbnail?.extension}"
            )
        } ?: emptyList()

    override fun toCharacterDetailModel(characterDetailsResponse: CharacterListEntity?): CharacterDetails {
        val result = characterDetailsResponse?.data?.results?.first()
        return CharacterDetails(
            id = result?.id ?: 0,
            name = result?.name.orEmpty(),
            thumbnailUrl = "${result?.thumbnail?.path.orEmpty()}.${result?.thumbnail?.extension.orEmpty()}",
            description = result?.description.orEmpty()
        )
    }
}