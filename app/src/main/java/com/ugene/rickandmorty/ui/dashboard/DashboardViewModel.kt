package com.ugene.rickandmorty.ui.dashboard

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.ugene.rickandmorty.api.ApiService
import com.ugene.rickandmorty.api.ServiceFactory
import io.reactivex.BackpressureStrategy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DashboardViewModel @Inject constructor(serviceFactory: ServiceFactory) :
    ViewModel() {

    private val subject = BehaviorSubject.createDefault(Unit)

    private val flowable =
        subject
            .observeOn(Schedulers.io())
            .flatMap {
                serviceFactory.create<ApiService>().getApis()
            }
            .replay(1).autoConnect()
            .toFlowable(BackpressureStrategy.BUFFER)

    val apis = LiveDataReactiveStreams.fromPublisher(flowable)

    fun refresh() {
        subject.onNext(Unit)
    }

}