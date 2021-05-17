package com.globant.marvelcharacters.domain

import com.globant.marvelcharacters.common.result.Result
import com.globant.marvelcharacters.domain.model.CharacterDetails
import com.globant.marvelcharacters.domain.repository.CharacterRepository
import com.globant.marvelcharacters.domain.usecase.characterdetails.CharacterDetailUseCase
import com.globant.marvelcharacters.domain.usecase.characterdetails.CharacterDetailUseCaseImpl
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
class CharacterDetailUseCaseImplTest {

    @Mock
    private lateinit var repository: CharacterRepository

    private lateinit var characterDetailUseCase: CharacterDetailUseCase

    @Before
    fun setup() {
        characterDetailUseCase = CharacterDetailUseCaseImpl(repository)
    }

    @Test
    fun testGetCharacterDetailsWithSuccess() {
        runBlockingTest {
            whenever(repository.getCharacterDetails(any())).thenReturn(
                Result.Success(
                    CharacterDetails(
                        id = 100,
                        name = "name",
                        thumbnailUrl = "test.jpg",
                        description = "description"
                    )
                )
            )

            val result = characterDetailUseCase.getCharacterDetails("100")
            assert(result.isSuccess)
            verify(repository).getCharacterDetails(any())
        }
    }

    @Test
    fun testGetCharacterDetailsWithError() {
        runBlockingTest {
            whenever(repository.getCharacterDetails(any())).thenReturn(
                Result.Error(Exception())
            )

            val result = characterDetailUseCase.getCharacterDetails("100")
            assert(!result.isSuccess)
            verify(repository).getCharacterDetails(any())
        }
    }
}