package com.example.myapplication.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.network.ApiFactory
import com.example.myapplication.databinding.FragmentListOfRoomsBinding
import com.example.myapplication.presentation.adapters.BuildingItemAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListOfBuildingsFragment: Fragment() {

    private val component by lazy {
        (requireActivity().application as MinimapApp).component
    }

    private var _binding: FragmentListOfRoomsBinding? = null
    private val binding: FragmentListOfRoomsBinding
        get() = _binding ?: throw RuntimeException("FragmentListOfBuildings == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

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
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]
        viewModel.buildings.observe(viewLifecycleOwner) {
            val adapter = BuildingItemAdapter(it) { name ->
                viewModel.getBuildingByName(name)
                viewModel.getListOfRooms(name)
                launchMapFragment()
            }
            binding.rvItemList.layoutManager = LinearLayoutManager(requireActivity().applicationContext)
            binding.rvItemList.adapter = adapter
            adapter.submitList(it)
        }
        binding.button.setOnClickListener {
            launchWelcomeFragment()
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