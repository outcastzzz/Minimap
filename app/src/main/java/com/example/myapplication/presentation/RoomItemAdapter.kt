package com.example.myapplication.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.domain.entities.Room

class RoomItemAdapter: RecyclerView.Adapter<RoomViewHolder>() {

    var roomList = listOf<Room>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_room,
            parent,
            false
        )
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = roomList[position]
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    companion object {

        const val VIEW_TYPE = 100

        const val MAX_POOL_SIZE = 30

    }

}