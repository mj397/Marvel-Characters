package com.app.marvelcharacters.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.marvelcharacters.models.Results
import com.app.marvelcharacters.remote.CharacterRepo

class MainActivityViewModel : ViewModel() {
    private val characterRepo: CharacterRepo?


    fun searchCharacterApi() {
        Log.d(TAG, "SearchValue " + true)
        characterRepo?.searchCharactersApi()
    }

    companion object {
        private const val TAG = "MainActivityViewModel"
    }

    init {
        Log.d(TAG, "INITValue " + true)
        characterRepo = CharacterRepo.instance
    }

    val result: MutableLiveData<ArrayList<Results>> = characterRepo?.client!!.charactersList
}