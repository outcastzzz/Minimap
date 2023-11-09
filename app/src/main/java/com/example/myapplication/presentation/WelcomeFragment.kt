package com.example.myapplication.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentWelcomeBinding
import java.nio.file.Files
import java.nio.file.Paths

class WelcomeFragment: Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(34)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFind.setOnClickListener {
            launchListOfRooms()
        }
        binding.btnScan.setOnClickListener {
            takePhoto()
        }
        val intent = Intent(requireActivity().applicationContext, MyReceiver::class.java )
        intent.action = "MyAction"
        requireActivity().sendBroadcast(intent)
    }

    private fun launchQrScanner() {
        findNavController().navigate(R.id.action_welcomeFragment_to_scannerFragment)
    }

    private fun launchListOfRooms() {
        findNavController().navigate(R.id.action_welcomeFragment_to_listOfRooms)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private val permReqLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all {
                it.value
            }
            if (granted) {
                displayCameraFragment()
            }
        }

    private fun takePhoto() {
        activity?.let {
            if (hasPermissions(activity as Context, PERMISSIONS)) {
                displayCameraFragment()
            } else {
                permReqLauncher.launch(
                    PERMISSIONS
                )
            }
        }
    }

    // util method
    private fun hasPermissions(context: Context, permissions: Array<String>): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun displayCameraFragment() {
        launchQrScanner()
    }


    companion object {
        var PERMISSIONS = arrayOf(
            android.Manifest.permission.CAMERA,
        )
    }
}