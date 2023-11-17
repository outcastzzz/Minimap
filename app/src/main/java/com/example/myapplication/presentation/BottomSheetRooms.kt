package com.example.myapplication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.network.ApiFactory
import com.example.myapplication.presentation.adapters.RoomItemAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BottomSheetRooms : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_rooms, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_item_list)
        val searchView = view.findViewById<SearchView>(R.id.search_item)
        CoroutineScope(Dispatchers.IO).launch {
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
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapter
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
        return view
    }
}