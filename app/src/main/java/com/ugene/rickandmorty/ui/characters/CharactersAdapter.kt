package com.ugene.rickandmorty.ui.characters

import android.view.ViewGroup
import com.ugene.rickandmorty.ui.AdapterItemBase
import com.ugene.rickandmorty.ui.BaseAdapter
import com.ugene.rickandmorty.ui.OnItemClickListener

class CharactersAdapter(onItemClickListener: OnItemClickListener<Character>) :
    BaseAdapter<CharactersAdapter.Character>(
        hashMapOf(
            ItemType.Character.ordinal to { parent: ViewGroup -> CharacterViewHolder(parent) },
        ),
        onItemClickListener
    ) {

    enum class ItemType {
        Character
    }

    data class Character(
        val id: Int,
        val name: String,
        val species: String,
        val status: String,
        val gender: String,
        val image: String
    ) : AdapterItemBase(ItemType.Character.ordinal)
}