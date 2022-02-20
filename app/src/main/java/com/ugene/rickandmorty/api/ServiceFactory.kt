package com.ugene.rickandmorty.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("API", chain.request().url().toString())
        return chain.proceed(chain.request())
    }

}

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
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(
            OkHttpClient().newBuilder()
                .addInterceptor(LoggingInterceptor())
                .build()
        )
        .build()
}