package com.example.origy.itemDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.origy.R


class ItemDetailAdapter (
    private val onClick:(ItemDetailEntity) -> Unit
) : RecyclerView.Adapter<ItemDetailAdapter.ViewHolder>(){

    private val list = mutableListOf<ItemDetailEntity>()

    fun setData(newList: List<ItemDetailEntity>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = list[position]

        holder.name.text = item.name
        holder.img.setImageResource(item.image)
        holder.imgNew.visibility =
            if (item.isNew) View.VISIBLE else View.GONE

        holder.imgFavorite.visibility =
            if (item.isFavorite) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val img: ImageView = view.findViewById(R.id.img)
        val name: TextView = view.findViewById(R.id.tvName)
        val imgNew: ImageView = view.findViewById(R.id.imgNew)
        val imgFavorite: ImageView = view.findViewById(R.id.imgfavorite)
    }
}