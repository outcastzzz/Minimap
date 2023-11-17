package com.example.myapplication.presentation.mapScreen

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.bumptech.glide.Glide
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import com.example.myapplication.R
import com.example.myapplication.data.network.ApiFactory
import com.example.myapplication.databinding.FragmentMapBinding
import com.example.myapplication.presentation.BottomSheetRooms
import com.example.myapplication.presentation.MinimapApp
import com.example.myapplication.presentation.adapters.RoomItemAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.ceil

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
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpinner()
        binding.button1.setOnClickListener {
            setupMap()
        }
        binding.button2.setOnClickListener {
            setupRecyclerView()
        }
    }

    private fun setupRecyclerView() {
        val bottomRecyclerView = BottomSheetRooms()
        bottomRecyclerView.show(requireActivity().supportFragmentManager, bottomRecyclerView.tag)
    }

    private fun setupMap() {
        CoroutineScope(Dispatchers.IO).launch {
            val svgData = ApiFactory.apiService.getMapImage()
            withContext(Dispatchers.Main) {
                val svgString = svgData.body()?.imgUrl?.replace("\\", " ")
                val svgString1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><svg wid" +
                        "th=\"54px\" height=\"54px\" viewBox=\"0 0 24 24\" stroke-wi" +
                        "dth=\"1.5\" fill=\"none\" xmlns=\"http://www.w3.org/2000/svg\" c" +
                        "olor=\"#000000\"><path d=\"M12 17C12.5523 17 13 16.5523 13 16C" +
                        "13 15.4477 12.5523 15 12 15C11.4477 15 11 15.4477 11 16C11 16.55" +
                        "23 11.4477 17 12 17Z\" fill=\"#000000\" stroke=\"#000000\" stro" +
                        "ke-width=\"1.5\" stroke-linecap=\"round\" stroke-linejoin=\"ro" +
                        "und\"></path><path d=\"M21 7.35304L21 16.647C21 16.8649 20.8819 1" +
                        "7.0656 20.6914 17.1715L12.2914 21.8381C12.1102 21.9388 11.8898 21" +
                        ".9388 11.7086 21.8381L3.30861 17.1715C3.11814 17.0656 3 16.8649 3" +
                        " 16.647L2.99998 7.35304C2.99998 7.13514 3.11812 6.93437 3.3086 " +
                        "6.82855L11.7086 2.16188C11.8898 2.06121 12.1102 2.06121 12.2914 2" +
                        ".16188L20.6914 6.82855C20.8818 6.93437 21 7.13514 21 7.35304Z\" s" +
                        "troke=\"#000000\" stroke-width=\"1.5\" stroke-linecap=\"round\" " +
                        "stroke-linejoin=\"round\"></path><path d=\"M20.5 16.7222L12.2914 " +
                        "12.1618C12.1102 12.0612 11.8898 12.0612 11.7086 12.1618L3.5 16.72" +
                        "22\" stroke=\"#000000\" stroke-width=\"1.5\" stroke-linecap=\"rou" +
                        "nd\" stroke-linejoin=\"round\"></path><path d=\"M3.52844 7.29363L" +
                        "11.7086 11.8382C11.8898 11.9388 12.1102 11.9388 12.2914 11.8382L2" +
                        "0.5 7.27783\" stroke=\"#000000\" stroke-width=\"1.5\" stroke-line" +
                        "cap=\"round\" stroke-linejoin=\"round\"></path><path d=\"M12 3L12" +
                        " 12\" stroke=\"#000000\" stroke-width=\"1.5\" stroke-linecap=\"ro" +
                        "und\" stroke-linejoin=\"round\"></path><path d=\"M12 19.5V22\" s" +
                        "troke=\"#000000\" stroke-width=\"1.5\" stroke-linecap=\"round\" " +
                        "stroke-linejoin=\"round\"></path></svg>"
                binding.ivMap.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                try {
                    val svg = SVG.getFromString(svgString1)
                    val drawable = PictureDrawable(svg.renderToPicture())
                    binding.ivMap.setImageDrawable(drawable)
                } catch (_: SVGParseException) {

                }
                binding.ivMap.invalidate()
                Log.i("MyTag", "SWITCHED")
            }
        }
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
                TODO("Not yet implemented")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

