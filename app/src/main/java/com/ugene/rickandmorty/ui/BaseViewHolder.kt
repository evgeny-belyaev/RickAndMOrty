package com.ugene.rickandmorty.ui

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val context: Context get() = itemView.context

    abstract fun bind(
        item: T,
        position: Int,
        onItemClickListener: OnItemClickListener<T>? = null
    )

    open fun onRecycled() {}

    protected fun attachOnItemClickListener(
        onItemClickListener: OnItemClickListener<T>?,
        root: View,
        item: T,
        position: Int
    ) {
        if (onItemClickListener != null) {
            root.setOnClickListener {
                onItemClickListener.onItemClicked(item, position, root)
            }
        }
    }
}