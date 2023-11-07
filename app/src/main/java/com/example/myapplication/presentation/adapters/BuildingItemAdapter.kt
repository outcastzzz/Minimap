package com.example.myapplication.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemBuildBinding
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Room


class BuildingItemAdapter(private val buildings: List<Building>): ListAdapter<Building, BuildingViewHolder>(BuildingItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_build, parent, false)
        return BuildingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BuildingViewHolder, position: Int) {
        holder.bind(
            buildings[position]
        )
    }

    override fun getItemCount(): Int {
        return buildings.size
    }
}