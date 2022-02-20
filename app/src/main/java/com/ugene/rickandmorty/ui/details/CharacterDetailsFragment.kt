package com.ugene.rickandmorty.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ugene.rickandmorty.FragmentBase
import com.ugene.rickandmorty.databinding.FragmentCharacterDetailsBinding
import dagger.android.support.AndroidSupportInjection

class CharacterDetailsFragment : FragmentBase<FragmentCharacterDetailsBinding>() {
    private val args by navArgs<CharacterDetailsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setBinding(FragmentCharacterDetailsBinding.inflate(inflater, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.name.text = args.name
        binding.species.text = args.species
        binding.status.text = args.status
        binding.gender.text = args.gender

        if (args.image.isNotEmpty()) {
            Glide.with(binding.image)
                .load(args.image)
                .circleCrop()
                .into(binding.image)
        }

    }
}
