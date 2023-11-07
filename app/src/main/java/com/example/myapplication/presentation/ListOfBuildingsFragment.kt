package com.example.myapplication.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.network.ApiFactory
import com.example.myapplication.databinding.FragmentListOfRoomsBinding
import com.example.myapplication.presentation.adapters.BuildingItemAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListOfBuildingsFragment: Fragment() {

    private lateinit var buildingItemAdapter: BuildingItemAdapter

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
        CoroutineScope(Dispatchers.IO).launch {
            val response= ApiFactory.buildingApi.getAllBuildings()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val buildings = response.body()
                    val adapter = BuildingItemAdapter(buildings!!)
                    binding.rvItemList.adapter = adapter
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchWelcomeFragment() {
        findNavController().navigate(R.id.action_listOfRooms_to_welcomeFragment)
    }

}