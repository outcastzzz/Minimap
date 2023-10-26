package com.example.myapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.databinding.ItemRoomBinding
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Room


class BuildingItemAdapter(
    private val buildList: MutableList<Building>
): ListAdapter<Building, BuildingViewHolder>(BuildingItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingViewHolder {
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuildingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuildingViewHolder, position: Int) {
        val buildItem = buildList[position]
        holder.bind(buildItem)
    }

    override fun getItemCount(): Int {
        return buildList.size
    }
}