package com.ugene.rickandmorty.ui.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ugene.rickandmorty.FragmentBase
import com.ugene.rickandmorty.databinding.FragmentLocationsBinding
import com.ugene.rickandmorty.ui.ListState
import com.ugene.rickandmorty.ui.OnItemClickListener
import com.ugene.rickandmorty.ui.setupPaging
import dagger.android.support.AndroidSupportInjection

class LocationsFragment : FragmentBase<FragmentLocationsBinding>() {
    private val locationsViewModel by lazy {
        getViewModel<LocationsViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setBinding(FragmentLocationsBinding.inflate(inflater, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewAdapter = LocationsAdapter(object : OnItemClickListener<LocationsAdapter.Location> {
            override fun onItemClicked(item: LocationsAdapter.Location, position: Int, view: View) {
                findNavController().navigate(
                    LocationsFragmentDirections.actionNavigationLocationsToLocationDetailsFragment(
                        item.name, item.locationType, item.dimension
                    )
                )
            }
        })

        binding.list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = viewAdapter
        }

        binding.list.setupPaging(viewAdapter, locationsViewModel)

        binding.swipeRefreshLayout.setOnRefreshListener {
            locationsViewModel.refresh()
        }

        locationsViewModel.states.observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = it == ListState.Loading
        }

        locationsViewModel.items.observe(viewLifecycleOwner) {
            viewAdapter.setModel(it)
        }
    }
}

