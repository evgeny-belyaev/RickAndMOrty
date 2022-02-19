package com.ugene.rickandmorty.ui.dashboard

import androidx.lifecycle.ViewModel
import com.ugene.rickandmorty.di.IComponent1
import javax.inject.Inject


class DashboardViewModel @Inject constructor(private val component1: IComponent1) : ViewModel() {

    private var i = 0

    init {
        i++
    }

    fun getI(): Int {
        return i;
    }

    fun m(): String {
        return component1.method()
    }
}