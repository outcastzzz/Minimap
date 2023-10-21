package com.example.myapplication.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentListOfRoomsBinding
import com.example.myapplication.presentation.adapters.RoomItemAdapter

class ListOfRooms(
    private val context: Context
) : Fragment() {

    private lateinit var roomItemAdapter: RoomItemAdapter


    private var _binding: FragmentListOfRoomsBinding? = null
    private val binding: FragmentListOfRoomsBinding
        get() = _binding ?: throw RuntimeException("FragmentListOfRooms == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfRoomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        with(binding.rvShopList) {
            roomItemAdapter = RoomItemAdapter(context)
            adapter = roomItemAdapter
        }
    }
}