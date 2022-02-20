package com.ugene.rickandmorty.ui.locations

import android.view.ViewGroup
import com.ugene.rickandmorty.ui.AdapterItemBase
import com.ugene.rickandmorty.ui.BaseAdapter
import com.ugene.rickandmorty.ui.OnItemClickListener

class LocationsAdapter(onItemClickListener: OnItemClickListener<Location>) :
    BaseAdapter<LocationsAdapter.Location>(
        hashMapOf(
            ItemType.Location.ordinal to { parent: ViewGroup -> LocationViewHolder(parent) },
        ),
        onItemClickListener
    ) {

    enum class ItemType {
        Location
    }

    data class Location(
        val id: Int,
        val name: String,
        val locationType: String,
        val dimension: String
    ) : AdapterItemBase(ItemType.Location.ordinal)
}