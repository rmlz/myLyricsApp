package com.ramonbarros.mylyricsapp.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.ramonbarros.mylyricsapp.domain.LoginData
import com.ramonbarros.mylyricsapp.domain.LoginResult
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginRepository {
        suspend fun login(data: LoginData): LoginResult = suspendCoroutine {
            val loginResult = LoginResult()
            val firebaseAuth = FirebaseAuth.getInstance()
            val operacao = firebaseAuth.signInWithEmailAndPassword(data.email, data.pass)
            operacao.addOnCompleteListener { resultado ->
                if (resultado.isSuccessful) {
                    loginResult.result = "LOGIN_FIREBASE_SUCCESS"
                } else {
                    Log.w(TAG, "signInWithEmail:failure", resultado.exception)
                    loginResult.error = "LOGIN_FIREBASE_ERROR"
                }
                it.resume(loginResult)
            }
        }


    fun forgotPass(data: LoginData) {
        return
    }

    fun register(data: LoginData) {
        return
    }
}