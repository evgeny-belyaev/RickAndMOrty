package com.ugene.rickandmorty.ui

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.ugene.rickandmorty.api.BaseEntity
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

abstract class PagedListViewModel<TEntity, TAdapterItem> : ViewModel() {
    private val requestSubject: BehaviorSubject<ApiRequest> =
        BehaviorSubject.createDefault(ApiRequest.Refresh)

    private val stateSubject: BehaviorSubject<ListState> =
        BehaviorSubject.createDefault(ListState.Idle)

    @Volatile
    private var nextPageUrl: String? = null

    private val flowable =
        requestSubject
            .observeOn(Schedulers.io())
            .concatMap {
                when (it) {
                    ApiRequest.Refresh -> {
                        stateSubject.onNext(ListState.Loading)

                        getFirstPage()
                    }
                    ApiRequest.NextPage -> {
                        if (nextPageUrl == null) {
                            Observable.fromArray()
                        } else {
                            getPage(nextPageUrl!!)
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

                BaseEntity(page.info, accumulator + page.results)
            }
            .map { it.results }

            .replay(1)
            .autoConnect()

            .toFlowable(BackpressureStrategy.BUFFER)

    val states =
        LiveDataReactiveStreams.fromPublisher(stateSubject.toFlowable(BackpressureStrategy.BUFFER))

    val items = LiveDataReactiveStreams.fromPublisher(flowable).map { list ->
        return@map list.map {
            convertToAdapterItem(it)
        }
    }

    abstract fun convertToAdapterItem(entity: TEntity): TAdapterItem

    abstract fun getFirstPage(): Observable<BaseEntity<TEntity>>

    abstract fun getPage(nextPageUrl: String): Observable<BaseEntity<TEntity>>

    fun requestNextPage() {
        requestSubject.onNext(ApiRequest.NextPage)
    }

    fun refresh() {
        requestSubject.onNext(ApiRequest.Refresh)
    }
}