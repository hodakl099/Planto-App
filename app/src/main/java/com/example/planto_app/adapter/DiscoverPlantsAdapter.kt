package com.example.planto_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.planto_app.data.entity.DiscoveryItem
import com.example.planto_app.data.entity.Plant
import com.example.planto_app.databinding.PlantDiscoverItemBinding

class DiscoverPlantsAdapter(val discoveryItems : MutableList<DiscoveryItem>) : RecyclerView.Adapter<DiscoverPlantsAdapter.DiscoverPlantViewHolder>() {

    inner class DiscoverPlantViewHolder(val binding : PlantDiscoverItemBinding) : RecyclerView.ViewHolder(binding.root)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverPlantViewHolder {
       val binding = PlantDiscoverItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DiscoverPlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiscoverPlantViewHolder, position: Int) {
        val discoveryItem = discoveryItems[position]
        holder.binding.apply {
            ivImgDiscovery.setImageDrawable(discoveryItem.img)
            ivItemTitle.text = discoveryItem.itemTitle
            tvItemTextSpec.text = discoveryItem.itemSpec
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(discoveryItem)
            }
        }
    }

    override fun getItemCount(): Int {
       return discoveryItems.size
    }


    private var onItemClickListener : ( (DiscoveryItem) -> Unit)? = null

    fun setonItemClickListener(listener: (DiscoveryItem) -> Unit) {
        onItemClickListener = listener
    }

}