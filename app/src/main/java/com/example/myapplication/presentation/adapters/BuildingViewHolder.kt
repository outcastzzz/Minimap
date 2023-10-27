package com.example.myapplication.presentation.adapters

import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBuildBinding
import com.example.myapplication.domain.entities.Building

class BuildingViewHolder(
    itemBuildingBinding: ItemBuildBinding
): RecyclerView.ViewHolder(itemBuildingBinding.root) {
    private val binding = itemBuildingBinding

    fun bind(buildItem: Building) {
        binding.tvName.text = "1"
        binding.tvTitle.text = "title"
    }


}