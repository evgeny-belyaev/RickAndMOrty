package com.ugene.rickandmorty.ui.characters

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ugene.rickandmorty.databinding.ItemCharacterBinding
import com.ugene.rickandmorty.ui.BaseViewHolder
import com.ugene.rickandmorty.ui.OnItemClickListener
import com.ugene.rickandmorty.ui.inflateViewBinding

class CharacterViewHolder(private val binding: ItemCharacterBinding) :
    BaseViewHolder<CharactersAdapter.Character>(binding.root) {
    constructor(parent: ViewGroup) : this(
        inflateViewBinding(
            ItemCharacterBinding::class,
            parent
        )
    )

    override fun bind(
        item: CharactersAdapter.Character,
        position: Int,
        onItemClickListener: OnItemClickListener<CharactersAdapter.Character>?
    ) {
        binding.name.text = item.name
        binding.species.text = item.species
        binding.status.text = item.status
        binding.gender.text = item.gender

        if (item.image.isNotEmpty()) {
            Glide.with(binding.image)
                .load(item.image)
                .circleCrop()
                .into(binding.image)
        }

        attachOnItemClickListener(onItemClickListener, binding.root, item, position)
    }
}