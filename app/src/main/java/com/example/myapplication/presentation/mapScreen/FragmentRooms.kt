package com.example.myapplication.presentation.mapScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRoomsBinding
import com.example.myapplication.domain.entities.Room
import com.example.myapplication.presentation.adapters.RoomItemAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class  FragmentRooms: BottomSheetDialogFragment() {



    private lateinit var roomItemAdapter: RoomItemAdapter
    private val roomList = mutableListOf<Room>()

    private var _binding: FragmentRoomsBinding? = null
    private val binding: FragmentRoomsBinding
        get() = _binding ?: throw RuntimeException("FragmentRoomsBinding == null")


    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val COLLPASED_HEIGT = 228
    }

}