package com.example.myapplication.presentation.mapScreen

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
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
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.IllegalStateException
import javax.inject.Inject

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
    private val scope = CoroutineScope(Dispatchers.Main + coroutineExceptionHandler)

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
        scope.launch {
            val svgData = ApiFactory.apiService.getMapImage()
            val svgString = svgData.imgUrl
            Glide.with(this@MapFragment)
                .load(svgString)
                .into(binding.ivMap)
            throw IllegalStateException("Child coroutine failed")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

