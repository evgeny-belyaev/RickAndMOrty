package com.ugene.rickandmorty.di

import com.ugene.rickandmorty.api.ServiceFactory
import dagger.Module
import dagger.Provides

@Module
class ApiModule {
    @Provides
    fun provideServiceFactory(): ServiceFactory {
        return ServiceFactory()
    }
}