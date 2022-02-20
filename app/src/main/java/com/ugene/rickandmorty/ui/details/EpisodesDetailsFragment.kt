package com.ugene.rickandmorty.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ugene.rickandmorty.FragmentBase
import com.ugene.rickandmorty.databinding.FragmentEpisodesDetailsBinding
import dagger.android.support.AndroidSupportInjection

class EpisodesDetailsFragment : FragmentBase<FragmentEpisodesDetailsBinding>() {
    private val args by navArgs<EpisodesDetailsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setBinding(FragmentEpisodesDetailsBinding.inflate(inflater, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.name.text = args.name
        binding.airDate.text = args.airDate
        binding.episode.text = args.episode
    }
}
