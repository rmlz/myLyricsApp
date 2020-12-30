package com.ramonbarros.mylyricsapp.interactor

import com.ramonbarros.mylyricsapp.domain.Character
import com.ramonbarros.mylyricsapp.repository.ApiRepository

class ApiInteractor {

    private val repo = ApiRepository()

    suspend fun callAPI(): List<Character> {
        return repo.callAPI()
    }
}