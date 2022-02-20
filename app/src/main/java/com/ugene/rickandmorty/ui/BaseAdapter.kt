package com.ugene.rickandmorty.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<TItem : AdapterItemBase>(
    private val viewHolderFactoryMapping: HashMap<Int, (ViewGroup) -> Any>,
    private val onItemClickListener: OnItemClickListener<TItem>? = null
) :
    RecyclerView.Adapter<BaseViewHolder<TItem>>() {

    private val items = mutableListOf<TItem>()

    fun setModel(newItems: List<TItem>) {
        val diffCallback = DiffCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        diffResult.dispatchUpdatesTo(this)

        items.apply {
            clear()
            addAll(newItems)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<TItem>, position: Int) {
        holder.bind(items[position], position, onItemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<TItem> =
        viewHolderFactoryMapping[viewType]!!.invoke(parent) as BaseViewHolder<TItem>

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].type

    private class DiffCallback<TItem>(
        private val oldList: List<TItem>,
        private val newList: List<TItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]


        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]
    }

}