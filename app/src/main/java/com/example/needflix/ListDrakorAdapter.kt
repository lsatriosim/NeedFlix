package com.example.needflix

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.needflix.databinding.ItemRowDrakorBinding

class ListDrakorAdapter(private val listDrakor: ArrayList<Drakor>) : RecyclerView.Adapter<ListDrakorAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(var binding: ItemRowDrakorBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowDrakorBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listDrakor.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title,releaseYear,jumlahEpisode,genre,poster,castName,productionStudio,sinopsis, posterInt) = listDrakor[position]
        Glide.with(holder.itemView.context)
            .load(poster)
            .into(holder.binding.imgItemPoster)
        holder.binding.tvItemTitle.text = title
        holder.binding.tvItemGenre.text = genre
        holder.binding.tvItemSinopsis.text = sinopsis

        holder.itemView.setOnClickListener{ onItemClickCallback.onItemClicked(listDrakor[holder.adapterPosition])}

    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Drakor)
    }
}