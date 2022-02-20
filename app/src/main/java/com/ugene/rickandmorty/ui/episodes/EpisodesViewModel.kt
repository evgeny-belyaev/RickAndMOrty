package com.ugene.rickandmorty.ui.episodes

import com.ugene.rickandmorty.api.ApiService
import com.ugene.rickandmorty.api.BaseEntity
import com.ugene.rickandmorty.api.EpisodeEntity
import com.ugene.rickandmorty.api.ServiceFactory
import com.ugene.rickandmorty.ui.PagedListViewModel
import io.reactivex.Observable
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(private val serviceFactory: ServiceFactory) :
    PagedListViewModel<EpisodeEntity, EpisodesAdapter.Episode>() {

    override fun getFirstPage(): Observable<BaseEntity<EpisodeEntity>> {
        return serviceFactory.create<ApiService>().getEpisodesFirstPage()
    }

    override fun getPage(nextPageUrl: String): Observable<BaseEntity<EpisodeEntity>> {
        return serviceFactory.create<ApiService>().getEpisodesPage(nextPageUrl)
    }

    override fun convertToAdapterItem(entity: EpisodeEntity): EpisodesAdapter.Episode {
        return EpisodesAdapter.Episode(entity.name, entity.air_date, entity.episode)
    }
}
