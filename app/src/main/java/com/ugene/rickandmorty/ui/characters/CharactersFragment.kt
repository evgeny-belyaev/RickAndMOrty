package com.ugene.rickandmorty.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ugene.rickandmorty.FragmentBase
import com.ugene.rickandmorty.databinding.FragmentCharactersBinding
import com.ugene.rickandmorty.ui.ListState
import com.ugene.rickandmorty.ui.OnItemClickListener
import com.ugene.rickandmorty.ui.setupPaging
import dagger.android.support.AndroidSupportInjection

class CharactersFragment : FragmentBase<FragmentCharactersBinding>() {
    private val charactersViewModel by lazy {
        getViewModel<CharactersViewModel>()
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
        setBinding(FragmentCharactersBinding.inflate(inflater, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewAdapter =
            CharactersAdapter(object : OnItemClickListener<CharactersAdapter.Character> {
                override fun onItemClicked(
                    item: CharactersAdapter.Character,
                    position: Int,
                    view: View
                ) {
                    findNavController().navigate(
                        CharactersFragmentDirections.actionNavigationCharactersToCharacterDetailsFragment(
                            item.name, item.species, item.status, item.gender, item.image
                        )
                    )

                }
            })

        binding.list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = viewAdapter
        }

        binding.list.setupPaging(viewAdapter, charactersViewModel)

        binding.swipeRefreshLayout.setOnRefreshListener {
            charactersViewModel.refresh()
        }

        charactersViewModel.states.observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = it == ListState.Loading
        }

        charactersViewModel.items.observe(viewLifecycleOwner) {
            viewAdapter.setModel(it)
        }
    }
}