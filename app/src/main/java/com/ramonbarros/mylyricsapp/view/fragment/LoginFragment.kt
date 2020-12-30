package com.ramonbarros.mylyricsapp.view.fragment

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ramonbarros.mylyricsapp.R
import com.ramonbarros.mylyricsapp.databinding.FragmentLoginBinding
import com.ramonbarros.mylyricsapp.domain.LoginData
import com.ramonbarros.mylyricsapp.domain.LoginResult
import com.ramonbarros.mylyricsapp.view.activity.HomeActivity
import com.ramonbarros.mylyricsapp.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*


class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var navController: NavController
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.loginFragment = this
        binding.lifecycleOwner = this
        viewModel.screenResult.observe(viewLifecycleOwner) { loginResult ->

            processLoginResult(loginResult)
        }
        return binding.root
    }

    fun processLoginResult(res: LoginResult) {
        if (res.error != "") {
            Toast.makeText( context, res.error, Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(context, "Conexão estabelecida", Toast.LENGTH_LONG).show()
        navController.navigate(R.id.characterListFragment)
        //return
    /*val intentHome = Intent(this, HomeActivity::class.java)
        startActivity(intentHome)
        finish()*/
    }

    fun login(){
        val email = binding.etEmail.text.toString()
        val pass = binding.etPassword.text.toString()
        val data = LoginData(email, pass)

        viewModel.login(data)
    }


    fun register(){
        // val intentRegister = Intent(this, RegisterActivity::class.java)
        // startActivity(intentRegister)
        // finish()
    }

    fun forgotPass(){
        return
    }
}