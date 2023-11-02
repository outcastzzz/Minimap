package com.example.myapplication.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.network.ApiFactory
import com.example.myapplication.databinding.FragmentListOfRoomsBinding
import com.example.myapplication.domain.entities.Building
import com.example.myapplication.presentation.adapters.BuildingItemAdapter
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException

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

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            launchWelcomeFragment()
        }
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                ApiFactory.apiService.getBuildingInfo()
            } catch (e: IOException) {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "app error ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
                return@launch
            } catch (e: HttpException) {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "http error ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
                return@launch
            }
            withContext(Dispatchers.Main) {
                binding.rvItemList.apply {
                    buildingItemAdapter = BuildingItemAdapter(buildList, requireActivity().applicationContext)
                    adapter = buildingItemAdapter
                    layoutManager = LinearLayoutManager(requireActivity().applicationContext)
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