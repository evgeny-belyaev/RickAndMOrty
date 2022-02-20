package com.ugene.rickandmorty.ui

import android.view.View

interface OnItemClickListener<T> {
    fun onItemClicked(item: T, position: Int, view: View)
}