package com.ramonbarros.mylyricsapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ramonbarros.mylyricsapp.domain.Character
import com.ramonbarros.mylyricsapp.interactor.ApiInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApiViewModel (val app: Application) : AndroidViewModel(app), CoroutineScope{

    override val coroutineContext = Dispatchers.Main

    private val interactor = ApiInteractor()

    val resultToScreen = MutableLiveData<List<Character>>()

    fun callAPI (){

        launch {
            val characterList = interactor.callAPI()
            resultToScreen.value = characterList
        }
    }
}