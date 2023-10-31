package com.example.myapplication.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRoomsBinding
import com.example.myapplication.domain.entities.Room
import com.example.myapplication.presentation.adapters.RoomItemAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class  FragmentRooms: BottomSheetDialogFragment() {



    private lateinit var roomItemAdapter: RoomItemAdapter
    private val roomList = mutableListOf<Room>()

    private var _binding: FragmentRoomsBinding? = null
    private val binding: FragmentRoomsBinding
        get() = _binding ?: throw RuntimeException("FragmentRoomsBinding == null")


    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
////        populateList()
////        setupRecyclerView()
//    }

    override fun onStart() {
        super.onStart()
        populateList()
        setupRecyclerView()

        val density = requireContext().resources.displayMetrics.density

        dialog?.let {

            val bottomSheet = it.findViewById<View>(
                com.google.android.material.R.id.design_bottom_sheet
            ) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)

            behavior.peekHeight = (COLLPASED_HEIGT * density).toInt()
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED

            behavior.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback() {

                override fun onStateChanged(bottomSheet: View, newState: Int) {

                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    with(binding) {
                        if(slideOffset > 0) {
                            layoutExpanded.alpha = slideOffset * slideOffset

                            if(slideOffset > 0.5) {
                                layoutExpanded.visibility = View.VISIBLE
                            }

                            if(slideOffset < 0.5 && binding.layoutExpanded.visibility == View.VISIBLE) {
                                layoutExpanded.visibility = View.INVISIBLE
                            }
                        }
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        roomItemAdapter = RoomItemAdapter(roomList)
        binding.rvItemList.adapter = roomItemAdapter
        binding.rvItemList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun populateList() {
        for (i in 1..10) {
            val name = "PPK SSTU"
            val title = "The Gagarin`s college"
            val room = Room(name = name, title = title, id = "")
            roomList.add(room)
        }
    }

    companion object {
        private const val COLLPASED_HEIGT = 228
    }

}