package com.example.myapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import com.example.myapplication.R

class RoomItemAdapter(
    private val roomList: List<String>,
    private val callback: (String) -> Unit
): androidx.recyclerview.widget.ListAdapter<String, RoomItemViewHolder>(RoomItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_room, parent, false)
        return RoomItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomItemViewHolder, position: Int) {
        val room = roomList[position]
        holder.roomTv.text = room
        holder.itemView.setOnClickListener {
            callback.invoke(holder.roomTv.text.toString())
        }
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

}