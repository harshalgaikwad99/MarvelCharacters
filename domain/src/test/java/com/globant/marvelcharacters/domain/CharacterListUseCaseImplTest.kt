package com.globant.marvelcharacters.domain

import com.globant.marvelcharacters.common.result.Result
import com.globant.marvelcharacters.domain.model.Character
import com.globant.marvelcharacters.domain.repository.CharacterRepository
import com.globant.marvelcharacters.domain.usecase.characterlist.CharacterListUseCase
import com.globant.marvelcharacters.domain.usecase.characterlist.CharacterListUseCaseImpl
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
class CharacterListUseCaseImplTest {

    @Mock
    private lateinit var repository: CharacterRepository

    private lateinit var characterListUseCase: CharacterListUseCase

    @Before
    fun setup() {
        characterListUseCase = CharacterListUseCaseImpl(repository)
    }

    @Test
    fun testGetCharacterListWithSuccess() {
        runBlockingTest {
            whenever(repository.getCharacters()).thenReturn(
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
            val result = characterListUseCase.getCharacterList()
            assert(result.isSuccess)
            verify(repository).getCharacters()
        }
    }

    @Test
    fun testGetCharacterListWithError() {
        runBlockingTest {
            whenever(repository.getCharacters()).thenReturn(
                Result.Error(Exception())
            )
            val result = characterListUseCase.getCharacterList()
            assert(!result.isSuccess)
            verify(repository).getCharacters()
        }
    }
}