package com.example.myapplication.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.databinding.ItemRoomBinding
import com.example.myapplication.domain.entities.Room


class RoomItemAdapter(
    private val roomList: MutableList<Room>
): ListAdapter<Room, RoomViewHolder>(RoomItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val roomItem = roomList[position]
        holder.bind(roomItem)
    }

    override fun getItemCount(): Int {
        return roomList.size
    }
}