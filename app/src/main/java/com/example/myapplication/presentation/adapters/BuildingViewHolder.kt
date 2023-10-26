package com.example.myapplication.presentation.adapters

import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemRoomBinding
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Room

class BuildingViewHolder(
    itemBuildingBinding: ItemRoomBinding
): RecyclerView.ViewHolder(itemBuildingBinding.root) {
    private val binding = itemBuildingBinding

    fun bind(buildItem: Building) {
        binding.tvName.text = "1"
        binding.tvTitle.text = "title"
    }


}