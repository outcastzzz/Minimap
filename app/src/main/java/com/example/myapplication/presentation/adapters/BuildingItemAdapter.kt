package com.example.myapplication.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemBuildBinding
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Room


class BuildingItemAdapter(
    private val buildList: MutableList<Building>,
    private val context: Context
): ListAdapter<Building, BuildingViewHolder>(BuildingItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingViewHolder {
        val binding = ItemBuildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuildingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuildingViewHolder, position: Int) {
        val buildItem = buildList[position]
        holder.binding.apply {
            tvName.text = "${buildItem.name}"
        }
    }

    override fun getItemCount(): Int {
        return buildList.size
    }
}