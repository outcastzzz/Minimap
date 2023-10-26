package com.example.myapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemRoomBinding
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Room

class RoomItemAdapter(
    private val roomList: MutableList<Room>
): androidx.recyclerview.widget.ListAdapter<Room, RoomItemViewHolder>(RoomItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomItemViewHolder {
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomItemViewHolder, position: Int) {
        val roomItem = roomList[position]
        holder.bind(roomItem)
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

}