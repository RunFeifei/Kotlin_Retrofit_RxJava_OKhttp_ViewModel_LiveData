package com.uestc.medicine.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.uestc.medicine.R
import com.uestc.medicine.net.ShoeEntity

/**
 * Created by PengFeifei on 2019-11-28.
 */
class ShoesAdapter(diffCallback: ShoeDiffCallback) : PagedListAdapter<ShoeEntity, ShoesAdapter.ShowViewHolder>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        return ShowViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_list_item_shoe,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class ShowViewHolder constructor(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(data: ShoeEntity?) {
            data ?: return
            view.findViewById<TextView>(R.id.name).text = data.name
        }

    }
}

class ShoeDiffCallback : DiffUtil.ItemCallback<ShoeEntity>() {

    override fun areItemsTheSame(oldItem: ShoeEntity, newItem: ShoeEntity): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: ShoeEntity, newItem: ShoeEntity): Boolean {
        return false
    }
}