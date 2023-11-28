package com.example.myapplication.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemRoomBinding

class RoomItemViewHolder(
    view: View
): RecyclerView.ViewHolder(view) {

    private val binding = ItemRoomBinding.bind(view)

    val roomTv = binding.tvName

}