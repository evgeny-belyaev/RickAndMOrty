package com.ugene.rickandmorty.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    fun getLocationsPage(@Url url: String): Observable<BaseEntity<LocationEntity>>

    @GET("/api/location")
    fun getLocationsFirstPage(): Observable<BaseEntity<LocationEntity>>

    @GET
    fun getCharactersPage(@Url url: String): Observable<BaseEntity<CharacterEntity>>

    @GET("/api/character")
    fun getCharactersFirstPage(): Observable<BaseEntity<CharacterEntity>>

    @GET
    fun getEpisodesPage(@Url url: String): Observable<BaseEntity<EpisodeEntity>>

    @GET("/api/episode")
    fun getEpisodesFirstPage(): Observable<BaseEntity<EpisodeEntity>>
}

