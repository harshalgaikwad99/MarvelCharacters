package com.globant.marvelcharacters.characterdetail.viewModel

import androidx.lifecycle.ViewModel
import com.globant.marvelcharacters.domain.usecase.characterdetails.CharacterDetailUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(private val characterDetailUseCase: CharacterDetailUseCase) :
    ViewModel() {

    val characterDetailState = CharacterDetailState()

    internal fun getCharacterDetail(characterId: String) {
        characterDetailState.showLoader()
        CoroutineScope(Dispatchers.IO).launch {
            val characterDetails = characterDetailUseCase.getCharacterDetails(characterId)
            if (characterDetails.isSuccess) {
                characterDetailState.showCharacterDetails(characterDetails.getSuccessData.data)
            } else {
                characterDetailState.showError()
            }
        }
    }

    fun subscribeCharacterDetail() =
        characterDetailState
}