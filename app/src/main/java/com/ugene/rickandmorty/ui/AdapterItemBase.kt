package com.ugene.rickandmorty.ui

abstract class AdapterItemBase constructor(val type: Int = 0) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }
}