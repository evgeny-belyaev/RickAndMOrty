package com.ugene.rickandmorty.di

import android.app.Application
import com.ugene.rickandmorty.RickAndMortyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidModule::class,
        ViewModelModule::class,
        MyModule::class,
        ApiModule::class,

        AndroidInjectionModule::class]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(appController: RickAndMortyApplication)
}