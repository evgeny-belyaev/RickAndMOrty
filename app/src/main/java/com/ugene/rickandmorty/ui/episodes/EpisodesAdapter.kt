package com.ugene.rickandmorty.ui.episodes

import android.view.ViewGroup
import com.ugene.rickandmorty.ui.AdapterItemBase
import com.ugene.rickandmorty.ui.BaseAdapter
import com.ugene.rickandmorty.ui.OnItemClickListener

class EpisodesAdapter(onItemClickListener: OnItemClickListener<Episode>) :
    BaseAdapter<EpisodesAdapter.Episode>(
        hashMapOf(
            ItemType.Episode.ordinal to { parent: ViewGroup -> EpisodeViewHolder(parent) },
        ),
        onItemClickListener
    ) {

    enum class ItemType {
        Episode
    }

    data class Episode(
        val name: String,
        val airDate: String,
        val episode: String,
    ) : AdapterItemBase(ItemType.Episode.ordinal)
}