package com.example.myapplication.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.domain.entities.Room


class RoomItemDiffCallback: DiffUtil.ItemCallback<Room>() {

    override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem == newItem
    }
}