package com.globant.marvelcharacters.characterlist.viewmodel

import androidx.lifecycle.ViewModel
import com.globant.marvelcharacters.domain.usecase.characterlist.CharacterListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(private val characterListUseCase: CharacterListUseCase) :
    ViewModel() {

    val characterListState = CharacterListState()

    internal fun getCharacterList() {
        characterListState.showLoader()
        CoroutineScope(Dispatchers.IO).launch {
            val result = characterListUseCase.getCharacterList()
            if (result.isSuccess) {
                characterListState.showCharacterList(result.getSuccessData.data)
            } else {
                characterListState.showError()
            }
        }
    }

    fun subscribeCharacterState() =
        characterListState
}