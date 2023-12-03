package com.example.myapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.R
import com.example.myapplication.domain.entities.Building


class BuildingItemAdapter(
    private val buildings: List<String>,
    private val callback: (String) -> Unit
): ListAdapter<String, BuildingViewHolder>(BuildingItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_build, parent, false)
        return BuildingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BuildingViewHolder, position: Int) {
        val building = buildings[position]
        holder.buildTv.text = building
        holder.itemView.setOnClickListener {
            callback.invoke(holder.buildTv.text.toString())
        }
    }

    override fun getItemCount(): Int {
        return buildings.size
    }
}