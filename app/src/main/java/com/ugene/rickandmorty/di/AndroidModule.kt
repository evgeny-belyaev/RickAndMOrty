package com.ugene.rickandmorty.di

import com.ugene.rickandmorty.MainActivity
import com.ugene.rickandmorty.ui.characters.CharactersFragment
import com.ugene.rickandmorty.ui.details.CharacterDetailsFragment
import com.ugene.rickandmorty.ui.details.EpisodesDetailsFragment
import com.ugene.rickandmorty.ui.details.LocationDetailsFragment
import com.ugene.rickandmorty.ui.episodes.EpisodesFragment
import com.ugene.rickandmorty.ui.locations.LocationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidModule {
    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector()
    abstract fun contributeDashboardFragment(): CharactersFragment

    @ContributesAndroidInjector()
    abstract fun contributeLocationsFragment(): LocationsFragment

    @ContributesAndroidInjector()
    abstract fun contributeEpisodesFragment(): EpisodesFragment

    @ContributesAndroidInjector()
    abstract fun contributeLocationDetailsFragment(): LocationDetailsFragment

    @ContributesAndroidInjector()
    abstract fun contributeEpisodesDetailsFragment(): EpisodesDetailsFragment

    @ContributesAndroidInjector()
    abstract fun contributeCharacterDetailsFragment(): CharacterDetailsFragment
}
