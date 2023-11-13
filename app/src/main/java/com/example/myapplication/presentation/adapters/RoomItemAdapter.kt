package com.example.myapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemRoomBinding
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Room

class RoomItemAdapter(
    private val roomList: List<String>,
    private val listener: OnItemClickListener
): androidx.recyclerview.widget.ListAdapter<Room, RoomItemViewHolder>(RoomItemDiffCallback) {

    interface OnItemClickListener {
        fun onItemClick(room: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_room, parent, false)
        return RoomItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomItemViewHolder, position: Int) {
        val room = roomList[position]
        holder.roomTv.text = room
        holder.itemView.setOnClickListener {
            listener.onItemClick(room)
        }
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

}