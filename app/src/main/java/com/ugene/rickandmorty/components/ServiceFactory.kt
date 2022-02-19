package com.ugene.rickandmorty.components

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceFactory @Inject constructor() {

    private val instance: Retrofit by lazy {
        createRetrofitInstance("https://rickandmortyapi.com") // fixme: hardcoded value
    }

    fun <T> create(service: Class<T>): T {
        return instance.create(service)
    }

    inline fun <reified T> create(): T {
        return create(T::class.java)
    }

    private fun createRetrofitInstance(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(
            OkHttpClient().newBuilder()
                .build()
        )
        .build()
}