package com.example.myapplication.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemRoomBinding
import com.example.myapplication.domain.entities.Room

class RoomItemViewHolder(
    itemRoomBinding: ItemRoomBinding
): RecyclerView.ViewHolder(itemRoomBinding.root) {

    private val binding = itemRoomBinding

    fun bind(roomItem: Room) {
        binding.tvName.text = "PPK SSTU"
        binding.tvName.text = "The Gagarin`s college"
    }

}