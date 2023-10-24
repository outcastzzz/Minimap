package com.example.myapplication.presentation.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemRoomBinding
import com.example.myapplication.domain.entities.Room

class RoomViewHolder(
    itemRoomBinding: ItemRoomBinding
): RecyclerView.ViewHolder(itemRoomBinding.root) {
    private val binding = itemRoomBinding

    fun bind(roomItem: Room) {
        binding.tvName.text = "1"
        binding.tvTitle.text = "title"
    }


}