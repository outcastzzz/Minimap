package com.example.myapplication.presentation.adapters


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBuildBinding

class BuildingViewHolder(
    view: View
): RecyclerView.ViewHolder(view) {
    private val binding = ItemBuildBinding.bind(view)

    val buildTv = binding.tvName
}