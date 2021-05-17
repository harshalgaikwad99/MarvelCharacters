package com.globant.marvelcharacters.characterdetails.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.globant.marvelcharacters.characterdetail.viewModel.CharacterDetailViewModel
import com.globant.marvelcharacters.common.result.Result
import com.globant.marvelcharacters.domain.model.CharacterDetails
import com.globant.marvelcharacters.domain.usecase.characterdetails.CharacterDetailUseCase
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
class CharacterDetailViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var characterDetailUseCase: CharacterDetailUseCase

    @Mock
    private lateinit var resultObserver: Observer<CharacterDetails>

    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    @Before
    fun setUp() {
        characterDetailViewModel = CharacterDetailViewModel(characterDetailUseCase)
    }

    @Test
    fun testGetCharacterDetailWithSuccess() {
        runBlockingTest {
            whenever(characterDetailUseCase.getCharacterDetails(any())).thenReturn(
                Result.Success(
                    CharacterDetails(
                        id = 101,
                        name = "name",
                        thumbnailUrl = "test.jpg",
                        description = "description"
                    )
                )
            )

            characterDetailViewModel.subscribeCharacterDetail().characterDetail.observeForever(
                resultObserver
            )
            characterDetailViewModel.getCharacterDetail("101")
            verify(characterDetailUseCase).getCharacterDetails(any())
            verify(resultObserver).onChanged(any())
            characterDetailViewModel.subscribeCharacterDetail().characterDetail.removeObserver(
                resultObserver
            )
        }
    }

    @Test
    fun testGetCharacterDetailWithError() {
        runBlockingTest {
            whenever(characterDetailUseCase.getCharacterDetails(any())).thenReturn(
                Result.Error(Exception())
            )

            characterDetailViewModel.subscribeCharacterDetail().characterDetail.observeForever(
                resultObserver
            )
            characterDetailViewModel.getCharacterDetail("101")
            verify(resultObserver, never()).onChanged(any())
            characterDetailViewModel.subscribeCharacterDetail().characterDetail.removeObserver(
                resultObserver
            )
        }
    }
}