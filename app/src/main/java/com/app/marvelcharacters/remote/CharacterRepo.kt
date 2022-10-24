package com.app.marvelcharacters.remote

import android.util.Log
import com.app.marvelcharacters.viewmodels.MainActivityViewModel

class CharacterRepo {
     val client: CharacterAPIClient?
    fun searchCharactersApi() {
        Log.d("CharacterRepo", "SearchValue " +2)
        client?.searchCharacters()
    }

    companion object {
        var instance: CharacterRepo? = null
            get() {
                if (field == null) {
                    field = CharacterRepo()
                }
                return field
            }
            private set
    }

    init {
        client = CharacterAPIClient.instance
    }
}