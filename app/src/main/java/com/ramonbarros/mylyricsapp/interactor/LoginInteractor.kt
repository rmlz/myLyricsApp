package com.ramonbarros.mylyricsapp.interactor

import android.widget.Toast
import com.ramonbarros.mylyricsapp.R
import com.ramonbarros.mylyricsapp.domain.LoginData
import com.ramonbarros.mylyricsapp.domain.LoginResult
import com.ramonbarros.mylyricsapp.repository.LoginRepository

class LoginInteractor {
    val repo = LoginRepository()

    suspend fun login(data: LoginData): LoginResult {
        val result = LoginResult()

        if (data.email.isBlank()) {
            result.error = "EMPTY EMAIL"
            return result
        }
        if (data.pass.isBlank()) {
            result.error = "EMPTY PASSWORD"
            return result
        }
        if (data.pass.length < 6) {
            result.error = "PASS MINIMUM LIMIT"
            return result
        }
        if (data.pass.length > 15) {
            result.error = "PASS MAXIMUM LIMIT"
            return result
        }

        return repo.login(data)
    }

    fun forgotPass() {
        return
    }

    fun register() {
        return
    }
}