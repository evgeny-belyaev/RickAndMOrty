package com.ugene.rickandmorty.ui.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ugene.rickandmorty.FragmentBase
import com.ugene.rickandmorty.databinding.FragmentLocationsBinding
import com.ugene.rickandmorty.ui.OnItemClickListener
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

            }
        })

        binding.locationsList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = viewAdapter
        }

        binding.locationsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val lastVisiblePosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                if (viewAdapter.itemCount - lastVisiblePosition < 30) {
                    locationsViewModel.requestNextPage()
                }
            }
        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            locationsViewModel.refresh()
        }

        locationsViewModel.states.observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = it == ListState.Loading
        }

        locationsViewModel.locations.observe(viewLifecycleOwner) {
            viewAdapter.setModel(it)
        }
    }
}