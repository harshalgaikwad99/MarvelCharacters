package com.globant.marvelcharacters.domain.model

data class CharacterDetails(
    val id: Int,
    val name: String,
    val thumbnailUrl: String,
    val description: String
)