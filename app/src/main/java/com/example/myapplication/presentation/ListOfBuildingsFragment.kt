package com.example.myapplication.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentListOfRoomsBinding
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.domain.entities.Room
import com.example.myapplication.presentation.adapters.BuildingItemAdapter

class ListOfBuildingsFragment: Fragment() {

    private lateinit var buildingItemAdapter: BuildingItemAdapter
    private val buildList = mutableListOf<Building>()

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
        populateList()
        setupRecyclerView()
        binding.button.setOnClickListener {
            launchWelcomeFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        buildingItemAdapter = BuildingItemAdapter(buildList)
        binding.rvItemList.adapter = buildingItemAdapter
        binding.rvItemList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun populateList() {
        for (i in 1..20) {
            val name = "1"
            val title = "abv"
            val build = Building(name = name, title = title)

            buildList.add(build)
        }
    }

    private fun launchWelcomeFragment() {
        findNavController().navigate(R.id.action_listOfRooms_to_welcomeFragment)
    }

}