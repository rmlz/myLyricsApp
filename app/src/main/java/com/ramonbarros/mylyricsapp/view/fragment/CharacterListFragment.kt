package com.ramonbarros.mylyricsapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramonbarros.mylyricsapp.databinding.FragmentCharacterListBinding
import com.ramonbarros.mylyricsapp.domain.Character
import com.ramonbarros.mylyricsapp.view.adapter.AdapterCharacters
import com.ramonbarros.mylyricsapp.viewmodel.ApiViewModel


class CharacterListFragment : Fragment() {

    lateinit var binding: FragmentCharacterListBinding
    private val viewModel: ApiViewModel by viewModels()

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        binding.characterFragment = this
        binding.lifecycleOwner = this

        viewModel.resultToScreen.observe(viewLifecycleOwner) { list ->
            showApiResult(list)
        }

        binding.rvCharacteres.layoutManager = LinearLayoutManager(context)


        return binding.root
    }

    fun showApiResult(list: List<Character>) {
        val adapter = AdapterCharacters(list)
        binding.rvCharacteres.adapter = adapter
    }

    fun apiCall() {
        viewModel.callAPI()
    }

}