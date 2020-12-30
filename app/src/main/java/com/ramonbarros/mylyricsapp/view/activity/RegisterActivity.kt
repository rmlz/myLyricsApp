package com.ramonbarros.mylyricsapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.ramonbarros.mylyricsapp.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btRegister.setOnClickListener() { register() }
        ibBack.setOnClickListener()  {goBack()}
    }
    fun register(){
        val nickname = etRegisterNickname.text.toString()
        val email = etRegisterEmail.text.toString()
        val pass = etRegisterPassword.text.toString()
        val repeatPass = etRegisterRepeatPassword.text.toString()

        if (nickname.isBlank()){
            Toast.makeText(this, getString(R.string.nicknameRequired), Toast.LENGTH_LONG).show()
            return
        }

        if (nickname.length < 3) {
            Toast.makeText(this, getString(R.string.nicknameMinimumLength), Toast.LENGTH_LONG).show()
            return
        }

        if (nickname.length > 15) {
            Toast.makeText(this, getString(R.string.nicknameMaximumLength), Toast.LENGTH_LONG).show()
            return
        }

        if (email.isBlank()) {
            Toast.makeText(this, getString(R.string.emailRequired), Toast.LENGTH_LONG).show()
            return
        }
        if (pass.isBlank()) {
            Toast.makeText(this, getString(R.string.passwordRequired), Toast.LENGTH_LONG).show()
            return
        }

        if (pass.length < 6) {
            Toast.makeText(this, getString(R.string.passwordMinimumLength), Toast.LENGTH_LONG).show()
            return
        }

        if (pass.length > 15) {
            Toast.makeText(this, getString(R.string.passwordMaximumLength), Toast.LENGTH_LONG).show()
            return
        }

        if (repeatPass.isBlank()) {
            Toast.makeText(this, getString(R.string.repeatPasswordRequired), Toast.LENGTH_LONG).show()
            return
        }

        if (repeatPass.length < 6) {
            Toast.makeText(this, getString(R.string.passwordMinimumLength), Toast.LENGTH_LONG).show()
            return
        }

        if (repeatPass.length > 15) {
            Toast.makeText(this, getString(R.string.passwordMaximumLength), Toast.LENGTH_LONG).show()
            return
        }
        if (repeatPass != pass) {
            Toast.makeText(this, getString(R.string.passwordVariying), Toast.LENGTH_LONG).show()
            return
        }

        val auth = FirebaseAuth.getInstance()
        val operacao = auth.createUserWithEmailAndPassword(email, pass)
        operacao.addOnCompleteListener { it ->
            if (it.isSuccessful) {
                Toast.makeText(this, "Cadastro efetuado, olhe a caixa de entrada do seu e-mail", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, it.exception?.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun goBack(){
        finish()
    }

}