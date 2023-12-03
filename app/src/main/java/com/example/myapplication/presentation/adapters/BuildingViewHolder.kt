package com.example.myapplication.presentation.adapters


import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBuildBinding
import com.example.myapplication.presentation.ListOfBuildingsFragmentDirections

class BuildingViewHolder(
    view: View
): RecyclerView.ViewHolder(view), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

    }

    private val binding = ItemBuildBinding.bind(view)

    val buildTv = binding.tvName

}