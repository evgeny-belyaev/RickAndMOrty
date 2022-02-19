package com.ugene.rickandmorty.components

import retrofit2.http.GET

interface ApiService {
    @GET("/api")
    suspend fun getApis(): ApisEntity
}