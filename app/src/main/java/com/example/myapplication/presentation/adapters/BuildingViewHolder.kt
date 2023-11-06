package com.example.myapplication.presentation.adapters

import android.app.LauncherActivity.ListItem
import android.os.Build
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBuildBinding
import com.example.myapplication.domain.entities.Building

class BuildingViewHolder(
    view: View
): RecyclerView.ViewHolder(view) {
    private val binding = ItemBuildBinding.bind(view)

    fun bind(building: Building) = with(binding) {
        tvName.text = building.name
    }
}