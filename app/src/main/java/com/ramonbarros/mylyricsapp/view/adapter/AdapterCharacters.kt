package com.ramonbarros.mylyricsapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ramonbarros.mylyricsapp.R
import com.ramonbarros.mylyricsapp.databinding.LayoutListCharactersBinding
import com.ramonbarros.mylyricsapp.domain.Character

class AdapterCharacters(private val list: List<Character>):
    RecyclerView.Adapter<AdapterCharacters.CharacterDataHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterDataHolder {
        val viewXML = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_characters, parent, false)
        val dataHolder = CharacterDataHolder(viewXML)
        return dataHolder
    }

    override fun onBindViewHolder(holder: CharacterDataHolder, position: Int) {
        val binding = holder.binding
        val char = list[position]
        binding.character = char
        binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class CharacterDataHolder(v: View): RecyclerView.ViewHolder(v) {
        val binding: LayoutListCharactersBinding = LayoutListCharactersBinding.bind(v)

    }
}