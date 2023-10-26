package com.example.myapplication.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Room


object BuildingItemDiffCallback: DiffUtil.ItemCallback<Building>() {

    override fun areItemsTheSame(oldItem: Building, newItem: Building): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Building, newItem: Building): Boolean {
        return oldItem == newItem
    }
}