package com.example.myapplication.presentation.adapters

import androidx.recyclerview.widget.DiffUtil

object RoomItemDiffCallback: DiffUtil.ItemCallback<Room>() {

    override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem == newItem
    }
}