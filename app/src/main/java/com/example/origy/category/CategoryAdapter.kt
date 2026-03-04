package com.example.origy.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.origy.R


class CategoryAdapter(
    private val onClick: (CategoryEntity) -> Unit
): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private val list = mutableListOf<CategoryEntity>()
    fun setData(newList: List<CategoryEntity>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = list[position]
        holder.img.setImageResource(item.image)
        holder.title.text = item.name

        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val img = view.findViewById<ImageView>(R.id.imgCover)
        val title = view.findViewById<TextView>(R.id.tvTitle)
    }
}