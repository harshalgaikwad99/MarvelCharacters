package com.globant.marvelcharacters.characterdetail.viewModel

import androidx.lifecycle.MutableLiveData
import com.globant.marvelcharacters.domain.model.CharacterDetails

data class CharacterDetailState(
    val showLoader: MutableLiveData<Boolean> = MutableLiveData(false),
    val showError: MutableLiveData<Boolean> = MutableLiveData(false),
    val showCharacterDetails: MutableLiveData<Boolean> =MutableLiveData(false),
    val characterDetail: MutableLiveData<CharacterDetails> = MutableLiveData()
) {
    fun showError(){
        showError.postValue(true)
        showLoader.postValue(false)
        showCharacterDetails.postValue(false)
    }

    fun showLoader(){
        showError.postValue(false)
        showLoader.postValue(true)
        showCharacterDetails.postValue(false)
    }

    fun showCharacterDetails(characterDetail:CharacterDetails){
        showError.postValue(false)
        showLoader.postValue(false)
        showCharacterDetails.postValue(true)
        this.characterDetail.postValue(characterDetail)
    }
}