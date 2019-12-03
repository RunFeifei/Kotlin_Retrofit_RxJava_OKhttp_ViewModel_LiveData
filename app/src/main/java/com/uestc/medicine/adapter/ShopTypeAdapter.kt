package com.uestc.medicine.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uestc.medicine.R
import com.uestc.medicine.net.DishBrandEntity
import com.uestc.medicine.util.click

/**
 * Created by PengFeifei on 2019-11-28.
 */
class ShopTypeAdapter : RecyclerView.Adapter<ShopTypeViewHolder>() {

    open lateinit var datas: List<DishBrandEntity>
    open var onItemClick: (Int,View, DishBrandEntity) -> Unit = {index, view, data ->
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopTypeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item_shoe, parent, false)
        return ShopTypeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopTypeViewHolder, position: Int) {
        holder.bindData(datas[position])
        holder.itemView.click { onItemClick(position,holder.itemView, datas[position]) }
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}


class ShopTypeViewHolder constructor(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bindData(data: DishBrandEntity?) {
        itemView.setBackgroundColor(Color.GRAY)
        data ?: return
        view.findViewById<TextView>(R.id.name).text = data.name
        view.tag = data
    }

}