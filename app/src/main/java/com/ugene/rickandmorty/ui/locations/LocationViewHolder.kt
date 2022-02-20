package com.ugene.rickandmorty.ui.locations

import android.view.ViewGroup
import com.ugene.rickandmorty.databinding.ItemLocationsBinding
import com.ugene.rickandmorty.ui.BaseViewHolder
import com.ugene.rickandmorty.ui.OnItemClickListener
import com.ugene.rickandmorty.ui.inflateViewBinding


class LocationViewHolder(private val binding: ItemLocationsBinding) :
    BaseViewHolder<LocationsAdapter.Location>(binding.root) {
    constructor(parent: ViewGroup) : this(
        inflateViewBinding(
            ItemLocationsBinding::class,
            parent
        )
    )

    override fun bind(
        item: LocationsAdapter.Location,
        position: Int,
        onItemClickListener: OnItemClickListener<LocationsAdapter.Location>?
    ) {
        binding.name.text = item.name
        binding.dimension.text = item.dimension
        binding.type.text = item.locationType

        attachOnItemClickListener(onItemClickListener, binding.root, item, position)
    }
}