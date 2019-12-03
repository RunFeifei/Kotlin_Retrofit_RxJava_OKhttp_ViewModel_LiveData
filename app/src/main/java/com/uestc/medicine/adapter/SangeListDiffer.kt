package com.uestc.medicine.adapter

import androidx.recyclerview.widget.DiffUtil
import com.uestc.medicine.net.DishShopEntity
import zlc.season.ironbranch.ioThread
import zlc.season.ironbranch.mainThread
import java.util.*

internal class SangeListDiffer {
    var adapter: DishPagingAdapter? = null

    private val diffCallback = PagingDiffCallback()

    private var list = emptyList<DishShopEntity>()
    private var currentList = emptyList<DishShopEntity>()

    // Max generation of currently scheduled runnable
    private var mMaxScheduledGeneration: Int = 0

    internal fun size() = currentList.size

    internal fun get(position: Int) = currentList[position]

    internal fun submitList(newList: List<DishShopEntity>, initial: Boolean = false) {
        if (initial) {
            list = newList
            currentList = Collections.unmodifiableList(newList)
            adapter?.notifyDataSetChanged()
            return
        }

        if (newList === list) {
            // nothing to do
            return
        }

        // incrementing generation means any currently-running diffs are discarded when they finish
        val runGeneration = ++mMaxScheduledGeneration

        // initial simple removeItem toList
        if (newList.isEmpty()) {

            val countRemoved = list.size
            list = emptyList()
            currentList = emptyList()
            // notify last, after list is updated
            adapter?.notifyItemRangeRemoved(0, countRemoved)
            return
        }

        // initial simple first insert
        if (list.isEmpty()) {
            list = newList
            currentList = Collections.unmodifiableList(newList)
            adapter?.notifyItemRangeInserted(0, newList.size)
            return
        }

        val oldList = list

        ioThread {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return oldList.size
                }

                override fun getNewListSize(): Int {
                    return newList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return diffCallback.areItemsTheSame(
                        oldList[oldItemPosition], newList[newItemPosition]
                    )
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return diffCallback.areContentsTheSame(
                        oldList[oldItemPosition], newList[newItemPosition]
                    )
                }

                override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
                    return diffCallback.getChangePayload(
                        oldList[oldItemPosition], newList[newItemPosition]
                    )
                }
            })

            mainThread {
                if (mMaxScheduledGeneration == runGeneration) {
                    latchList(newList, result)
                }
            }
        }
    }

    private fun latchList(newList: List<DishShopEntity>, diffResult: DiffUtil.DiffResult) {
        list = newList
        // notify last, after list is updated
        currentList = Collections.unmodifiableList(newList)
        adapter?.let {
            diffResult.dispatchUpdatesTo(it)
        }
    }

}

class PagingDiffCallback : DiffUtil.ItemCallback<DishShopEntity>() {
    override fun areItemsTheSame(oldItem: DishShopEntity, newItem: DishShopEntity): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: DishShopEntity, newItem: DishShopEntity): Boolean {
        return oldItem.name == newItem.name
    }


}
