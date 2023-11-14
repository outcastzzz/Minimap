package com.example.myapplication.presentation.mapScreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.caverock.androidsvg.R
import com.caverock.androidsvg.R.attr.svg
import com.caverock.androidsvg.SVG
import com.example.myapplication.data.network.ApiFactory
import com.example.myapplication.databinding.FragmentMapBinding
import com.example.myapplication.presentation.MinimapApp
import com.example.myapplication.presentation.adapters.RoomItemAdapter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapFragment: Fragment() {

    private val component by lazy {
        (requireActivity().application as MinimapApp).component
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Handle exception: ${throwable.message}")
    }
    private val scope = CoroutineScope(Dispatchers.IO + coroutineExceptionHandler)

    private var _binding: FragmentMapBinding? = null
    private val binding: FragmentMapBinding
        get() = _binding ?: throw RuntimeException("FragmentMapBinding == null")


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        binding.button1.setOnClickListener {
            setupMap()
        }
    }

    private val svgString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><svg width=\"24px\" height=\"24px\" stroke-width=\"1.5\" viewBox=\"0 0 24 24\" fill=\"none\" xmlns=\"http://www.w3.org/2000/svg\" color=\"#000000\"><path d=\"M7 12.5L10 15.5L17 8.5\" stroke=\"#000000\" stroke-width=\"1.5\" stroke-linecap=\"round\" stroke-linejoin=\"round\"></path><path d=\"M12 22C17.5228 22 22 17.5228 22 12C22 6.47715 17.5228 2 12 2C6.47715 2 2 6.47715 2 12C2 17.5228 6.47715 22 12 22Z\" stroke=\"#000000\" stroke-width=\"1.5\" stroke-linecap=\"round\" stroke-linejoin=\"round\"></path></svg>"

    private fun setupMap() {
        CoroutineScope(Dispatchers.IO).launch {
            val svgData = ApiFactory.apiService.getMapImage()
            withContext(Dispatchers.Main) {
                val svgString = svgData.body()?.imgUrl?.replace("\\", " ")
                Glide.with(requireActivity().applicationContext)
                    .load(svgString)
                    .into(binding.ivMap)
                binding.ivMap.invalidate()
                Log.i("MyTag", "SWITCHED")
            }
        }
    }

    private fun setupRecyclerView() {
        scope.launch {
            val response = ApiFactory.apiService.getAllRooms()
            withContext(Dispatchers.Main) {
                if(response.isSuccessful && response.body() != null) {
                    val rooms = response.body()?.room
                    val adapter = RoomItemAdapter(
                        rooms!!,
                        object: RoomItemAdapter.OnItemClickListener {
                        override fun onItemClick(room: String) {
                        }
                    })
                    binding.rvItemList.adapter = adapter
                    val searchView: SearchView = binding.searchItem
                    searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                               val filteredItems = rooms.filter {
                                   it.contains(newText ?: "", ignoreCase = true)
                               }
                            binding.rvItemList.adapter = RoomItemAdapter(
                                filteredItems,
                                object: RoomItemAdapter.OnItemClickListener {
                                override fun onItemClick(room: String) {

                                }
                            })
                            return false
                        }
                    })
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

