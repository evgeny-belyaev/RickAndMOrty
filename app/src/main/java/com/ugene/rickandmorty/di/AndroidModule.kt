package com.ugene.rickandmorty.di

import com.ugene.rickandmorty.MainActivity
import com.ugene.rickandmorty.ui.dashboard.DashboardFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidModule {
    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector()
    abstract fun contributeDashboardFragment(): DashboardFragment
}