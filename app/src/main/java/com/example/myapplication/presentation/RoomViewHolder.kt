package com.example.myapplication.presentation

import android.view.View
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class RoomViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById<TextView>(R.id.tv_name)
    val tvTitle = view.findViewById<TextView>(R.id.tv_title)
}