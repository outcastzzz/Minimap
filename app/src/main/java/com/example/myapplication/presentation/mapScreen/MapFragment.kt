package com.example.myapplication.presentation.mapScreen

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myapplication.data.network.ApiFactory
import com.example.myapplication.databinding.FragmentMapBinding
import com.example.myapplication.presentation.MinimapApp
import com.example.myapplication.presentation.ViewModelFactory
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

class MapFragment: Fragment() {

    private lateinit var viewModel: MapViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as MinimapApp).component
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[MapViewModel::class.java]
        binding.tvRoom.setOnClickListener {
            FragmentRooms().show(requireActivity().supportFragmentManager, "tag")
        }
        binding.buttonMe.setOnClickListener {
            setupMap()
        }
    }

    private fun setupMap() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiFactory.buildingApi.getMapImage()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val gson = Gson()
                    val json = response.body()
                    val jsonString = gson.toJson(json)
                    val jsonObject = JSONObject(jsonString)
                    val svgUrl = jsonObject.getString("build.svg")
                    Glide.with(requireActivity().applicationContext)
                        .load(svgUrl)
                        .into(binding.ivMap)

                }
            }
        }
    }

    private fun parseImage(map: String): Drawable? {
        val drawableId = resources.getIdentifier(
            map,
            "drawable",
            requireActivity().packageName
        )
        val drawable = ContextCompat.getDrawable(
            requireActivity().applicationContext,
            drawableId
        )
        return drawable
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

