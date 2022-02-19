package com.ugene.rickandmorty.di

import com.ugene.rickandmorty.components.ServiceFactory
import dagger.Module
import dagger.Provides

@Module
class ApiModule {
    @Provides
    fun provideServiceFactory(): ServiceFactory {
        return ServiceFactory()
    }
}