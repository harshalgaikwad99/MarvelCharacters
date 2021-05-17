package com.globant.marvelcharacters.characterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.globant.marvelcharacters.databinding.CardCharacterListBinding
import com.globant.marvelcharacters.domain.model.Character

class CharacterListAdapter constructor(
    private val characterList: List<Character>,
    private val onCharacterClicked: (String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cardCharacterListBinding =
            CardCharacterListBinding.inflate(layoutInflater, parent, false)
        return CharacterListViewHolder(cardCharacterListBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CharacterListViewHolder) {
            holder.characterBinding.character = characterList[position]
        }
    }

    override fun getItemCount(): Int = characterList.size

    private inner class CharacterListViewHolder(cardCharacterListBinding: CardCharacterListBinding) :
        RecyclerView.ViewHolder(cardCharacterListBinding.root) {

        val characterBinding: CardCharacterListBinding = cardCharacterListBinding

        init {
            cardCharacterListBinding.root.setOnClickListener {
                onCharacterClicked.invoke(characterList.get(adapterPosition).id.toString())
            }
        }
    }
}