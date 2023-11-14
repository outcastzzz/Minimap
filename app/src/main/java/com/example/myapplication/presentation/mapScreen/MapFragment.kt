package com.example.myapplication.presentation.mapScreen

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.network.ApiFactory
import com.example.myapplication.databinding.FragmentMapBinding
import com.example.myapplication.presentation.MinimapApp
import com.example.myapplication.presentation.adapters.RoomItemAdapter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
        binding.button2.setOnClickListener {
            showAndHideRecyclerView(binding.rvItemList)
            Toast.makeText(
                requireActivity().applicationContext,
                "clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun slideUp(view: View) {
        view.visibility = View.VISIBLE
        val animate = ObjectAnimator.ofFloat(
            view,
            "translationY",
            view.height.toFloat(),
            0f
        )
        animate.duration = 500
        animate.start()
    }

    private fun slideDown(view: View) {
        val animate = ObjectAnimator.ofFloat(
            view,
            "translationY",
            0f,
            view.height.toFloat()
        )
        animate.duration = 500
        animate.start()
    }

    private fun showAndHideRecyclerView(view: View) {
        var isUp = false
        if (isUp) {
            slideDown(view)
        } else {
            slideUp(view)
        }
        isUp = !isUp
    }

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
                                    Toast.makeText(
                                        requireActivity().applicationContext,
                                        "clicked",
                                        Toast.LENGTH_SHORT
                                    ).show()
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

