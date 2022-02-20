package com.ugene.rickandmorty.ui.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ugene.rickandmorty.FragmentBase
import com.ugene.rickandmorty.databinding.FragmentEpisodesBinding
import com.ugene.rickandmorty.ui.ListState
import com.ugene.rickandmorty.ui.OnItemClickListener
import com.ugene.rickandmorty.ui.setupPaging
import dagger.android.support.AndroidSupportInjection

class EpisodesFragment : FragmentBase<FragmentEpisodesBinding>() {
    private val episodesViewModel by lazy {
        getViewModel<EpisodesViewModel>()
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
        setBinding(FragmentEpisodesBinding.inflate(inflater, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewAdapter =
            EpisodesAdapter(object : OnItemClickListener<EpisodesAdapter.Episode> {
                override fun onItemClicked(
                    item: EpisodesAdapter.Episode,
                    position: Int,
                    view: View
                ) {
                    findNavController().navigate(
                        EpisodesFragmentDirections.actionNavigationEpisodesToEpisodesDetailsFragment(
                            item.name, item.airDate, item.episode
                        )
                    )
                }
            })

        binding.list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = viewAdapter
        }

        binding.list.setupPaging(viewAdapter, episodesViewModel)

        binding.swipeRefreshLayout.setOnRefreshListener {
            episodesViewModel.refresh()
        }

        episodesViewModel.states.observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = it == ListState.Loading
        }

        episodesViewModel.items.observe(viewLifecycleOwner) {
            viewAdapter.setModel(it)
        }
    }
}