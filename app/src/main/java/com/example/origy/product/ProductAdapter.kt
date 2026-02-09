package com.example.origy.product

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.origy.R


class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private val list = mutableListOf<ProductEntity>()

    fun setData(data:List<ProductEntity>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val img = LayoutInflater.from(parent.context)
            .inflate(R.layout.step_item,parent,false) as ImageView
        return ViewHolder(img)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.img.setImageResource(list[position].image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(val img: ImageView): RecyclerView.ViewHolder(img)
}