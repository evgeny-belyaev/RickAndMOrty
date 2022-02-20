package com.ugene.rickandmorty.ui.episodes

import android.view.ViewGroup
import com.ugene.rickandmorty.databinding.ItemEpisodeBinding
import com.ugene.rickandmorty.ui.BaseViewHolder
import com.ugene.rickandmorty.ui.OnItemClickListener
import com.ugene.rickandmorty.ui.inflateViewBinding

class EpisodeViewHolder(private val binding: ItemEpisodeBinding) :
    BaseViewHolder<EpisodesAdapter.Episode>(binding.root) {
    constructor(parent: ViewGroup) : this(
        inflateViewBinding(
            ItemEpisodeBinding::class,
            parent
        )
    )

    override fun bind(
        item: EpisodesAdapter.Episode,
        position: Int,
        onItemClickListener: OnItemClickListener<EpisodesAdapter.Episode>?
    ) {
        binding.name.text = item.name
        binding.airDate.text = item.airDate
        binding.episode.text = item.episode

        attachOnItemClickListener(onItemClickListener, binding.root, item, position)
    }
}