package com.ugene.rickandmorty.ui.locations

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.ugene.rickandmorty.api.ApiService
import com.ugene.rickandmorty.api.LocationsResult
import com.ugene.rickandmorty.api.ServiceFactory
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

enum class ListState {
    Loading,
    Idle
}

class LocationsViewModel @Inject constructor(serviceFactory: ServiceFactory) : ViewModel() {

    sealed class ApiRequest {}

    class RefreshRequest : ApiRequest()

    class NextPageRequest() : ApiRequest()

    private val requestSubject: BehaviorSubject<ApiRequest> =
        BehaviorSubject.createDefault(RefreshRequest())

    private val stateSubject: BehaviorSubject<ListState> =
        BehaviorSubject.createDefault(ListState.Idle)

    @Volatile
    private var nextPageUrl: String? = null

    private val flowable =
        requestSubject
            .observeOn(Schedulers.io())
            .concatMap {
                when (it) {
                    is RefreshRequest -> {
                        stateSubject.onNext(ListState.Loading)

                        serviceFactory.create<ApiService>().getLocationsFirstPage()
                    }
                    is NextPageRequest -> {
                        if (nextPageUrl == null) {
                            Observable.fromArray()
                        } else {
                            serviceFactory.create<ApiService>().getLocationsPage(nextPageUrl!!)
                        }
                    }
                }
            }
            .doOnError {
                stateSubject.onNext(ListState.Idle)
            }
            .doOnNext {
                stateSubject.onNext(ListState.Idle)
                nextPageUrl = it.info.next
            }
            .scan { list, page ->
                val isFirstPage = page.info.prev == null
                val accumulator = if (isFirstPage) emptyList() else list.results

                LocationsResult(page.info, accumulator + page.results)
            }
            .map { it.results }

            .replay(1)
            .autoConnect()

            .toFlowable(BackpressureStrategy.BUFFER)

    val states =
        LiveDataReactiveStreams.fromPublisher(stateSubject.toFlowable(BackpressureStrategy.BUFFER))

    val locations = LiveDataReactiveStreams.fromPublisher(flowable).map {
        return@map it.map {
            LocationsAdapter.Location(it.id, it.name, it.type, it.dimension)
        }
    }

    fun requestNextPage() {
        requestSubject.onNext(NextPageRequest())
    }

    fun refresh() {
        requestSubject.onNext(RefreshRequest())
    }
}