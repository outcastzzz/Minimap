package com.example.myapplication.presentation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.caverock.androidsvg.SVG
import com.example.myapplication.databinding.FragmentMapBinding
import com.example.myapplication.presentation.adapters.RoomItemAdapter
import javax.inject.Inject

class MapFragment: Fragment() {

    private val component by lazy {
        (requireActivity().application as MinimapApp).component
    }

    private var _binding: FragmentMapBinding? = null
    private val binding: FragmentMapBinding
        get() = _binding ?: throw RuntimeException("FragmentMapBinding == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private var roomStart = ""
    private var roomEnd = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(layoutInflater, container, false)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]
        viewModel.building.observe(viewLifecycleOwner) {
            val mapsWithoutRoute = convertStringToSvg(it.floors)

        }
        binding.btnChooseFrom.setOnClickListener {
            showChooseLayout(binding.linLayout)
            chooseStartRoom()
        }
        binding.btnChooseTo.setOnClickListener {
            showChooseLayout(binding.linLayout)
            chooseEndRoom()
        }
        checkVisibility(binding.root)
    }

    private fun setupBuildingByFind() {
        viewModel.route.observe(viewLifecycleOwner) {
            val maps = convertStringToSvg(it.maps)
            val adapter = ArrayAdapter(
                requireActivity().applicationContext,
                android.R.layout.simple_spinner_item,
                it.floors
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            val spinner = binding.spinner
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = it.floors[position]
                    val imageView = binding.ivMap
                    when (selectedItem) {
                        it.floors[0] -> imageView.setImageDrawable(maps[0])
                        it.floors[1] -> imageView.setImageDrawable(maps[1])
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Log.d("SpinnerTag", "$this item hasn`t chosen yet")
                }
            }
        }
    }

    private fun setupBuildingByScan() {
        viewModel.route.observe(viewLifecycleOwner) {
            val maps = convertStringToSvg(it.maps)
            val adapter = ArrayAdapter(
                requireActivity().applicationContext,
                android.R.layout.simple_spinner_item,
                it.floors
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            val spinner = binding.spinner
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = it.floors[position]
                    val imageView = binding.ivMap
                    when (selectedItem) {
                        it.floors[0] -> imageView.setImageDrawable(maps[0])
                        it.floors[1] -> imageView.setImageDrawable(maps[1])
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Log.d("SpinnerTag", "$this item hasn`t chosen yet")
                }
            }
        }
    }

    private fun showChooseLayout(view: View) {
        view.animate()
            .translationY(1 - view.height.toFloat())
            .setDuration(400)
            .start()
    }

    private fun hideChooseLayout(view: View) {
        view.animate()
            .translationY(view.height.toFloat())
            .setDuration(400)
            .start()
    }

    private fun chooseStartRoom(): String {
        viewModel.rooms.observe(viewLifecycleOwner) {
            val adapter = RoomItemAdapter(it) { name ->
                roomStart = name
                binding.tvFrom.text = name
                hideChooseLayout(binding.linLayout)
            }
            binding.rvRoomList.layoutManager = LinearLayoutManager(requireActivity().applicationContext)
            binding.rvRoomList.adapter = adapter
            adapter.submitList(it)
        }
        return roomStart
    }



    private fun chooseEndRoom(): String {
        viewModel.rooms.observe(viewLifecycleOwner) {
            val adapter = RoomItemAdapter(it) { name ->
                roomEnd = name
                binding.tvTo.text = name
                hideChooseLayout(binding.linLayout)
                viewModel.getRoute("college", roomStart, roomEnd)
                setupBuildingByFind()
            }
            binding.rvRoomList.layoutManager = LinearLayoutManager(requireActivity().applicationContext)
            binding.rvRoomList.adapter = adapter
            adapter.submitList(it)
        }
        return roomEnd
    }

    private fun checkVisibility(view: View) {
        view.setOnClickListener {
            if(binding.linLayout.visibility == View.VISIBLE) {
                hideChooseLayout(binding.linLayout)
            }
        }
    }


    private fun convertStringToSvg(maps: List<String>): List<Drawable> {
        val svgList = mutableListOf<Drawable>()
        for (i in maps) {
            val svg = SVG.getFromString(i)
            val picture = svg.renderToPicture()
            val drawable = PictureDrawable(picture)
            svgList.add(drawable)
        }
        return svgList.toList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

