package com.ugene.rickandmorty.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET("/api")
    fun getApis(): Observable<ApisEntity>

    @GET
    fun getLocationsPage(@Url url: String): Observable<LocationsResult>

    @GET("/api/location")
    fun getLocationsFirstPage(): Observable<LocationsResult>
}

