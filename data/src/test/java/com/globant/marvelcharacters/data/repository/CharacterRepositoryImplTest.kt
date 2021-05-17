package com.globant.marvelcharacters.data.repository

import com.globant.marvelcharacters.common.result.Result
import com.globant.marvelcharacters.data.datasource.remote.MarvelCharacterRemoteSource
import com.globant.marvelcharacters.domain.model.Character
import com.globant.marvelcharacters.domain.model.CharacterDetails
import com.globant.marvelcharacters.domain.repository.CharacterRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class CharacterRepositoryImplTest {

    @Mock
    private lateinit var marvelCharacterRemoteSource: MarvelCharacterRemoteSource

    private lateinit var characterRepository: CharacterRepository

    @Before
    fun setup() {
        characterRepository = CharacterRepositoryImpl(marvelCharacterRemoteSource)
    }

    @Test
    fun testGetCharactersWithSuccess() {
        runBlockingTest {
            whenever(marvelCharacterRemoteSource.getCharacters()).thenReturn(
                Result.Success(
                    listOf(
                        Character(
                            id = 1,
                            name = "name",
                            thumbnailUrl = "test.jpg"
                        )
                    )
                )
            )

            val result = characterRepository.getCharacters()
            assert(result.isSuccess)
            verify(marvelCharacterRemoteSource).getCharacters()
        }
    }

    @Test
    fun testGetCharactersWithError() {
        runBlockingTest {
            whenever(marvelCharacterRemoteSource.getCharacters()).thenReturn(
                Result.Error(Exception())
            )

            val result = characterRepository.getCharacters()
            assert(!result.isSuccess)
            verify(marvelCharacterRemoteSource).getCharacters()
        }
    }

    @Test
    fun testGetCharacterDetailsWithSuccess() {
        runBlockingTest {
            whenever(marvelCharacterRemoteSource.getCharacterDetails(any())).thenReturn(
                Result.Success(
                    CharacterDetails(
                        id = 100,
                        name = "name",
                        thumbnailUrl = "test.jpg",
                        description = "description"
                    )
                )
            )

            val result = characterRepository.getCharacterDetails("100")
            assert(result.isSuccess)
            verify(marvelCharacterRemoteSource).getCharacterDetails(any())
        }
    }

    @Test
    fun testGetCharacterDetailsWithError() {
        runBlockingTest {
            whenever(marvelCharacterRemoteSource.getCharacterDetails(any())).thenReturn(
                Result.Error(Exception())
            )

            val result = characterRepository.getCharacterDetails("100")
            assert(!result.isSuccess)
            verify(marvelCharacterRemoteSource).getCharacterDetails(any())
        }
    }
}

