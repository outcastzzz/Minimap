package com.example.myapplication.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemRoomBinding
import com.example.myapplication.domain.entities.Room


class RoomItemAdapter(
    private val context: Context
): ListAdapter<Room, RoomViewHolder>(RoomItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = ItemRoomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = getItem(position)
        with(holder.binding) {
            with(room) {
                val roomNumber = context.resources.getString(R.string.room_number)
                val roomTitle = context.resources.getString(R.string.room_title)
                tvName.text = roomNumber
                tvTitle.text = roomTitle
            }
        }
    }
}