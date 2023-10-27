package com.example.myapplication.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentRoomsBinding
import com.example.myapplication.domain.entities.Room
import com.example.myapplication.presentation.adapters.RoomItemAdapter


class FragmentRooms: Fragment() {



    private lateinit var roomItemAdapter: RoomItemAdapter
    private val roomList = mutableListOf<Room>()

    private var _binding: FragmentRoomsBinding? = null
    private val binding: FragmentRoomsBinding
        get() = _binding ?: throw RuntimeException("FragmentRoomsBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateList()
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        roomItemAdapter = RoomItemAdapter(roomList)
        binding.rvItemList.adapter = roomItemAdapter
        binding.rvItemList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun populateList() {
        for (i in 1..10) {
            val name = "PPK SSTU"
            val title = "The Gagarin`s college"
            val room = Room(name = name, title = title)
            roomList.add(room)
        }
    }

}