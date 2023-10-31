package com.example.myapplication.presentation

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentScannerBinding
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

class ScannerFragment: Fragment(), ZBarScannerView.ResultHandler {

    private lateinit var zbView: ZBarScannerView

    private var _binding: FragmentScannerBinding? = null
    private val binding: FragmentScannerBinding
        get() = _binding ?: throw RuntimeException("FragmentScanner == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScannerBinding.inflate(layoutInflater, container, false)
        zbView = ZBarScannerView(requireActivity().applicationContext)
        return zbView
    }

    override fun handleResult(result: Result?) {
        val str = result?.contents
        Log.d("MyTag", "$str")
        launchMapFragment()
    }

    private fun launchMapFragment() {
        findNavController().navigate(R.id.action_scannerFragment_to_mapFragment)
    }

    override fun onResume() {
        super.onResume()
        zbView.setResultHandler(this)
        zbView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        zbView.stopCamera()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}