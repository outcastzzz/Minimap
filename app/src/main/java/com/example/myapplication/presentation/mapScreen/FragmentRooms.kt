package com.example.myapplication.presentation.mapScreen

import android.os.Bundle
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
            val name = "name"
            val title = "title"
            val room = Room(name = name, id = "")
            roomList.add(room)
        }
    }

//    val a: String = "<?xmlversion=\"1.0\"encoding=\"UTF-8\"standalone=\"no\"?><!DOCTYPEsvgPUBLIC" +
//            "\"-//W3C//DTDSVG1.1//EN\"\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">" +
//            "<svgversion=\"1.1\"xmlns=\"http://www.w3.org/2000/svg\"xmlns:xlink=\"http://www." +
//            "w3.org/1999/xlink\"width=\"256px\"height=\"256px\"viewBox=\"00256256\"><imagexlin" +
//            "k:href=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAIAAADTED8xAAAAAX" +
//            "NSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAUjSURBVHhe7d1Bctp" +
//            "IAEBRey6Qw8wFskpVTjzbuUA2uRDDVCiXC2whZEAt/fc2wVm1u/u7JYGT18Ph8AJVf53+hCQBkCYA0gR" +
//            "AmgBIEwBpAiBNAKQJgDQBkCYA0gRAmgBIEwBpAiBNAKQJgDQBkCYA0gRAmgBIEwBpAiBNAKQJgDQBkCY" +
//            "A0gRAmgBIEwBpAiBNAKQJgDQBkCYA0gRAmgBIEwBpAiBNAKQJgDQBkCYA0gRAmgBIEwBpAiBNAKQJgDQ" +
//            "BkCYA0gRAmgBIEwBpAiBNAKQJgDQBkCYA0gRAmgBIEwBpAiBNAKQJgDQBkCYA0gRAmgBIEwBpAiBNAKS9H" +
//            "g6H08uVvL6+nl69vKw+GGpWPgHe7/6jsy/h0VY+AaZ3vAOBRxs6gJl0wmJ7CGCaPJiw/wAuSYI3awYww" +
//            "i2vGOIGCuBtJGuF8YipGCHyuOllHTGAm9hhXDWxtVYL4HLj3n0k2uDNZ7trlACeMwxJZH22wVqfBTrOwpv" +
//            "TX9HWOgE+9Ohj4Y7f2ttQBXzVzA0mgIFGMm0r4xzEzOla5xLobHBs13Ep/zh9vTV+H4Dl3u/7jTYwRABO" +
//            "c+5rfo1OANKGCGC7V5BswsQlhhOAtHUCcNHPXXz7/eP06p2bLii8DzDQSKYNOM4xp+6mUbkEIk0ApAlgq" +
//            "84Oev64dVoEQJoANmPYu/ORXZ00AZAmANIEwEL7uAsXwDlPV7ZrwdoJgN2a89hAAJ6u3MdGp1EApAmAJ" +
//            "ZzpySAD7gP7hAAN7v8AbHd+ygBkLZOAKNdY3gQ9BWbnr0hTgD7j7W4BCJNAKQJYDM8nH0EAZAmgG0Y59" +
//            "H7zg4iAfzP1UXWOv8w1tmGW/cx6OC7/zg5H45wkBNg3bU7s2Bs0RPgOFNvTn81qs9GuMrIx5+uW+VOgP0" +
//            "t4VV3nN7L2dv6CbD/AII7ftpXZvuZC7eAAL663Y8jEcxny3E5M6tsngmbD2DFzTfaWh4NnuL4M7aZANYy4B" +
//            "LONMIECmC5dddvu/t+gcdNtQCWe3IAqR0/4Y7TPuaUCuDEjv+Kq6sz7PTuJADbl2W2GoAdz10s2Fc+DEeaA" +
//            "EgTwAN9//nr9IpRuQdgP1r3AH6+8nUbDuDff/4+vWIRP0GOXAKxHx6Dwm0EQJoASBMAaQIgTQCkCYA0AbBb" +
//            "Z28LfEgApAmA/VjwkQIBkCYA0gRAmgDYs6sPggRAmgDYlVsfBAmANAGQJgDSBECaANi56SehAiBNAOzNT" +
//            "U9CBUCaAEgTAGkCIE0ApAmANAGQJgDSBECaAEgTAGkCIE0ApAmAtBUC8F9EMg4nAGkCIE0ApAmANAGQJgDS" +
//            "BECaAEgTAGkCIE0ApAmANAGQJgDSBEDaswPwywAMxQlAmgBIEwBpAiBNAKQJgDQBsH8T/1WwAEgTADs0/w1" +
//            "WAZAmANIEQJoASBMAI/r+89fp1YO9PvkDyT4OzXPM3GlOANIEQJoASBMAaQIgTQCkCYC0IQL49vvH6RU8lz" +
//            "fC2CdvhNE18StgZwTA3szf/UcCYP8mrrQFQNrKN8Ez7eBeedk3vkXrLtblPE+PZxsBPM5N335nEz/ZHTehA" +
//            "Nih+bt09ACONMDTXN3eK9wEH8f0/OrgQyucADfZzXHRaX6oJbs67aMH8Gg3rZaD60Ee1Myc9aoHwOAWtzFz" +
//            "YwuANO8EkyYA0gRAmgBIEwBpAiBNAKQJgDQBkCYA0gRAmgBIEwBpAiBNAKQJgDQBkCYA0gRAmgBIEwBpAiB" +
//            "NAKQJgDQBkCYA0gRA2MvLfzY7z/NCuyd/AAAAAElFTkSuQmCC\"width=\"256\"height=\"256\"x=\"0\"y=\"0\"/></svg>"
//
//    val id = resources.getIdentifier(a, "drawable", requireActivity().packageName)
//    val drawable: Drawable? = ContextCompat.getDrawable(requireActivity().applicationContext, id)




    companion object {
        private const val COLLPASED_HEIGT = 228
    }

}