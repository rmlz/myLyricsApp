package com.ramonbarros.mylyricsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ramonbarros.mylyricsapp.R
import com.ramonbarros.mylyricsapp.domain.LoginData
import com.ramonbarros.mylyricsapp.domain.LoginResult
import com.ramonbarros.mylyricsapp.interactor.LoginInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(val app: Application): AndroidViewModel(app), CoroutineScope {

    override val coroutineContext = Dispatchers.Main
    private val interactor = LoginInteractor()

    val screenResult = MutableLiveData<LoginResult>()

    fun login(data: LoginData) {

        launch {
            val result = interactor.login(data)
            if (result.error != "") {
                if (result.error == "EMPTY EMAIL") {
                    result.error = app.getString(R.string.emailRequired)
                } else if (result.error == "EMPTY PASSWORD") {
                    result.error = app.getString(R.string.passwordRequired)
                } else if (result.error == "PASS MINIMUM LIMIT") {
                    result.error = app.getString(R.string.passwordMinimumLength)
                } else if (result.error == "PASS MAXIMUM LIMIT"){
                    result.error = app.getString(R.string.passwordMaximumLength)
                } else if (result.error == "LOGIN_FIREBASE_ERROR") {
                    result.error = app.getString(R.string.authError)
                }
            } else {
                result.error = ""
                result.result = "Success"
            }
            screenResult.value = result
        }
    }
}