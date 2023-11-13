package com.example.myapplication.presentation.mapScreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.network.ApiFactory
import com.example.myapplication.databinding.FragmentMapBinding
import com.example.myapplication.presentation.MinimapApp
import com.example.myapplication.presentation.ViewModelFactory
import com.example.myapplication.presentation.adapters.RoomItemAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.IllegalStateException

class MapFragment: Fragment() {

    private lateinit var viewModel: MapViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as MinimapApp).component
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Handle exception: ${throwable.message}")
    }
    private val scope = CoroutineScope(Dispatchers.IO + coroutineExceptionHandler)

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private var _binding: FragmentMapBinding? = null
    private val binding: FragmentMapBinding
        get() = _binding ?: throw RuntimeException("FragmentMapBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun setupMap() {
        scope.launch {
            val svgData = ApiFactory.apiService.getMapImage()
            val svgString = svgData.imgUrl
            Glide.with(this@MapFragment)
                .load(svgString)
                .into(binding.ivMap)
        }
    }

   // <!-- ------------------------------------------------------------------------------------ -->

    val recyclerView: RecyclerView = binding.rvItemList

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
        viewModel = ViewModelProvider(this, viewModelFactory)[MapViewModel::class.java]
        binding.tvRoom.setOnClickListener {
            FragmentRooms().show(requireActivity().supportFragmentManager, "tag")
        }
        binding.buttonMe.setOnClickListener {
            setupMap()
        }

        val bottomSheet = binding.bottomSheet
        val behavior = BottomSheetBehavior.from(bottomSheet)

        behavior.state = BottomSheetBehavior.STATE_COLLAPSED

        behavior.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                with(binding) {
                    if (slideOffset > 0) {
                        searchItem.alpha = slideOffset * slideOffset
                    }
                    if (slideOffset > 0.5) {
                        searchItem.visibility = View.VISIBLE
                    }
                    if(slideOffset < 0.5 && binding.searchItem.visibility == View.VISIBLE) {
                        searchItem.visibility = View.INVISIBLE
                    }
                }
            }
        })
    }

    @SuppressLint("InflateParams")
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
                    recyclerView.adapter = adapter
                    val searchViewX = binding.searchView
                    val searchViewLayout = LayoutInflater
                        .from(requireContext().applicationContext)
                        .inflate(R.layout.search_view, null)
                    searchViewX
                    val searchView: SearchView = binding.searchItem
                    searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                               val filteredItems = rooms.filter {
                                   it.contains(newText ?: "", ignoreCase = true)
                               }
                            recyclerView.adapter = RoomItemAdapter(
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

