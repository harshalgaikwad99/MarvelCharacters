package com.globant.marvelcharacters.data.di

import com.globant.marvelcharacters.data.datasource.remote.config.CommonApiParamProvider
import com.globant.marvelcharacters.data.datasource.remote.MarvelCharacterRemoteSource
import com.globant.marvelcharacters.data.datasource.remote.MarvelCharacterRemoteSourceImpl
import com.globant.marvelcharacters.data.datasource.remote.apiservice.MarvelCharacterApi
import com.globant.marvelcharacters.data.datasource.remote.mapper.MarvelCharacterMapper
import com.globant.marvelcharacters.data.datasource.remote.mapper.MarvelCharacterMapperImpl
import com.globant.marvelcharacters.data.repository.CharacterRepositoryImpl
import com.globant.marvelcharacters.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DataModule {

    @Provides
    fun providesCharacterRepository(remoteDataSource: MarvelCharacterRemoteSource): CharacterRepository =
        CharacterRepositoryImpl(remoteDataSource)

    @Provides
    fun providesMarvelCharacterRemoteSource(
        marvelApi: MarvelCharacterApi,
        mapper: MarvelCharacterMapper
    ): MarvelCharacterRemoteSource =
        MarvelCharacterRemoteSourceImpl(marvelApi, mapper)

    @Provides
    fun providesMarvelCharacterApi(retrofit: Retrofit): MarvelCharacterApi =
        retrofit.create(MarvelCharacterApi::class.java)

    @Provides
    fun providesMarvelCharacterMapper(): MarvelCharacterMapper = MarvelCharacterMapperImpl()

    @Provides
    fun providesCommonApiParamProvider(): CommonApiParamProvider = CommonApiParamProvider()

}