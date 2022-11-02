package com.example.planto_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.planto_app.data.entity.Plant
import com.example.planto_app.databinding.PlantItemLayoutBinding

class PlantAdapter : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    inner class PlantViewHolder( val binding: PlantItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    // differ call back to calculate different between 2 lists and output it,
    // and converts the old list to the new list, and it can be used with recyclerview adapter.
    private val differCallBack = object : DiffUtil.ItemCallback<Plant>() {
        override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val binding = PlantItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return PlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plantItem = differ.currentList[position]
        holder.binding.apply {
            plantImg.setImageBitmap(plantItem.plantImage)
            tvPlantType.text = plantItem.plantType
            tvPlantName.text = plantItem.plantName
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}