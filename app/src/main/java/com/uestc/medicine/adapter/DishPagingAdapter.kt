package com.uestc.medicine.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.uestc.medicine.R
import com.uestc.medicine.net.DishShopEntity
import com.uestc.medicine.paging.ShopDataSource

/**
 * Created by PengFeifei on 2019-12-03.
 */
open class DishPagingAdapter(var source: ShopDataSource?) : RecyclerView.Adapter<ShopViewHolder>(){

    lateinit var listView: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        return if (viewType == 0) {
            ShopViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.layout_list_item_shoe,
                    parent,
                    false
                )
            )
        } else {
            ShopViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.dish_item_list_type,
                    parent,
                    false
                )
            )
        }
    }


    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bindData(getItem(position,true))
    }

    override fun getItemViewType(position: Int): Int {
        val data = getItem(position,true)
        return if (data != null && data.showType == 0) 0 else 1
    }

    private fun getItem(position: Int, fromBind: Boolean=false): DishShopEntity? {
        return source?.getItem(position,fromBind)
    }

    override fun onViewAttachedToWindow(holder: ShopViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.getPosition {
            getItem(it)
        }
    }

    override fun getItemCount(): Int {
        return if (source == null) 0 else source!!.getItemCount()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        listView = recyclerView
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        (recyclerView.itemAnimator as SimpleItemAnimator).changeDuration = 0
        (recyclerView.itemAnimator as SimpleItemAnimator).addDuration = 0
        (recyclerView.itemAnimator as SimpleItemAnimator).moveDuration = 0
        (recyclerView.itemAnimator as SimpleItemAnimator).removeDuration = 0
        recyclerView.addItemDecoration(StickyItemDecoration())
        source?.setAdapter(this)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        source?.setAdapter(null)
        source = null
    }


    fun ShopViewHolder.getPosition(block: ShopViewHolder.(Int) -> Unit) {
        this.adapterPosition.let {
            if (it != -1) {
                this.block(it)
            }
        }
    }

}


class ShopViewHolder constructor(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bindData(data: DishShopEntity?) {
        data ?: return
        view.findViewById<TextView>(R.id.name).text = data.name
        view.tag = data
    }

}