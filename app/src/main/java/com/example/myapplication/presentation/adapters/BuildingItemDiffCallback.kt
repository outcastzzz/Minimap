package com.example.myapplication.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.domain.entities.Building


class BuildingItemDiffCallback: DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}