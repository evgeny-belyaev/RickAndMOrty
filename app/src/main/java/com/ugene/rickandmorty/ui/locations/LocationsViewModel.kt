package com.ugene.rickandmorty.ui.locations

import com.ugene.rickandmorty.api.ApiService
import com.ugene.rickandmorty.api.BaseEntity
import com.ugene.rickandmorty.api.LocationEntity
import com.ugene.rickandmorty.api.ServiceFactory
import com.ugene.rickandmorty.ui.PagedListViewModel
import io.reactivex.Observable
import javax.inject.Inject

class LocationsViewModel @Inject constructor(private val serviceFactory: ServiceFactory) :
    PagedListViewModel<LocationEntity, LocationsAdapter.Location>() {

    override fun getFirstPage(): Observable<BaseEntity<LocationEntity>> {
        return serviceFactory.create<ApiService>().getLocationsFirstPage()
    }

    override fun getPage(nextPageUrl: String): Observable<BaseEntity<LocationEntity>> {
        return serviceFactory.create<ApiService>().getLocationsPage(nextPageUrl)
    }

    override fun convertToAdapterItem(entity: LocationEntity): LocationsAdapter.Location {
        return LocationsAdapter.Location(entity.id, entity.name, entity.type, entity.dimension)
    }
}