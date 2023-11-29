package com.example.myapplication.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMapBinding
import com.example.myapplication.presentation.BottomSheetRooms
import com.example.myapplication.presentation.MinimapApp

class MapFragment: Fragment() {

    private val component by lazy {
        (requireActivity().application as MinimapApp).component
    }

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
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinner()
        binding.button2.setOnClickListener {
            setupRecyclerView()
        }
    }

    private fun setupRecyclerView() {
        val bottomRecyclerView = BottomSheetRooms()
        bottomRecyclerView.show(requireActivity().supportFragmentManager, bottomRecyclerView.tag)
    }

    private fun setupSpinner() {
        val spinView: Spinner = binding.spinner

        val adapter = ArrayAdapter.createFromResource(
            requireActivity().applicationContext,
            R.array.spinner_items,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinView.adapter = adapter

        spinView.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent != null) {
                    when(parent.getItemAtPosition(position).toString()) {
                        "Item 1" -> binding.ivMap.setImageResource(R.drawable.house_rooms)
                        "Item 2" -> binding.ivMap.setImageResource(R.drawable.smart_home)
                        "Item 3" -> binding.ivMap.setImageResource(R.drawable.nazi_symbol_3_svgrepo_com)
                        "Item 4" -> binding.ivMap.setImageResource(R.drawable.bottom_sheet_background)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

