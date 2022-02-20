package com.ugene.rickandmorty.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass

fun <TBinding : ViewBinding> inflateViewBinding(
    kClass: KClass<TBinding>,
    parent: ViewGroup
): TBinding {
    val method = kClass.java.getMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.java
    )

    @Suppress("UNCHECKED_CAST")
    return method.invoke(
        null, LayoutInflater.from(parent.context),
        parent,
        false
    ) as TBinding
}

fun <T : AdapterItemBase> RecyclerView.setupPaging(
    viewAdapter: BaseAdapter<T>,
    viewModel: PagedListViewModel<*, *>
) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            val lastVisiblePosition =
                (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
            if (viewAdapter.itemCount - lastVisiblePosition < 30) {
                viewModel.requestNextPage()
            }
        }
    })
}

fun View.visibleOrGone(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
