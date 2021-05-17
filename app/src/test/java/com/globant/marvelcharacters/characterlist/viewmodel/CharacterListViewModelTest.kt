package com.globant.marvelcharacters.characterlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.globant.marvelcharacters.common.result.Result
import com.globant.marvelcharacters.domain.model.Character
import com.globant.marvelcharacters.domain.usecase.characterlist.CharacterListUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class CharacterListViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var characterListUseCase: CharacterListUseCase

    @Mock
    private lateinit var errorResultObserver: Observer<List<Character>>

    @Mock
    private lateinit var successResultObserver: Observer<List<Character>>

    private lateinit var characterListViewModel: CharacterListViewModel

    @Before
    fun setUp() {
        characterListViewModel = CharacterListViewModel(characterListUseCase)
    }

    @Test
    fun testGetCharacterListWithError() {
        runBlockingTest {
            whenever(characterListUseCase.getCharacterList()).thenReturn(
                Result.Error(Exception())
            )

            characterListViewModel.subscribeCharacterState().characterList.observeForever(
                errorResultObserver
            )
            characterListViewModel.getCharacterList()
            verify(characterListUseCase).getCharacterList()
            verify(errorResultObserver, never()).onChanged(any())
            characterListViewModel.subscribeCharacterState().characterList.removeObserver(
                errorResultObserver
            )
        }
    }

    @Test
    fun testGetCharacterListWithSuccess() {
        runBlockingTest {
            whenever(characterListUseCase.getCharacterList()).thenReturn(
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
            characterListViewModel.subscribeCharacterState().characterList.observeForever(
                successResultObserver
            )
            characterListViewModel.getCharacterList()
            verify(characterListUseCase).getCharacterList()
            verify(successResultObserver).onChanged(any())
            characterListViewModel.subscribeCharacterState().characterList.removeObserver(
                successResultObserver
            )
        }
    }

}