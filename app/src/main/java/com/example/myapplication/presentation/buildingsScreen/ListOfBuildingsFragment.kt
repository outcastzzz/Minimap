package com.example.myapplication.presentation.buildingsScreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.network.ApiFactory
import com.example.myapplication.databinding.FragmentListOfRoomsBinding
import com.example.myapplication.presentation.ViewModelFactory
import com.example.myapplication.presentation.adapters.BuildingItemAdapter
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalStateException
import javax.inject.Inject

class ListOfBuildingsFragment: Fragment() {

    private var _binding: FragmentListOfRoomsBinding? = null
    private val binding: FragmentListOfRoomsBinding
        get() = _binding ?: throw RuntimeException("FragmentListOfBuildings == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Handle exception: ${throwable.message}")
    }
    private val scope = CoroutineScope(Dispatchers.IO + coroutineExceptionHandler)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfRoomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        binding.button.setOnClickListener {
            launchWelcomeFragment()
        }
    }

    private fun setupRecyclerView() {
        scope.launch {
            val response = ApiFactory.apiService.getAllBuildings()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    val buildings = response.body()?.build
                    val adapter = BuildingItemAdapter(
                        buildings!!,
                        object: BuildingItemAdapter.OnItemClickListener {
                        override fun onItemClick(building: String) {
                            launchMapFragment()
                        }
                    })
                    binding.rvItemList.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvItemList.adapter = adapter
                    val searchView = binding.searchView
                    searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            val filteredItems = buildings.filter {
                                it.contains(newText ?: "", ignoreCase = true)
                            }
                            binding.rvItemList.adapter = BuildingItemAdapter(
                                filteredItems,
                                object: BuildingItemAdapter.OnItemClickListener {
                                    override fun onItemClick(building: String) {

                                    }
                                }
                            )
                            return false
                        }
                    })
                }
            }
            throw IllegalStateException("Child coroutine failed")
        }
    }

    private fun launchWelcomeFragment() {
        findNavController().navigate(R.id.action_listOfRooms_to_welcomeFragment)
    }

    private fun launchMapFragment() {
        findNavController().navigate(R.id.action_listOfRooms_to_mapFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}