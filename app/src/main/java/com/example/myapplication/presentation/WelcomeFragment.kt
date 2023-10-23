package com.example.myapplication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentWelcomeBinding

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFind.setOnClickListener {
            launchListOfRooms()
        }
        binding.btnScan.setOnClickListener {
            launchQrScanner()
        }
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
}