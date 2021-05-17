package com.globant.marvelcharacters.characterlist.viewmodel

import androidx.lifecycle.MutableLiveData
import com.globant.marvelcharacters.domain.model.Character

data class CharacterListState constructor(
    val showLoader: MutableLiveData<Boolean> = MutableLiveData(false),
    val showError: MutableLiveData<Boolean> = MutableLiveData(false),
    val showCharacterList: MutableLiveData<Boolean> = MutableLiveData(false),
    val characterList: MutableLiveData<List<Character>> = MutableLiveData()
) {
    fun showError() {
        showError.postValue(true)
        showLoader.postValue(false)
        showCharacterList.postValue(false)
    }

    fun showLoader() {
        showError.postValue(false)
        showLoader.postValue(true)
        showCharacterList.postValue(false)
    }

    fun showCharacterList(characterList: List<Character>) {
        showError.postValue(false)
        showLoader.postValue(false)
        showCharacterList.postValue(true)
        this.characterList.postValue(characterList)
    }

}